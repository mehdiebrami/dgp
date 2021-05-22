package com.dgp.notificationservice.service;


import com.dgp.notificationservice.model.SmsMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class MessageConsumer {

    private final NotificationService notificationService;

    public MessageConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void handleMessage(SmsMessage message) throws JsonProcessingException {
        notificationService.sendSms(message);
    }

}
