package com.dimasari.olshop.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dimasari.olshop.enumeration.StatusTransaction;
import com.dimasari.olshop.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	Optional<Transaction> findByUserIdAndTransactionRefAndStatusAndDeletedIsFalse(Long userId, String transactionRef, String status);
	
	default Optional<Transaction> findByUserIdAndTransactionRefAndStatusInWaiting(Long userId, String transactionRef) {
		return findByUserIdAndTransactionRefAndStatusAndDeletedIsFalse(userId, transactionRef, StatusTransaction.WAITING.getValue());
	}
}
