package br.com.redis.cache.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.redis.cache.demo.domain.User;
import br.com.redis.cache.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {

	private UserRepository repository;

	public void save(User user) {
		log.info("Saving user '{}'", user);
		repository.save(user);
	}

	public Optional<User> findById(Long id) {
		log.info("Searching user '{}' in database...", id);
		return repository.findById(id);
	}

	public List<User> findAll() {
		log.info("Searching all users in database...");
		return repository.findAll();
	}

	public void delete(User user) {
		log.info("Deleting user '{}'", user);
		repository.delete(user);
	}
}
