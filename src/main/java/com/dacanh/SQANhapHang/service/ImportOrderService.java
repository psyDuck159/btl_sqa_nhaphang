package com.dacanh.SQANhapHang.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dacanh.SQANhapHang.dto.ResponseObject;
import com.dacanh.SQANhapHang.model.ImportOrder;
import com.dacanh.SQANhapHang.model.ImportedProduct;
import com.dacanh.SQANhapHang.model.Product;
import com.dacanh.SQANhapHang.repository.ImportOrderRepository;
import com.dacanh.SQANhapHang.repository.ImportedProductRepository;
import com.dacanh.SQANhapHang.repository.ProductRepository;
import com.dacanh.SQANhapHang.repository.UserRepository;

@Service
public class ImportOrderService {
	@Autowired
	private ImportOrderRepository importOrderRepository;
	@Autowired
	private ImportedProductRepository importedProductRepository;
	@Autowired
	private ProductRepository productRepository;
	
	
	@Transactional
	public ResponseObject insertImportOrder(ImportOrder importOrder) {
		importOrder = importOrderRepository.save(importOrder);
		
		for (ImportedProduct product : importOrder.getListProduct()) {
			product.setImportOrder(importOrder);
			Product p = productRepository.getById(product.getProduct().getId());
			product.setPrice(p.getPrice());
			importedProductRepository.save(product);
			//product.setImportOrder(null);
		}
		
		return new ResponseObject("ok", "Add order successfully", importOrder);
	}
	
	@Transactional(readOnly = true)
	public ResponseObject getImportOrder(int id) {
		ImportOrder importOrder = importOrderRepository.findById(id).orElse(null);
		if(importOrder == null) {
			return new ResponseObject("failed", "Cannot get order " + id, null);
		}
		List<ImportedProduct> importedProducts = importedProductRepository.findImportedProductOf(id);
		for (ImportedProduct ip : importedProducts) {
			Product p = productRepository.findById(ip.getProduct().getId()).orElse(null);
			ip.setProduct(p);
			ip.setImportOrder(null);
		}
		importOrder.setListProduct(importedProducts);
		importOrder.getSupplier().setProducts(new HashSet<>());
		return new ResponseObject("ok", "Get order " + id + " successfully", importOrder);
		
	}
	@Transactional(readOnly = true)
	public ResponseObject getAllImportOrder() {
		List<ImportOrder> importOrders = importOrderRepository.findAll();
		for(ImportOrder iod : importOrders) {
			iod = (ImportOrder) getImportOrder(iod.getId()).getData();
			iod.getSupplier().setProducts(new HashSet<>());
		}
		return new ResponseObject("ok", "Get all import orders", importOrders);
	}
	
}
