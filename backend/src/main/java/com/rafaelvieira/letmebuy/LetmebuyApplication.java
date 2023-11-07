package com.rafaelvieira.letmebuy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rafae
 */

// @EnableTransactionManagement
// @ComponentScan(basePackages = {"com.rafaelvieira.letmebuy.entities"})
@SpringBootApplication
public class LetmebuyApplication {

	public static void main(String[] args) {
		SpringApplication.run(LetmebuyApplication.class, args);
	}

}
