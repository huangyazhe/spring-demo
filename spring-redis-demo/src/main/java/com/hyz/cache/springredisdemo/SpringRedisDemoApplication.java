package com.hyz.cache.springredisdemo;

import com.hyz.cache.springredisdemo.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import redis.clients.jedis.JedisPoolConfig;

@SpringBootApplication
public class SpringRedisDemoApplication {

	@Bean
	JedisConnectionFactory jedisConnectionFactory(){
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(5);
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestOnReturn(true);

		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
		jedisConFactory.setHostName("localhost");
		jedisConFactory.setPort(6379);
		jedisConFactory.setTimeout(5000);
		return jedisConFactory;
	}

	@Bean
	RedisTemplate<String ,User> redisTemplate(){
		RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Object.class));
		redisTemplate.setEnableTransactionSupport(true);
		return redisTemplate;
	}

	@Bean
	public StringRedisTemplate stringRedisTemplate() {
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(jedisConnectionFactory());
		stringRedisTemplate.setEnableTransactionSupport(true);
		return stringRedisTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringRedisDemoApplication.class, args);
	}
}
