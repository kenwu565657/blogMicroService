package com.contentfarm.kafka.springboot.starter.constant;

public class ContentFarmKafkaTopicConstant {
    private ContentFarmKafkaTopicConstant() {}
    public static final String CONTENT_FARM_KAFKA_TOPIC = "contentfarm-kafka-consumer";
    public static final String CONTENT_FARM_KAFKA_ADD_BLOGPOST_TOPIC = "add-blogpost-topic";
    public static final String CONTENT_FARM_KAFKA_ADD_BLOGPOST_COMPLETE_TOPIC = "add-blogpost-complete-topic";
    public static final String CONTENT_FARM_KAFKA_DELETE_BLOGPOST_TOPIC = "delete-blogpost-topic";
    public static final String CONTENT_FARM_KAFKA_INDEX_BLOGPOST_TOPIC = "index-blogpost-topic";
    public static final String CONTENT_FARM_KAFKA_DE_INDEX_BLOGPOST_TOPIC = "de-index-blogpost-topic";
    public static final String CONTENT_FARM_KAFKA_DE_INDEX_BLOGPOST_COMPLETE_TOPIC = "de-index-blogpost-complete-topic";
}
