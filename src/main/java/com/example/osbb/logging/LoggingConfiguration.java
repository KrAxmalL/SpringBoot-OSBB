package com.example.osbb.logging;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class LoggingConfiguration {

  private final LogRepository logRepository;

  @Bean
  public DatabaseAppender databaseAppender() {
    DatabaseAppender databaseAppender = DatabaseAppender.createAppender("MockAppender", null);
    databaseAppender.setLogRepository(logRepository);
    return databaseAppender;
  }
}
