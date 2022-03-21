package com.dacanh.SQANhapHang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dacanh.SQANhapHang.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findByName(String productName);
}
