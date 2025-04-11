package com.example.BackEnd.Sistema.Portuario.SENAI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RestController

public class BackEndSistemaPortuarioSenaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndSistemaPortuarioSenaiApplication.class, args);
	}

@GetMapping("/hello")
	public String hello() {
		return "Hello World";
	}

/*@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!" + name);
	}*/

}