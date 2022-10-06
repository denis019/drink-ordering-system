package com.drink.ordering.system.barista.service.dataaccess.order.entity;

import com.drink.ordering.system.common.domain.valueobject.OrderPreparationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Entity
public class OrderEntity {
    @NotNull
    @Id
    @Column(nullable = false)
    private UUID id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderPreparationStatus orderPreparationStatus;

    @Column(updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
