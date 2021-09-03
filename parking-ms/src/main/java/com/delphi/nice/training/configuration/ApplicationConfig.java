package com.delphi.nice.training.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:/application.properties")
@ComponentScan("com/delphi/nice/training/service")
public class ApplicationConfig {

}
