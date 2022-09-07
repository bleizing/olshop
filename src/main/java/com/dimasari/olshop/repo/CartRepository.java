package com.dimasari.olshop.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dimasari.olshop.enumeration.StatusCart;
import com.dimasari.olshop.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	Optional<Cart> findByUserIdAndStatusAndDeletedFalse(Long userId, String status);
	
	default Optional<Cart> findByUserIdAndStatusInCart(Long userId) {
		return findByUserIdAndStatusAndDeletedFalse(userId, StatusCart.CART.getValue());
	}
}
