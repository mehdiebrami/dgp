package com.dgp.paymentservice.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface PaymentChannel {

    String NOTIFICATION_CHANNEL = "paymentChannel";

    @Input(PaymentChannel.NOTIFICATION_CHANNEL)
    SubscribableChannel input();

    @Output(PaymentChannel.NOTIFICATION_CHANNEL)
    MessageChannel output();


}
