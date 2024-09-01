package com.alugueaqui.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class ExternalPropertiesConfig {

    @Value("${external.path}")
    private String filePath;

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("C:\\config_propriedades\\aluga_aqui_config.txt")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        configurer.setProperties(properties);
        return configurer;
    }
}