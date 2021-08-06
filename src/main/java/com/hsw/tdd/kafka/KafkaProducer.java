package com.hsw.tdd.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

@Slf4j
public class KafkaProducer {

    @Value("${spring.kafka.template.address-topic}")
    private String publishTopic;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    public void start() {
        MessageListenerContainer listenerContainer =
                kafkaListenerEndpointRegistry.getListenerContainer(groupId);
        listenerContainer.start();
    }

    @KafkaListener(
            id = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.consumer.address-topic}",
            autoStartup = "false")
    public void listener(String message, @Header(KafkaHeaders.OFFSET) Long offset) {
        log.info("AddressRequest Start Offset is {}", offset);
        log.info("Request message : {}", message);

    }
}
