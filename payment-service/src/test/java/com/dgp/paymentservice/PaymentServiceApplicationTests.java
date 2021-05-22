package com.dgp.paymentservice;

import com.dgp.paymentservice.api.FirstBankService;
import com.dgp.paymentservice.api.FirstFeignService;
import com.dgp.paymentservice.dto.CardInfoDTO;
import com.dgp.paymentservice.dto.TransactionDTO;
import com.dgp.paymentservice.dto.TransferDTO;
import com.dgp.paymentservice.exception.PaymentException;
import com.dgp.paymentservice.model.Card;
import com.dgp.paymentservice.model.Transaction;
import com.dgp.paymentservice.model.User;
import com.dgp.paymentservice.repository.CardRepository;
import com.dgp.paymentservice.repository.UserRepository;
import com.dgp.paymentservice.service.CardService;
import com.dgp.paymentservice.service.MessageProducer;
import com.dgp.paymentservice.service.TransactionService;
import com.dgp.paymentservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceApplicationTests {

    @Mock
    private CardRepository cardRepository;
    @InjectMocks
    private CardService cardService;
    @InjectMocks
    private FirstBankService firstBankService;
    @Mock
    private FirstFeignService firstFeignService;
    @InjectMocks
    private MessageProducer messageProducer;
    @InjectMocks
    private TransactionService transactionService;
    @Mock
    private UserService userService;
    @Spy
    @Autowired
    private ModelMapper modelMapper;
    private User user;
    private Card card;
    private CardInfoDTO cardInfoDTO;
    private TransferDTO transferDTO;
    private TransactionDTO transactionDTO;

    @Before
    public void setup() {

        user = new User();
        user.setId(1L);
        user.setUsername("testUSer1");

        card = new Card();
        card.setId(1L);
        card.setNumber("6037-1111-1111-1111");
        card.setCvv2("123");
        card.setExpireDate("0505");
        cardInfoDTO = modelMapper.map(card, CardInfoDTO.class);

        transferDTO = new TransferDTO();
        transferDTO.setAmount(5000L);
        transferDTO.setCvv2("123");
        transferDTO.setExpDate("20220502");
        transferDTO.setSource("6037-1111-1111-1111");
        transferDTO.setDest("6037-1111-1111-2222");
        transferDTO.setPin("123");


        transactionDTO= new TransactionDTO();
        transactionDTO.setAmount(5000L);
        transactionDTO.setBankStatus(200);
        transactionDTO.setSourceNumber("6037-1111-1111-1111");
        transactionDTO.setDestNumber("6037-1111-1111-2222");
        transactionDTO.setCreated(new Date());
        transactionDTO.setId(1l);

    }

    public PaymentServiceApplicationTests() {
    }


    @Test
    public void addServiceTest() throws PaymentException {
        when(userService.findUser(any())).thenReturn(Optional.of(user));
        when(cardRepository.save(any(Card.class))).thenReturn(card);
        assertThat(cardService.addCard(1L, cardInfoDTO).getId()).isEqualTo(1L);
    }

    @Test
    public void transactionServiceTest() throws PaymentException, IOException {
//        when(firstFeignService.transfer(any())).thenReturn(ResponseEntity.status(HttpStatus.OK).body(""));
//        when(firstBankService.transfer(any())).thenReturn(ResponseEntity.status(HttpStatus.OK).body(""));
//        when(userService.findUser(any())).thenReturn(Optional.of(user));
//        when(cardService.findCard(any(),any())).thenReturn(Optional.of(card));
//
//        assertThat(transactionService.transfer(1L, transferDTO).getId()).isNotNull();

    }



}
