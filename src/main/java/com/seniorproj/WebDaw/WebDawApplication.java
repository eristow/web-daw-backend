package com.seniorproj.WebDaw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.seniorproj.WebDaw.services")
public class WebDawApplication{
	public static void main(String[] args) {SpringApplication.run(WebDawApplication.class, args); }
}
