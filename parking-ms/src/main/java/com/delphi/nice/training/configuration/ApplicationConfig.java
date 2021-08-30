package com.delphi.nice.training.configuration;

import com.delphi.nice.training.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:/application.properties")
@ComponentScan("com/delphi/nice/training/service")
public class ApplicationConfig {


}
