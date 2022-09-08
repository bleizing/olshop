package com.dimasari.olshop.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dimasari.olshop.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByIdAndDeletedIsFalse(Long id);
	Optional<List<User>> findByDeletedIsFalse();
}
