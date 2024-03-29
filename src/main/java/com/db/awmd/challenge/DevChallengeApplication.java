package com.db.awmd.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.db.awmd.challenge"})
public class DevChallengeApplication {

  public static void main(String[] args) {

    SpringApplication.run(DevChallengeApplication.class, args);

  }
}
