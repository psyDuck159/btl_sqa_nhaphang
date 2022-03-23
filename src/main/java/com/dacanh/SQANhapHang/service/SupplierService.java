package com.dacanh.SQANhapHang.service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dacanh.SQANhapHang.dao.SupplierDao;
import com.dacanh.SQANhapHang.dto.ResponseObject;
import com.dacanh.SQANhapHang.model.Supplier;
import com.dacanh.SQANhapHang.repository.SupplierRepository;

@Slf4j
@Service
public class SupplierService {
	@Autowired
	private SupplierRepository supplierRepository;
	
	public ResponseObject getSupplierByProduct(int id) {
		SupplierDao supplierDao = new SupplierDao();
		List<Supplier> list = supplierDao.getSuppliersByProduct(id);
		if (list != null)
			return new ResponseObject("ok", "get list supplier of product " + id + " successfully", list);
		return new ResponseObject("failed", "cannot get list supplier of product " + id, "");
	}

	public ResponseObject saveSupplier(Supplier supplier) {
		List<Supplier> foundSupplier = supplierRepository.findByName(supplier.getName().trim());
		if(foundSupplier.size() > 0){
			log.info("Supplier already exits");
			return new ResponseObject("failed", "Supplier already exists", "");
		}
		else{
			log.info("Save Supplier successfully");
			return new ResponseObject("ok", "Save supplier successfully", supplierRepository.save(supplier));
		}
	}
}
