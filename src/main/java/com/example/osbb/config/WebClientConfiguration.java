package com.example.osbb.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfiguration {

  private final WebClientProperties webClientProperties;

  @Bean
  public WebClient monobankWebClient() {
    return WebClient.builder().baseUrl(webClientProperties.getUrl()).build();
  }
}
