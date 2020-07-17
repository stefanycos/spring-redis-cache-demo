package br.com.redis.cache.demo.controller;

import static br.com.redis.cache.demo.cache.Caches.USER_CACHE;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.redis.cache.demo.controller.dto.UserDTO;
import br.com.redis.cache.demo.domain.User;
import br.com.redis.cache.demo.service.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

	private UserService service;

	@CacheEvict(value = USER_CACHE, allEntries = true)
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public User create(@RequestBody UserDTO payload) {
		User user = payload.toUser();
		service.save(user);

		return user;
	}

	@Cacheable(value = USER_CACHE, key = "#id", unless = "#result.age < 18")
	@GetMapping("/{id}")
	public User read(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
	}

	@Cacheable(value = USER_CACHE, key = "#root.method.name")
	@GetMapping
	public List<User> list() {
		return service.findAll();
	}

	@CacheEvict(value = USER_CACHE, key = "#id")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		User user = this.read(id);
		service.delete(user);
	}

	@CachePut(value = USER_CACHE, key = "#id")
	@PutMapping("/{id}")
	public User update(@PathVariable Long id, @RequestBody UserDTO payload) {
		User user = this.read(id);

		payload.update(user);
		service.save(user);

		return user;
	}

}
