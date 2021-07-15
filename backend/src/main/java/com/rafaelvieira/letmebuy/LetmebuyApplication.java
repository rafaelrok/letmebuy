package com.rafaelvieira.letmebuy;

import com.rafaelvieira.letmebuy.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LetmebuyApplication implements CommandLineRunner {

	@Autowired
	private S3Service s3service;

	public static void main(String[] args) {

		SpringApplication.run(LetmebuyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		s3service.uploadFile("A:\\DESENVOLVIMENTO\\bootcamp_devsuperior\\code-test.jpg");
	}
}
