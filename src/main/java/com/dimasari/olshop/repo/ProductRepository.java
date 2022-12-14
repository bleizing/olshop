package com.dimasari.olshop.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dimasari.olshop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findByIdAndDeletedIsFalse(Long id);

	Page<Product> findByDeletedIsFalse(Pageable pageable);
	Page<Product> findByNameContainingAndDeletedIsFalse(String name, Pageable pageable);
}
