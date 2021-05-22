package com.dgp.paymentservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(indexes = {@Index(name = "transaction_index", columnList = "card_id, created")})
@Data
@FieldNameConstants
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Card card;
    private String destNumber;
    private Long amount;
    private int bankStatus;
    private int smsStatus;
    private Date created;
    @Version
    @JsonIgnore
    private Integer version;

}
