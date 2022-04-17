package com.nhnacademy.edu.springframework.project2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.nhnacademy.edu.springframework.project2")
@EnableAspectJAutoProxy
public class MainConfiguration {
}
