package com.dgp.paymentservice.controller;

import com.dgp.paymentservice.dto.CardInfoDTO;
import com.dgp.paymentservice.exception.PaymentException;
import com.dgp.paymentservice.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${service.api-uri}/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    /**
     * Add a card
     *
     * @param userId      user id
     * @param cardInfoDTO card info to be saved {@link CardInfoDTO}
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> addCard(@RequestHeader(value = "userId") Long userId,
                                          @RequestBody @Validated CardInfoDTO cardInfoDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cardService.addCard(userId, cardInfoDTO));
        } catch (PaymentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> removeCard(@RequestHeader(value = "userId") Long userId,
                                             @PathVariable("id") Long cardId) {
        try {
            cardService.removeCard(userId, cardId);
            return ResponseEntity.ok().body("Deleted");
        } catch (PaymentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * List All cards of a user
     *
     * @param userId user id, which in production development we should use token to get the user id
     * @return list of {@link CardInfoDTO}
     */
    @GetMapping
    public List<CardInfoDTO> getCards(@RequestHeader(value = "userId") Long userId) {
        return cardService.getUserCards(userId);
    }
}
