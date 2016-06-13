package com.test.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Johan Vargas
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.test")
public class AppConfiguration {
	

}