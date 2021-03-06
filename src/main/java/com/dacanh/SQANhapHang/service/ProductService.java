package com.dacanh.SQANhapHang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dacanh.SQANhapHang.dao.ProductDao;
import com.dacanh.SQANhapHang.dao.SupplierDao;
import com.dacanh.SQANhapHang.dto.ResponseObject;
import com.dacanh.SQANhapHang.model.Product;
import com.dacanh.SQANhapHang.model.Supplier;
import com.dacanh.SQANhapHang.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public ResponseObject getProductsBySupplier(int id) {
		ProductDao productDao = new ProductDao();
		List<Product> list = productDao.getProductsBySupplier(id);
		if (list != null)
			return new ResponseObject("ok", "get list product of supplier " + id + " successfully", list);
		return new ResponseObject("failed", "cannot get list sproduct of supplier " + id, "");
	}
}
