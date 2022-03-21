package com.dacanh.SQANhapHang.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dacanh.SQANhapHang.dto.ResponseObject;
import com.dacanh.SQANhapHang.model.ImportOrder;
import com.dacanh.SQANhapHang.repository.ImportOrderRepository;
import com.dacanh.SQANhapHang.service.ImportOrderService;

@RestController
@EnableTransactionManagement
public class ImportOrderController {
	@Autowired
	private ImportOrderService importOrderService;

	@Autowired
	private ImportOrderRepository importOrderRepository;

	@PostMapping("/order")
	@Transactional
	ResponseEntity<ResponseObject> insertOrder(@RequestBody ImportOrder order) {

		return ResponseEntity.ok().body(importOrderService.insertImportOrder(order));
	}

	@GetMapping("/order/{id}")
	ResponseEntity<ResponseObject> getOrder(@PathVariable int id) {
		return ResponseEntity.ok()
				.body(importOrderService.getImportOrder(id));
	}
	
	@GetMapping("/orders")
	ResponseEntity<ResponseObject> getOrder() {
		return ResponseEntity.ok()
				.body(importOrderService.getAllImportOrder());
	}
}
