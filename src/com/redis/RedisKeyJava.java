package com.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class RedisKeyJava {
		public static void main(String[] args) {
				//连接本地的 Redis 服务
				Jedis jedis = new Jedis("localhost");
				System.out.println("连接成功");

				// 获取数据并输出
				Set<String> keys = jedis.keys("*");
				keys.forEach(data -> System.out.println(data));
		}
}
