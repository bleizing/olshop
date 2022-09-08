package com.dimasari.olshop;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.dimasari.olshop.model.User;
import com.dimasari.olshop.repo.UserRepository;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan("com.dimasari.olshop.*")
public class OnlineShopApplication {
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(OnlineShopApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void userInit() {
		var usersOptional = userRepository.findByDeletedIsFalse();
		
		var init = false;
		
		if (usersOptional.isPresent()) {
			if (usersOptional.get().size() < 1) {
				init = true;
			}
		} else {
			init = true;
		}
		
		if (init) {
			var user = new User();
			user.setCreatedAt(LocalDateTime.now());
			user.setName("User Test");
			user.setEmail("test@test.com");
			user.setPhoneNumber("081234567890");
			user.setUsername("user_test");
			userRepository.saveAndFlush(user);
		}
	}

}
