package io.vicevil4.slogging.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class AppConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer propsConfig() {
        PropertySourcesPlaceholderConfigurer props = new PropertySourcesPlaceholderConfigurer();
        props.setLocation(new ClassPathResource("git.properties"));
        props.setIgnoreResourceNotFound(true);
        props.setIgnoreUnresolvablePlaceholders(true);
        return props;
    }
}
