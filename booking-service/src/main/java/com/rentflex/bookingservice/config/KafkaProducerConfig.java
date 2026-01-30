package com.rentflex.bookingservice.config;

import com.rentflex.bookingservice.kafka.events.BookingCreatedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public KafkaTemplate<String, BookingCreatedEvent> kafkaTemplate(
            ProducerFactory<String, BookingCreatedEvent> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
