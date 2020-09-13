package com.weekendceo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@SpringBootApplication
@EnableConfigurationProperties
public class BootWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootWebApplication.class, args);
	}

	@Bean(name = "bnViewResolver")
	public BeanNameViewResolver bnViewResolver() {
		return new BeanNameViewResolver();
	}

	@Bean(name = "jsonView")
	public MappingJackson2JsonView jsonView() {
		return new MappingJackson2JsonView();
	}
}
