package br.com.redis.cache.demo.controller.dto;

import br.com.redis.cache.demo.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private String name;

	private Integer age;

	private String passportNumber;

	public User toUser() { //@formatter:off
		return User.builder()
				.name(name)
				.age(age)
				.passportNumber(passportNumber)
				.build(); //@formatter:on
	}
	
	public User update(User user) { //@formatter:off
		user.setName(name);
		user.setAge(age);
		user.setPassportNumber(passportNumber);
		
		return user; //@formatter:on
	}

}
