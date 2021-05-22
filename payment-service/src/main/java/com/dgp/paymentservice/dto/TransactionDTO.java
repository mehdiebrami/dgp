package com.dgp.paymentservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionDTO {

    private Long id;
    private String sourceNumber;
    private String destNumber;
    private int bankStatus;
    private Long amount;
    private Date created;
}
