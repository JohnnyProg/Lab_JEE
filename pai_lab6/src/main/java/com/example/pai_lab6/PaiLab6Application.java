package com.example.pai_lab6;

import com.example.pai_lab6.models.User;
import com.example.pai_lab6.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PaiLab6Application {
	@Autowired
	private UserRepository repo;
	@Autowired
	private PasswordEncoder encoder;


	public static void main(String[] args) {
		SpringApplication.run(PaiLab6Application.class, args);
	}


	@PostConstruct
	public void init() {
		repo.save(new User("Piotr", "Piotrowski", "admin", encoder.encode("admin")));
		repo.save(new User("Ania", "Annowska", "ania", encoder.encode("ania")));
	}
}
