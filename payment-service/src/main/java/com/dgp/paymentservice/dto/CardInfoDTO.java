package com.dgp.paymentservice.dto;

import com.dgp.paymentservice.validation.CardNumberValidation;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CardInfoDTO {
    @JsonIgnore
    private Long id;
    @CardNumberValidation
    private String number;
    @NotEmpty
    private String cvv2;
    @NotEmpty
    private String expireDate;

    @JsonGetter("id")
    public Long get_Id() {
        return id;
    }
}
