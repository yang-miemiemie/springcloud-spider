package com.mie.spider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mie.util.ApplicationContextHolder;

@Configuration
public class SpringConfig {
	@Bean
	public ApplicationContextHolder applicationContextHolder() {
		return new ApplicationContextHolder();
	}
}
