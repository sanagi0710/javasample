package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.LoginUser;

public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {

	LoginUser findByUsername(String username);

}
