package com.drink.ordering.system.order.service.application.rest;

import com.drink.ordering.system.common.domain.valueobject.OrderId;
import com.drink.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.drink.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.drink.ordering.system.order.service.domain.dto.find.FindOrderQuery;
import com.drink.ordering.system.order.service.domain.dto.find.FindOrderResponse;
import com.drink.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/orders", produces = "application/vnd.api.v1+json")
public class OrderController {

    private final OrderApplicationService orderApplicationService;

    public OrderController(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderCommand createOrderCommand) {
        CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);
        return ResponseEntity.ok(createOrderResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindOrderResponse> findOrder(@PathVariable("id") UUID orderId) {
        FindOrderResponse findOrderResponse = orderApplicationService.findOrder(FindOrderQuery.builder()
                .orderId(new OrderId(orderId))
                .build()
        );
        return ResponseEntity.ok(findOrderResponse);
    }
}
