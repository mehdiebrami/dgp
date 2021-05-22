package com.dgp.paymentservice.service;


import com.dgp.paymentservice.exception.PaymentException;
import com.dgp.paymentservice.model.SmsStatusMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@Log4j2
public class MessageConsumer {

    private final TransactionService transactionService;

    public MessageConsumer(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void handleMessage(SmsStatusMessage message) throws PaymentException {
        log.info("Received a sms status message! for transaction id: " + message.getTransactionId());
        transactionService.setTransactionSmsStatus(message);
    }

}
