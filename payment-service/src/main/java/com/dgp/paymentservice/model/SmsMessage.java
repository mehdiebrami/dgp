package com.dgp.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsMessage implements Serializable {
    private Long transactionId;
    private String content;
    private String phoneNumber;
}
