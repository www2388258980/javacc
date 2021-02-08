package com.redis;

import redis.clients.jedis.Jedis;

import java.util.Collections;

public class RedisTool {

		private static final String LOCK_SUCCESS = "OK";
		private static final String SET_IF_NOT_EXIST = "NX";
		private static final String SET_WITH_EXPIRE_TIME = "PX";

		/**
		 * 尝试获取分布式锁
		 *
		 * @param jedis      Redis客户端
		 * @param lockKey    锁
		 * @param requestId  请求标识
		 * @param expireTime 超期时间
		 * @return 是否获取成功
		 */
		public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {

				String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

				if (LOCK_SUCCESS.equals(result)) {
						return true;
				}
				return false;

		}

		/*
		可以看到，我们加锁就一行代码：jedis.set(String key, String value, String nxxx, String expx, int time)，这个set()方法一共有五个形参：

第一个为key，我们使用key来当锁，因为key是唯一的。

第二个为value，我们传的是requestId，很多童鞋可能不明白，有key作为锁不就够了吗，为什么还要用到value？原因就是我们在上面讲到可靠性时，分布式锁要满足第四个条件解铃还须系铃人，通过给value赋值为requestId，我们就知道这把锁是哪个请求加的了，在解锁的时候就可以有依据。requestId可以使用UUID.randomUUID().toString()方法生成。

第三个为nxxx，这个参数我们填的是NX，意思是SET IF NOT EXIST，即当key不存在时，我们进行set操作；若key已经存在，则不做任何操作；

第四个为expx，这个参数我们传的是PX，意思是我们要给这个key加一个过期的设置，具体时间由第五个参数决定。

第五个为time，与第四个参数相呼应，代表key的过期时间。

总的来说，执行上面的set()方法就只会导致两种结果：

1. 当前没有锁（key不存在），那么就进行加锁操作，并对锁设置个有效期，同时value表示加锁的客户端。

2. 已有锁存在，不做任何操作。
		 */

		public static void wrongGetLock1(Jedis jedis, String lockKey, String requestId, int expireTime) {
				Long result = jedis.setnx(lockKey, requestId);
				if (result == 1) {
						// 若在这里程序突然崩溃，则无法设置过期时间，将发生死锁
						jedis.expire(lockKey, expireTime);
				}
		}
		/*
		setnx()方法作用就是SET IF NOT EXIST，expire()方法就是给锁加一个过期时间。乍一看好像和前面的set()方法结果一样，然而由于这是两条Redis命令，不具有原子性，如果程序在执行完setnx()之后突然崩溃，导致锁没有设置过期时间。
		那么将会发生死锁。网上之所以有人这样实现，是因为低版本的jedis并不支持多参数的set()方法。
		 */

		public static boolean wrongGetLock2(Jedis jedis, String lockKey, int expireTime) {
				long expires = System.currentTimeMillis() + expireTime;
				String expiresStr = String.valueOf(expires);
				// 如果当前锁不存在，返回加锁成功
				if (jedis.setnx(lockKey, expiresStr) == 1) {
						return true;
				}
				// 如果锁存在，获取锁的过期时间
				String currentValueStr = jedis.get(lockKey);
				if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
						// 锁已过期，获取上一个锁的过期时间，并设置现在锁的过期时间
						String oldValueStr = jedis.getSet(lockKey, expiresStr);
						if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
								// 考虑多线程并发的情况，只有一个线程的设置值和当前值相同，它才有权利加锁
								return true;
						}
				}
				// 其他情况，一律返回加锁失败
				return false;
		}

		private static final Long RELEASE_SUCCESS = 1L;

		/**
		 * 释放分布式锁
		 *
		 * @param jedis     Redis客户端
		 * @param lockKey   锁
		 * @param requestId 请求标识
		 * @return 是否释放成功
		 */
		public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {
				String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
				Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
				if (RELEASE_SUCCESS.equals(result)) {
						return true;
				}
				return false;
		}
		/*
		可以看到，我们解锁只需要两行代码就搞定了！第一行代码，我们写了一个简单的Lua脚本代码，上一次见到这个编程语言还是在《黑客与画家》里，没想到这次居然用上了。第二行代码，我们将Lua代码传到jedis.eval()方法里，并使参数KEYS[1]赋值为lockKey，ARGV[1]赋值为requestId。eval()方法是将Lua代码交给Redis服务端执行。

那么这段Lua代码的功能是什么呢？其实很简单，首先获取锁对应的value值，检查是否与requestId相等，如果相等则删除锁（解锁）。那么为什么要使用Lua语言来实现呢？因为要确保上述操作是原子性的。关于非原子性会带来什么问题，可以阅读【解锁代码-错误示例2】 。那么为什么执行eval()方法可以确保原子性，源于Redis的特性，简单来说，就是在eval命令执行Lua代码的时候，
Lua代码将被当成一个命令去执行，并且直到eval命令执行完成，Redis才会执行其他命令。
		 */

		public static void wrongReleaseLock1(Jedis jedis, String lockKey) {
				jedis.del(lockKey);
		}
		/*
		最常见的解锁代码就是直接使用jedis.del()方法删除锁，这种不先判断锁的拥有者而直接解锁的方式，
		会导致任何客户端都可以随时进行解锁，即使这把锁不是它的。
		 */


		public static void wrongReleaseLock2(Jedis jedis, String lockKey, String requestId) {
				// 判断加锁与解锁是不是同一个客户端
				if (requestId.equals(jedis.get(lockKey))) {
						// 若在此时，这把锁突然不是这个客户端的，则会误解锁
						jedis.del(lockKey);
				}
		}
		/*
		如代码注释，这个代码的问题在于如果调用jedis.del()方法的时候，这把锁已经不属于当前客户端的时候会解除他人加的锁。那么是否真的有这种场景？答案是肯定的，比如客户端A加锁，一段时间之后客户端A解锁，在执行jedis.del()之前，
		锁突然过期了，此时客户端B尝试加锁成功，然后客户端A再执行del()方法，则将客户端B的锁给解除了。
		 */
}
