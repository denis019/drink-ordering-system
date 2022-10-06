package com.drink.ordering.system.infrastructure.kafka.consumer;

import org.apache.avro.specific.SpecificRecordBase;

import java.time.Instant;
import java.util.List;

public interface KafkaConsumer<T extends SpecificRecordBase> {
    void receive(
            List<T> messages,
            List<String> keys,
            List<Integer> partitions,
            Instant createdAt,
            List<Long> offsets
    );
}
