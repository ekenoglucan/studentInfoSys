package com.example.demo.Config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SecurityConfig {
    private String jwtSecret = "sis_demo";
}
