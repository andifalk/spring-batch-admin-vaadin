package de.batchadmin.vaadin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.vaadin.spring.stuff.sidebar.EnableSideBar;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableSideBar
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}
