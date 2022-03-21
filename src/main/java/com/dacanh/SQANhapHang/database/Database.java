package com.dacanh.SQANhapHang.database;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dacanh.SQANhapHang.model.ImportOrder;
import com.dacanh.SQANhapHang.model.ImportedProduct;
import com.dacanh.SQANhapHang.model.Product;
import com.dacanh.SQANhapHang.model.Supplier;
import com.dacanh.SQANhapHang.model.User;
import com.dacanh.SQANhapHang.repository.ImportOrderRepository;
import com.dacanh.SQANhapHang.repository.ImportedProductRepository;
import com.dacanh.SQANhapHang.repository.ProductRepository;
import com.dacanh.SQANhapHang.repository.SupplierRepository;
import com.dacanh.SQANhapHang.repository.UserRepository;


@Configuration
public class Database {
	private static final Logger logger = LoggerFactory.getLogger(Database.class);
	@Bean
	CommandLineRunner initDatabase(ProductRepository productRepository,
			SupplierRepository supplierRepository,
			UserRepository userRepository,
			ImportedProductRepository importedProductRepository,
			ImportOrderRepository importOrderRepository) {
		 
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
//				Supplier supplierA = new Supplier("C", null, null);
//				Supplier supplierB = new Supplier("D", null, null);
//				logger.info("insert data: " + supplierRepository.save(supplierA));
//				logger.info("insert data: " + supplierRepository.save(supplierB));
//				
//				Product productA = new Product("dell", 13000000, "learn", null, null, "good laptop");
//				
//				Product productB = new Product("dell 123", 20000000, "love", null, null, "");
//				logger.info("insert data: " + productRepository.save(productA));
//				logger.info("insert data: " + productRepository.save(productB));
//				
//				User userA = new User("admin", "admin", "admin", "admin");
//				logger.info("insert data: " + userRepository.save(userA));
//				
//				ImportedProduct importedProductA = new ImportedProduct(2, productA.getPrice(), productA);
//				ImportedProduct importedProductB = new ImportedProduct(3, productB.getPrice(), productB);
//				
//				ImportOrder importOrder = new ImportOrder();
//				importOrder.setStaff(userA);
//				importOrder.setSupplier(supplierA);
//				List<ImportedProduct> importedProducts = new ArrayList<ImportedProduct>();
//				importedProductA.setImportOrder(importOrder);
//				importedProductB.setImportOrder(importOrder);
//				importedProducts.add(importedProductA);
//				importedProducts.add(importedProductB);
//				importOrder.setListProduct(importedProducts);
//				importOrder = importOrderRepository.save(importOrder);
//				logger.info("insert data: " + importOrder);
//				logger.info("insert data: " + importedProductRepository.save(importedProductA));
//				logger.info("insert data: " + importedProductRepository.save(importedProductB));

			}
		};
	}
}
