package com.dimasari.olshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dimasari.olshop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
