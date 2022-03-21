package com.dacanh.SQANhapHang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dacanh.SQANhapHang.dao.SupplierDao;
import com.dacanh.SQANhapHang.dto.ResponseObject;
import com.dacanh.SQANhapHang.model.Supplier;
import com.dacanh.SQANhapHang.repository.SupplierRepository;

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
}
