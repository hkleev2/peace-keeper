package com.hkleev2.peacekeeper;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@RequiredArgsConstructor
@EnableJpaAuditing
@SpringBootApplication
public class PeaceKeeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeaceKeeperApplication.class, args);
	}

}