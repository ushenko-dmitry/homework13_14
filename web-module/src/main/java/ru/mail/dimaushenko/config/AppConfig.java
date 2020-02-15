package ru.mail.dimaushenko.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"ru.mail.dimaushenko.repository",
    "ru.mail.dimaushenko.service",
    "ru.mail.dimaushenko.controller"
})
@PropertySource({"constants.properties"})
public class AppConfig {

}
