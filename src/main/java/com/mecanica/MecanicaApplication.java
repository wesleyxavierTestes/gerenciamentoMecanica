package com.mecanica;

import com.mecanica.application.validations.Validations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({
	"com.mecanica.controller",
	"com.mecanica.domain.services",
	"com.mecanica.application.applicationServices",
	"com.mecanica.application.config"
})
@EntityScan("com.mecanica.domain.entities")
@EnableJpaRepositories("com.mecanica.infra.repositorys")
@SpringBootApplication
public class MecanicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MecanicaApplication.class, args);
	}


	@Bean
	public Validations validations() {
		return new Validations();
	}

}
