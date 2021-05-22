package com.dgp.paymentservice.api;

import com.dgp.paymentservice.dto.TransferDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FirstBankService implements BankService {

    private final FirstFeignService firstFeignService;

    public FirstBankService(FirstFeignService firstFeignService) {
        this.firstFeignService = firstFeignService;
    }

    @Override
    public ResponseEntity transfer(TransferDTO transferDTO) {
        return firstFeignService.transfer(transferDTO);
    }

}
