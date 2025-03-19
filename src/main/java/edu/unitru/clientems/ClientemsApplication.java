package edu.unitru.clientems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "edu.unitru.clientems.feign")
public class ClientemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientemsApplication.class, args);
	}

}
