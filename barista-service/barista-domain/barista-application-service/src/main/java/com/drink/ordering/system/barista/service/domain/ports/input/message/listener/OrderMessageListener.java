package com.drink.ordering.system.barista.service.domain.ports.input.message.listener;

import com.drink.ordering.system.barista.service.domain.dto.receive.AcceptOrderDto;

public interface OrderMessageListener {

    void acceptOrder(AcceptOrderDto acceptOrderDto);
}
