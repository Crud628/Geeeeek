package io.lan.cache.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;

public class RedisConfig {

	@Bean
	public Redisson redisson() {
		Config config = new Config();
		config.useSingleServer().setAddress("redis://192.168.111.147:6379").setDatabase(0);
		return (Redisson)Redisson.create(config);
	}
}
