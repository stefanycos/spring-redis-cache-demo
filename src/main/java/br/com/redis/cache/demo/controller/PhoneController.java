package br.com.redis.cache.demo.controller;

import static br.com.redis.cache.demo.cache.Caches.PHONE_CACHE;

import java.util.List;

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

import br.com.redis.cache.demo.cache.annotations.PhoneCache;
import br.com.redis.cache.demo.controller.dto.PhoneDTO;
import br.com.redis.cache.demo.domain.Phone;
import br.com.redis.cache.demo.service.PhoneService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/phones")
public class PhoneController {

	private PhoneService service;

	@PhoneCache
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public Phone create(@RequestBody PhoneDTO payload) {
		Phone phone = payload.toPhone();
		service.save(phone);

		return phone;
	}

	@Cacheable(value = PHONE_CACHE, key = "#id", condition = "#id > 2")
	@GetMapping("/{id}")
	public Phone read(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone not found"));
	}

	@Cacheable(value = PHONE_CACHE, keyGenerator = "customKeyGenerator")
	@GetMapping
	public List<Phone> list() {
		return service.findAll();
	}

	@PhoneCache
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Phone phone = this.read(id);
		service.delete(phone);
	}

	@PhoneCache
	@PutMapping("/{id}")
	public Phone update(@PathVariable Long id, @RequestBody PhoneDTO payload) {
		Phone phone = this.read(id);

		payload.update(phone);
		service.save(phone);

		return phone;
	}

}
