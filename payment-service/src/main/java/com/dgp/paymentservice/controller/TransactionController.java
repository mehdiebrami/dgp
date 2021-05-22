package com.dgp.paymentservice.controller;

import com.dgp.paymentservice.dto.TransactionDTO;
import com.dgp.paymentservice.dto.TransferDTO;
import com.dgp.paymentservice.exception.PaymentException;
import com.dgp.paymentservice.service.TransactionService;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

@RestController
@RequestMapping("${service.api-uri}/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/report/{cardId}")
    public ResponseEntity<Map<String, String>> getTransactionReport(@PathVariable("cardId") Long cardId,
                                                                    @ApiParam(example = "1984-01-19 07:30:00") @RequestParam String startDate,
                                                                    @ApiParam(example = "1984-01-19 07:30:00") @RequestParam String endDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return ResponseEntity.ok().body(transactionService.getTransactionReport(cardId, simpleDateFormat.parse(startDate), simpleDateFormat.parse(endDate)));
    }


    @PostMapping("transfer")
    public ResponseEntity<TransactionDTO> transfer(@RequestHeader("userId") Long userId, @RequestBody @Validated TransferDTO transferDTO) throws IOException {
        try {
            return ResponseEntity.ok().body(transactionService.transfer(userId, transferDTO));
        } catch (PaymentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
