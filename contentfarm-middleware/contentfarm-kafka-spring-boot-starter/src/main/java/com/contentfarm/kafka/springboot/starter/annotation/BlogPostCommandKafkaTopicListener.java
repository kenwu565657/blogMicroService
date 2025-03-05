package com.contentfarm.kafka.springboot.starter.annotation;

import com.contentfarm.kafka.springboot.starter.constant.ContentFarmKafkaGroupConstant;
import com.contentfarm.kafka.springboot.starter.constant.ContentFarmKafkaTopicConstant;
import org.springframework.kafka.annotation.KafkaListener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@KafkaListener(topics = ContentFarmKafkaTopicConstant.CONTENT_FARM_KAFKA_TOPIC, groupId = ContentFarmKafkaGroupConstant.CONTENT_FARM_KAFKA_GROUP)
public @interface BlogPostCommandKafkaTopicListener {
}
