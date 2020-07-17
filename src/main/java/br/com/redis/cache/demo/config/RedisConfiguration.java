package br.com.redis.cache.demo.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import br.com.redis.cache.demo.cache.Caches;
import br.com.redis.cache.demo.cache.utils.CustomKeyGenerator;

@Configuration
public class RedisConfiguration {

	@Bean
	public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
		return builder -> { //@formatter:off
			
            Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
            configurationMap.put(Caches.USER_CACHE, RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(20))); 
            configurationMap.put(Caches.PHONE_CACHE, RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(30))); 
            builder.withInitialCacheConfigurations(configurationMap);
            
        }; //@formatter:on
	}

	@Bean("customKeyGenerator")
	public KeyGenerator keyGenerator() {
		return new CustomKeyGenerator();
	}

}
