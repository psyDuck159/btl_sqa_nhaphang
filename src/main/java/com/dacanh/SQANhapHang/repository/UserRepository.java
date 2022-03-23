package com.dacanh.SQANhapHang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dacanh.SQANhapHang.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
