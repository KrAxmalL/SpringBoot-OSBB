package com.example.osbb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class OSBBApplication {

  public static void main(String[] args) {
    SpringApplication.run(OSBBApplication.class, args);
  }
}
