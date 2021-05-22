package com.dgp.paymentservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SmsStatusMessage implements Serializable {
    private Long transactionId;
    private int status;
}
