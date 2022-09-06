package com.dimasari.olshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dimasari.olshop.model.CartUserDetail;

@Repository
public interface CartUserDetailRepository extends JpaRepository<CartUserDetail, Long> {

}