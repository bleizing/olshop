package com.dimasari.olshop.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dimasari.olshop.model.TransactionDetail;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long> {
	Optional<List<TransactionDetail>> findByTransactionRefAndDeletedIsFalse(String transactionRef);
}
