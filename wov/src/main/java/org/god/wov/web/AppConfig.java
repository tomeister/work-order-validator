package org.god.wov.web;

import org.god.wov.rules.WorkOrderValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public WorkOrderValidator workOrderValidator() {
        return new WorkOrderValidator();
    }
}
