package com.dgp.notificationservice.model;

import lombok.*;

import java.io.Serializable;

@Data
public class SmsMessage implements Serializable {
    private String phoneNumber;
    private String content;
    private Long transactionId;

}
