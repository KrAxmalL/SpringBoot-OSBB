package com.example.osbb.security.jwt;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Value
@ConfigurationProperties(prefix = "jwt")
@ConstructorBinding
public class JwtProperties {

  String secret;
}
