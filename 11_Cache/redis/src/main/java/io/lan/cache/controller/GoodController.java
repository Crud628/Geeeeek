package io.lan.cache.controller;

import java.util.UUID;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodController {
	public static final String REDIS_LOCK = "MyLock";
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Value("server.port")
	private String serverPort;
	
	@Autowired
	private Redisson redisson;
	
	@GetMapping("/buy_goods")
	public String buyGoods() throws Exception {

		RLock lock = redisson.getLock(REDIS_LOCK);
		
		lock.lock();
		try {
			String result = stringRedisTemplate.opsForValue().get("goods:001");
			int goodsNumber = result == null ? 0 : Integer.parseInt(result);
			
			if (goodsNumber > 0) {
				int realNumber = goodsNumber - 1;
				stringRedisTemplate.opsForValue().set("goods:001", String.valueOf(realNumber));
				System.out.println("库存剩下" + realNumber + "port:" + serverPort);	
			} else {
				System.out.println("已经售完");
			}
			return "已经售完";
		} finally {
			if (lock.isLocked() && lock.isHeldByCurrentThread()) {
				lock.unlock();
			}

		}

	}
	
}