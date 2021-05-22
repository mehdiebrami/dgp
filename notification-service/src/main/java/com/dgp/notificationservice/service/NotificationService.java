package com.dgp.notificationservice.service;

import com.dgp.notificationservice.api.SmsService;
import com.dgp.notificationservice.model.SmsMessage;
import com.dgp.notificationservice.model.SmsStatusMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class NotificationService {
    private final SmsService smsService;
    private final MessageProducer messageProducer;

    public NotificationService(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") SmsService smsService, MessageProducer messageProducer) {
        this.smsService = smsService;
        this.messageProducer = messageProducer;
    }


    void sendSms(SmsMessage smsMessage) throws JsonProcessingException {
        ResponseEntity responseEntity = smsService.sendMessage(smsMessage);
        if (responseEntity != null) {
            SmsStatusMessage smsResultMessage = new SmsStatusMessage();
            smsResultMessage.setTransactionId(smsMessage.getTransactionId());
            smsResultMessage.setStatus(responseEntity.getStatusCode().value());
            if (responseEntity.getStatusCode().equals(HttpStatus.OK))
                log.info("SMS notification for transaction: {} has been sent.", smsMessage.getTransactionId());
            else log.error("SMS notification for transaction: {} has been failed.", smsMessage.getTransactionId());
            messageProducer.send(smsResultMessage);
        }
    }

}
