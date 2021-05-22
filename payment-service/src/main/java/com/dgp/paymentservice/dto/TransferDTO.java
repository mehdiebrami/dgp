package com.dgp.paymentservice.dto;

import com.dgp.paymentservice.validation.CardNumberValidation;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class TransferDTO {
    @CardNumberValidation
    private String source;
    @CardNumberValidation
    private String dest;
    @NotEmpty
    private String cvv2;
    @NotEmpty
    private String expDate;
    @NotEmpty
    private String pin;
    @NotNull
    private Long amount;
}
