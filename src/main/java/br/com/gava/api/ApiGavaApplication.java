package br.com.gava.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ApiGavaApplication {

  public static void main(String[] args) {
    SpringApplication.run(ApiGavaApplication.class, args);
  }

}
