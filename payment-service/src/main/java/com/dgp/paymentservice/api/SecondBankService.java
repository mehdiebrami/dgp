package com.dgp.paymentservice.api;

import com.dgp.paymentservice.dto.TransferDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SecondBankService implements BankService {

    private final OtherFeignService otherFeignService;

    public SecondBankService(OtherFeignService otherFeignService) {
        this.otherFeignService = otherFeignService;
    }

    @Override
    public ResponseEntity transfer(TransferDTO transferDTO) {
        return otherFeignService.transfer(transferDTO);
    }

}
