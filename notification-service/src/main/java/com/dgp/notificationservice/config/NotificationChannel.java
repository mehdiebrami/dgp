package com.dgp.notificationservice.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface NotificationChannel {

    String NOTIFICATION_CHANNEL = "notificationChannel";

    @Input(NotificationChannel.NOTIFICATION_CHANNEL)
    SubscribableChannel input();

    @Output(NotificationChannel.NOTIFICATION_CHANNEL)
    MessageChannel output();


}
