package com.dgp.paymentservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BankServiceFactory {

    private final FirstBankService firstBankService;
    private final SecondBankService secondBankService;

    public BankService getBAnkServiceProvider(String cardNumber) {
        String firstPart = cardNumber.substring(0, 4);
        if ("6037".equals(firstPart)) {
            return firstBankService;
        }
        return secondBankService;
    }


}
