package com.isaque.simplecamel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.isaque.simplecamel.model.NameAddressDTO;
import com.isaque.simplecamel.repository.NameAddressRepository;

@SpringBootApplication
public class SimpleCamelApplication implements CommandLineRunner {

	@Autowired
	NameAddressRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(SimpleCamelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		NameAddressDTO addressDTO = new NameAddressDTO("Isaque", "68", "Sao paulo", "Sao Paulo", "03591070");
//		repository.save(addressDTO);
		
	}

}
