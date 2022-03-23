package com.dacanh.SQANhapHang;

import com.dacanh.SQANhapHang.model.Product;
import com.dacanh.SQANhapHang.model.Role;
import com.dacanh.SQANhapHang.model.Supplier;
import com.dacanh.SQANhapHang.model.User;
import com.dacanh.SQANhapHang.service.SupplierService;
import com.dacanh.SQANhapHang.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication()
public class SqaNhapHangApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqaNhapHangApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService, SupplierService supplierService){
		return args -> {
//			userService.saveRole(new Role(null, "ROLE_USER"));
//			userService.saveRole(new Role(null, "ROLE_MANAGER"));
//			userService.saveRole(new Role(null, "ROLE_ADMIN"));
//			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
//
//			userService.saveUser(new User(null,"John Travolta", "john","1234", new ArrayList<>()));
//			userService.saveUser(new User(null,"Will Smith", "will","1234", new ArrayList<>()));
//			userService.saveUser(new User(null,"Jim Carry", "jim","1234", new ArrayList<>()));
//			userService.saveUser(new User(null,"Arnold Schwarzenegger", "arnold","1234", new ArrayList<>()));
//
//			userService.addRoleToUser("john", "ROLE_USER");
//			userService.addRoleToUser("john", "ROLE_MANAGER");
//			userService.addRoleToUser("will", "ROLE_MANAGER");
//			userService.addRoleToUser("jim", "ROLE_ADMIN");
//			userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
//			userService.addRoleToUser("arnold", "ROLE_USER");
//			userService.addRoleToUser("arnold", "ROLE_ADMIN");
//
//			Set<Product> products = new HashSet<>();
//			Product productA = new Product("sua rua mat", 235, "cach dung","huong dan su dung","hinh anh","mo ta");
//			Product productB = new Product("kem duong da", 235, "cach dung","huong dan su dung","hinh anh","mo ta");
//			products.add(productA);
//			products.add(productB);
//			Supplier supplierA = new Supplier("Tuan","quang trung","254563456");
//			supplierA.setProducts(products);
//			supplierService.saveSupplier(supplierA);
		};
	}
}
