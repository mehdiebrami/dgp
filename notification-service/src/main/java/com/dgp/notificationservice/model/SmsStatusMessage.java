package com.dgp.notificationservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SmsStatusMessage implements Serializable {
    private Long transactionId;
    private int status;
}
