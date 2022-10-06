package com.drink.ordering.system.barista.service.dataaccess.order.repository;

import com.drink.ordering.system.barista.service.dataaccess.order.entity.OrderEntity;
import com.drink.ordering.system.common.domain.valueobject.OrderPreparationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {

    boolean existsById(UUID id);

    Optional<OrderEntity> findFirstByOrderPreparationStatusOrderByCreatedAtAsc(OrderPreparationStatus orderPreparationStatus);
}
