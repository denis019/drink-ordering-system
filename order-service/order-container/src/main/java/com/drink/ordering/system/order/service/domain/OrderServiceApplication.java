package com.drink.ordering.system.order.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {
        "com.drink.ordering.system.order.service.dataaccess",
        "com.drink.ordering.system.common.dataaccess"
})
@EntityScan(basePackages = {
        "com.drink.ordering.system.order.service.dataaccess",
        "com.drink.ordering.system.common.dataaccess"
})
@SpringBootApplication(scanBasePackages = {
        "com.drink.ordering.system.order",
        "com.drink.ordering.system.common",
        "com.drink.ordering.system.infrastructure"
})
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
