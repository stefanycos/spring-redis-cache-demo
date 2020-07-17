package br.com.redis.cache.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.redis.cache.demo.domain.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
