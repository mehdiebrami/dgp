package com.dgp.paymentservice.api;

import com.dgp.paymentservice.dto.TransferDTO;
import org.springframework.http.ResponseEntity;

public interface BankService {

    ResponseEntity transfer(TransferDTO transferDTO);

}
