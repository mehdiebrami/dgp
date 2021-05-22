package com.dgp.paymentservice.api;

import com.dgp.paymentservice.dto.TransferDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "otherBankService", url = "http://64q5j.mocklab.io")
public interface OtherFeignService {

    @RequestMapping(method = RequestMethod.POST, value = "/payments/transfer", consumes = "application/json")
    ResponseEntity transfer(TransferDTO transferDTO);
}
