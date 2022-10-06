package com.drink.ordering.system.barista.service.domain;

import com.drink.ordering.system.barista.service.domain.dto.receive.AcceptOrderDto;
import com.drink.ordering.system.barista.service.domain.ports.input.message.listener.OrderMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderMessageListenerImpl implements OrderMessageListener {

    private final OrderAcceptHelper orderAcceptHelper;

    public OrderMessageListenerImpl(OrderAcceptHelper orderAcceptHelper) {
        this.orderAcceptHelper = orderAcceptHelper;
    }

    @Override
    public void acceptOrder(AcceptOrderDto acceptOrderDto) {
        orderAcceptHelper.acceptOrder(acceptOrderDto);
    }
}
