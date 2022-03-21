package com.dacanh.SQANhapHang.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dacanh.SQANhapHang.model.ImportedProduct;

public interface ImportedProductRepository extends JpaRepository<ImportedProduct, Integer> {
	@Query(value = "SELECT ip "
			+ "FROM ImportedProduct AS ip "
			+ "WHERE ip.importOrder.id = :orderid", nativeQuery = false)
	List<ImportedProduct> findImportedProductOf(@Param("orderid") int orderid);
	
}
