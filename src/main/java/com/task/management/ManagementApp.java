package com.task.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.task.management")
public class ManagementApp {

  public static void main(String[] args) {
    SpringApplication.run(ManagementApp.class, args);
  }

}
