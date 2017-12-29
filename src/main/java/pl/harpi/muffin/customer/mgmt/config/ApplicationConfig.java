package pl.harpi.muffin.customer.mgmt.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@ComponentScan(basePackages = "pl.harpi.muffin.common")
public class ApplicationConfig {
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("utf-8");
        messageSource.setBasenames("common-exceptions", "exceptions");
        return messageSource;
    }
}
