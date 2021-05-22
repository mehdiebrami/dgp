package com.dgp.paymentservice.repository;

import com.dgp.paymentservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    @Query("SELECT t.bankStatus  as status, count(t) as statusCount FROM Transaction t where t.card.id = :cardId and t.created between :startDate and :endDate group by t.bankStatus")
    String[][] getCountPerBankStatus(@Param("cardId") Long cardId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);


}
