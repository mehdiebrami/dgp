package com.dgp.notificationservice.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@EnableBinding(NotificationChannel.class)
@Configuration
public class NotificationServiceConfiguration {
    private final AmqpAdmin amqpAdmin;

    @Value("${spring.rabbitmq.template.default-receive-queue}")
    private String notificationQueue;


    public NotificationServiceConfiguration(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    @PostConstruct
    public void createQueues() {
        amqpAdmin.declareQueue(new Queue(notificationQueue, true));
    }


}
