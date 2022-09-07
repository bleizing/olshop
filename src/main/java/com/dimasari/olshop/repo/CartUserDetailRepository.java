package com.dimasari.olshop.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dimasari.olshop.model.CartUserDetail;

@Repository
public interface CartUserDetailRepository extends JpaRepository<CartUserDetail, Long> {
	Optional<CartUserDetail> findByCartIdAndProductIdAndDeletedFalse(Long cartId, Long productId);
	Optional<List<CartUserDetail>> findByCartIdAndDeletedFalse(Long cartId);
}
