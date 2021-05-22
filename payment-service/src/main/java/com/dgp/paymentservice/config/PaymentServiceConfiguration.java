package com.dgp.paymentservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@EnableBinding(PaymentChannel.class)
@Configuration
public class PaymentServiceConfiguration {
    private final AmqpAdmin amqpAdmin;

    @Value("${spring.rabbitmq.template.default-receive-queue}")
    private String paymentQueue;


    public PaymentServiceConfiguration(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    @PostConstruct
    public void createQueues() {
        amqpAdmin.declareQueue(new Queue(paymentQueue, true));
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new Jdk8Module()).disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
}
