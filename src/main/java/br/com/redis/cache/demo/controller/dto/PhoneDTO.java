package br.com.redis.cache.demo.controller.dto;

import br.com.redis.cache.demo.domain.Phone;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDTO {

	private String prefix;

	
	private String number;

	public Phone toPhone() { //@formatter:off
		return Phone.builder()
				.prefix(prefix)
				.number(number)
				.build(); //@formatter:on
	}
	
	public Phone update(Phone phone) { //@formatter:off
		phone.setNumber(number);
		phone.setPrefix(prefix);
		
		return phone; //@formatter:on
	}

}
