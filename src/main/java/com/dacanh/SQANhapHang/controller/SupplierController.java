package com.dacanh.SQANhapHang.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dacanh.SQANhapHang.dto.ResponseObject;
import com.dacanh.SQANhapHang.model.Product;
import com.dacanh.SQANhapHang.model.Supplier;
import com.dacanh.SQANhapHang.repository.SupplierRepository;
import com.dacanh.SQANhapHang.service.SupplierService;

@RestController
@RequestMapping("/api")
public class SupplierController {
	@Autowired
	private SupplierRepository supplierRepository;
	@Autowired
	private SupplierService supplierService;

	@GetMapping("/suppliers")
	public ResponseEntity<ResponseObject> getSuppliers(){
		return ResponseEntity.ok().body(supplierService.getSuppliers());
	}
	
	@GetMapping("/supplier/{id}")
	public ResponseEntity<ResponseObject> getSupplier(@PathVariable int id) {
		Optional<Supplier> foundSupplier = supplierRepository.findById(id);
		if (foundSupplier.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject("ok", "Query supplier successfully", foundSupplier)	
			);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseObject("failed", "Cannot find supplier with id="+id, "")	
			);
		} 
	}

	@PostMapping("/supplier/save")
	public ResponseEntity<ResponseObject> saveSupplier(@RequestBody Supplier newSupplier){
		return ResponseEntity.status(HttpStatus.OK).body(supplierService.saveSupplier(newSupplier));
	}
	
	@GetMapping("/productsupplier/product/{id}")
	public ResponseEntity<ResponseObject> getSupplierByProduct(@PathVariable int id) {
		return ResponseEntity.ok()
				.body(supplierService.getSupplierByProduct(id));
	}
}
