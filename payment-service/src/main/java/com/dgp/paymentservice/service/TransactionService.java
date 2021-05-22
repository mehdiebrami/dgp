package com.dgp.paymentservice.service;

import com.dgp.paymentservice.api.BankServiceFactory;
import com.dgp.paymentservice.dto.TransactionDTO;
import com.dgp.paymentservice.dto.TransferDTO;
import com.dgp.paymentservice.exception.PaymentException;
import com.dgp.paymentservice.model.Card;
import com.dgp.paymentservice.model.SmsMessage;
import com.dgp.paymentservice.model.SmsStatusMessage;
import com.dgp.paymentservice.model.Transaction;
import com.dgp.paymentservice.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CardService cardService;
    private final ModelMapper modelMapper;
    private final MessageProducer messageProducer;
    private final BankServiceFactory bankServiceFactory;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, CardService cardService, ModelMapper modelMapper, MessageProducer messageProducer, BankServiceFactory bankServiceFactory) {
        this.transactionRepository = transactionRepository;
        this.cardService = cardService;
        this.modelMapper = modelMapper;
        this.messageProducer = messageProducer;
        this.bankServiceFactory = bankServiceFactory;
    }

    @Transactional
    public TransactionDTO transfer(Long userId, TransferDTO transferDTO) throws PaymentException, IOException {
        Optional<Card> cardOptional = cardService.findCard(userId, transferDTO.getSource());
        if (cardOptional.isEmpty())
            throw new PaymentException("the source card is not in database");

        Transaction transaction = new Transaction();
        transaction.setCard(cardOptional.get());
        transaction.setAmount(transferDTO.getAmount());
        transaction.setCreated(new Date());
        transaction.setDestNumber(transferDTO.getDest());
        Transaction savedTransaction = transactionRepository.save(transaction);

        ResponseEntity result = bankServiceFactory.getBAnkServiceProvider(transferDTO.getSource()).transfer(transferDTO);
        if (result.getStatusCode().equals(HttpStatus.OK)) {
            SmsMessage smsMessage = new SmsMessage(savedTransaction.getId(), "Bank transfer succeed.", "093535354");
            messageProducer.send(smsMessage);
            transaction.setBankStatus(HttpStatus.OK.value());
        }
        transactionRepository.save(transaction);
        TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);
        transactionDTO.setSourceNumber(transaction.getCard().getNumber());
        return transactionDTO;
    }


    public Map<String, String> getTransactionReport(Long cardId, Date startDate, Date endDate) {
        return Arrays.stream(transactionRepository.getCountPerBankStatus(cardId, startDate, endDate))
                .collect(Collectors.toMap(e -> e[0].equals("200") ? "Successful" : "Failed", e -> e[1]));
    }


    public void setTransactionSmsStatus(SmsStatusMessage smsStatusMessage) throws PaymentException {
        Transaction transaction = transactionRepository.findById(smsStatusMessage.getTransactionId()).orElseThrow(() -> new PaymentException("transaction not found"));
        transaction.setSmsStatus(smsStatusMessage.getStatus());
        transactionRepository.save(transaction);
    }
}
