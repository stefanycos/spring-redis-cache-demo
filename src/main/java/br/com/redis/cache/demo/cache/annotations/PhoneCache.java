package br.com.redis.cache.demo.cache.annotations;



import static br.com.redis.cache.demo.cache.Caches.PHONE_CACHE;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.cache.annotation.CacheEvict;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@CacheEvict(value = PHONE_CACHE, allEntries = true)
public @interface PhoneCache {

}
