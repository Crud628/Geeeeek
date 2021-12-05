package io.lan.cache;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;

@SpringBootApplication(scanBasePackages = "io.lan.cache")
public class RedisApplication {

	public static void main(String[] args) {

		// C1.最简单demo
		Jedis jedis = new Jedis("localhost", 6379);
		System.out.println(jedis.info());
		jedis.set("uptime", new Long(System.currentTimeMillis()).toString());
		System.out.println(jedis.get("uptime"));
		jedis.set("teacher", "Cuijing");
		System.out.println(jedis.get("teacher"));

	}

}
