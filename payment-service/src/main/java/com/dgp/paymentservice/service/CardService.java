package com.dgp.paymentservice.service;

import com.dgp.paymentservice.dto.CardInfoDTO;
import com.dgp.paymentservice.exception.PaymentException;
import com.dgp.paymentservice.model.Card;
import com.dgp.paymentservice.model.User;
import com.dgp.paymentservice.repository.CardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public CardService(CardRepository cardRepository, UserService userService, ModelMapper modelMapper) {
        this.cardRepository = cardRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public CardInfoDTO addCard(Long userId, CardInfoDTO cardInfoDTO) throws PaymentException {
        User user = userService.findUser(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Optional<Card> any = user.getCards().stream()
                .filter(card -> card.getNumber().equals(cardInfoDTO.getNumber())).findAny();
        if (any.isPresent())
            throw new PaymentException("This card already existed in database");

        Card card = modelMapper.map(cardInfoDTO, Card.class);
        card.setUser(user);
        return modelMapper.map(cardRepository.save(card), CardInfoDTO.class);
    }


    public void removeCard(Long userId, Long cardId) throws PaymentException {
        Card card = cardRepository.findCardByUserIdAndId(userId, cardId).orElseThrow(() -> new PaymentException(String.format("The card: %d doesn't exist for the user: %d", cardId, userId)));
        cardRepository.delete(card);
    }

    public Optional<Card> findCard(Long userId, String cardNumber) {
        return cardRepository.findCardByUserIdAndNumber(userId, cardNumber);
    }

    public Optional<Card> findCardById(Long cardId) {
        return cardRepository.findById(cardId);
    }

    public List<CardInfoDTO> getUserCards(Long userId) {
        return cardRepository.findAllByUserId(userId)
                .stream()
                .map(card -> modelMapper.map(card, CardInfoDTO.class))
                .collect(Collectors.toList());
    }
}
