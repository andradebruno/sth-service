package br.com.bruno.sth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = "br.com.bruno.sth.repository")
@EnableHystrix
@EnableFeignClients
public class SthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SthServiceApplication.class, args);
	}

}
