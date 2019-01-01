package com.example.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Slf4j
@EnableAsync
public class AlgorithmApplication {

  public static void main(String[] args) {
    SpringApplication.run(AlgorithmApplication.class, args);
  }

  @RestController
  public static class MyController {


  }
}
