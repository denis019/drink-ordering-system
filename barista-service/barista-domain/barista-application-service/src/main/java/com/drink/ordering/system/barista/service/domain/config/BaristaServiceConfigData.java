package com.drink.ordering.system.barista.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "barista-service")
public class BaristaServiceConfigData {
    private String orderTopicName;
    private String baristaTopicName;
}
