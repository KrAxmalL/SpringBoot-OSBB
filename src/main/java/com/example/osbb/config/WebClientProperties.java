package com.example.osbb.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "web.monobank")
@ConstructorBinding
@RequiredArgsConstructor
@Getter
public class WebClientProperties {

  private final String url;
}
