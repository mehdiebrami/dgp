package com.dgp.notificationservice.api;

import com.dgp.notificationservice.model.SmsMessage;
import com.dgp.notificationservice.model.SmsStatusMessage;
import com.dgp.notificationservice.service.MessageProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class NotificationFallback implements SmsService {

    private final MessageProducer messageProducer;

    public NotificationFallback(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }


    @Override
    public ResponseEntity sendMessage(SmsMessage smsMessage) throws JsonProcessingException {
        log.error("Fallback has been occurred; transactionId: {} .", smsMessage.getTransactionId());
        SmsStatusMessage smsStatusMessage = new SmsStatusMessage();
        smsStatusMessage.setTransactionId(smsMessage.getTransactionId());
        smsStatusMessage.setStatus(HttpStatus.CONFLICT.value());
        messageProducer.send(smsStatusMessage);
        return new ResponseEntity(HttpStatus.CONFLICT);
    }
}
