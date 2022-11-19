package com.mcj.api.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer
{
	@Override
    public void addCorsMappings(CorsRegistry registry)
	{
		registry
		.addMapping("/**")
		// todos os endereços que você deseja liberar devem ser adicionados aqui
		.allowedOrigins("http://localhost:3000")
		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }	
}