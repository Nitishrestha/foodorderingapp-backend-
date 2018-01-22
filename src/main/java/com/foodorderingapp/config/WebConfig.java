package com.foodorderingapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.foodorderingapp")
public class WebConfig extends WebMvcConfigurerAdapter{

    public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**")
                .allowedMethods("*")
                        .allowedHeaders("*");
    }
}
