package com.dacanh.SQANhapHang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dacanh.SQANhapHang.model.ImportOrder;

public interface ImportOrderRepository extends JpaRepository<ImportOrder, Integer> {
	@Query(value = "SELECT iod.id, iod.code, iod.date, iod.staff_id, iod" +
			".supplier_id FROM importorder AS iod", nativeQuery = true)
	public List<ImportOrder> findAll();
}
