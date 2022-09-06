package com.dimasari.olshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dimasari.olshop.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
