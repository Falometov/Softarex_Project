package com.epam.esm.giftcertificates.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Runner class for SpringBoot application.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
@SpringBootApplication
@ComponentScan("com.epam.esm")
@EntityScan("com.epam.esm.giftcertificates.entity")
public class GiftCertificatesApp {

    private static final String VALIDATION_MESSAGES = "ValidationMessages";

    public static void main(final String[] args) {
        SpringApplication.run(GiftCertificatesApp.class, args);
    }

    /**
     * Bean for object {@link ModelMapper}.
     *
     * @return value of the object {@link ModelMapper}
     */
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    /**
     * Bean for object {@link ResourceBundleMessageSource}.
     *
     * @return value of the object {@link ResourceBundleMessageSource}
     */
    @Bean
    public ResourceBundleMessageSource getMessageSource() {
        final ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames(VALIDATION_MESSAGES);
        return source;
    }

    /**
     * Bean for object {@link BCryptPasswordEncoder}.
     *
     * @return value of the object {@link BCryptPasswordEncoder}
     */
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder(4);
    }

}
