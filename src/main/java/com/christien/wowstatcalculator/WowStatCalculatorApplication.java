package com.christien.wowstatcalculator;

import com.christien.wowstatcalculator.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class WowStatCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WowStatCalculatorApplication.class, args);
	}

}
