package com.redis;

import redis.clients.jedis.Jedis;

public class RedisJava {
		public static void main(String[] args) {
				//连接本地的 Redis 服务
				Jedis jedis = new Jedis("localhost", 6379);
				System.out.println("连接成功");
				//查看服务是否运行
				System.out.println("服务器正在运行: " + jedis.ping());
		}
}
