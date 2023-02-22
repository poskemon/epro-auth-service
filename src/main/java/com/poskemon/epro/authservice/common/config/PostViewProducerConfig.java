package com.poskemon.epro.authservice.common.config;

import com.poskemon.epro.authservice.domain.dto.UserDTO;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class PostViewProducerConfig {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServer;

    @Bean
    Map<String,Object> postViewProducerConfigs() {
        return CommonJsonSerializer.getStringObjectMap(bootstrapServer);
    }

    @Bean
    ProducerFactory<String, UserDTO> postViewCountDTOProducerFactory() {
        return new DefaultKafkaProducerFactory<>(postViewProducerConfigs());
    }

    @Bean
    KafkaTemplate<String, UserDTO> postViewDTOKafkaTemplate() {
        return new KafkaTemplate<>(postViewCountDTOProducerFactory());
    }
}