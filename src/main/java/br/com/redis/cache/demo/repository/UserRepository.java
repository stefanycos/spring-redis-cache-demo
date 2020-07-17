package br.com.redis.cache.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.redis.cache.demo.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
