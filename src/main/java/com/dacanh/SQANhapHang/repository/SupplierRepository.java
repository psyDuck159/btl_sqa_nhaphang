package com.dacanh.SQANhapHang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dacanh.SQANhapHang.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
	
}
