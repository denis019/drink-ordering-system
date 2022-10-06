package com.drink.ordering.system.barista.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaRepositories(basePackages = {
        "com.drink.ordering.system.barista.service.dataaccess",
        "com.drink.ordering.system.common.dataaccess"
})
@EntityScan(basePackages = {
        "com.drink.ordering.system.barista.service.dataaccess",
        "com.drink.ordering.system.common.dataaccess"
})
@SpringBootApplication(scanBasePackages = {
        "com.drink.ordering.system.barista",
        "com.drink.ordering.system.common",
        "com.drink.ordering.system.infrastructure"
})
@EnableScheduling
public class BaristaServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaristaServiceApplication.class, args);
    }
}
