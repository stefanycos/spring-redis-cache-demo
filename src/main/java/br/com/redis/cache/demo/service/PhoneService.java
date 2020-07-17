package br.com.redis.cache.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.redis.cache.demo.domain.Phone;
import br.com.redis.cache.demo.repository.PhoneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class PhoneService {

	private PhoneRepository repository;

	public void save(Phone phone) {
		log.info("Saving phone '{}'", phone);
		repository.save(phone);
	}

	public Optional<Phone> findById(Long id) {
		log.info("Searching phone '{}' in database...", id);
		return repository.findById(id);
	}

	public List<Phone> findAll() {
		log.info("Searching all phones in database...");
		return repository.findAll();
	}

	public void delete(Phone phone) {
		log.info("Deleting phone '{}'", phone);
		repository.delete(phone);
	}
}
