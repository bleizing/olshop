package com.dimasari.olshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dimasari.olshop.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
