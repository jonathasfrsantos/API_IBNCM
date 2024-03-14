package com.jonathasfrsaint.API_IBNCM.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonathasfrsaint.API_IBNCM.entities.Transaction;

public interface TransactionRepository  extends JpaRepository<Transaction, Long>{

}
