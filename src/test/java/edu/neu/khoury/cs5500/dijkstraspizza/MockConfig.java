package edu.neu.khoury.cs5500.dijkstraspizza;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("edu.neu.khoury.cs5500.dijkstraspizza")
public class MockConfig implements WebMvcConfigurer {

}
