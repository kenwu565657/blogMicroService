package com.contentfarm.kafka.springboot.starter.config;

import com.contentfarm.kafka.springboot.starter.producer.IBlogPostKafkaMessageProducer;
import com.contentfarm.kafka.springboot.starter.producer.impl.BlogPostMessageProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableConfigurationProperties(KafkaProperty.class)
@Configuration
public class KafkaProducerConfiguration {
    @Bean
    public ProducerFactory<String, String> producerFactory(KafkaProperty kafkaProperty) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperty.getProducerBootstrapServer());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(KafkaProperty kafkaProperty) {
        return new KafkaTemplate<>(producerFactory(kafkaProperty));
    }

    @Bean
    IBlogPostKafkaMessageProducer blogPostKafkaMessageProducer(KafkaProperty kafkaProperty) {
        return new BlogPostMessageProducer(kafkaTemplate(kafkaProperty));
    }
}
