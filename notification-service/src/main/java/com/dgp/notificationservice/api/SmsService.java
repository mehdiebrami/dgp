package com.dgp.notificationservice.api;

import com.dgp.notificationservice.model.SmsMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "smsProviderService", url = "http://64q5j.mocklab.io", fallback = NotificationFallback.class)
public interface SmsService {

    @RequestMapping(method = RequestMethod.POST, value = "/sms-notification/send", consumes = "application/json")
    ResponseEntity sendMessage(@RequestBody SmsMessage smsMessage) throws JsonProcessingException;
}
