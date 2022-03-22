package com.dacanh.SQANhapHang.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dacanh.SQANhapHang.dto.ResponseObject;
import com.dacanh.SQANhapHang.model.Product;
import com.dacanh.SQANhapHang.repository.ProductRepository;
import com.dacanh.SQANhapHang.service.ProductService;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProductService productService;

    // get list products
    @GetMapping("/lists")
    List<Product> getAllProduct(){
        return repository.findAll();
    }

    // get detail product
    @GetMapping("/detail/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable int id){
        Optional<Product> foundProduct = repository.findById(id);
        if(foundProduct.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query product successfully", foundProduct)
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Cannot find product with id = "+id, "")
            );
        }
    }

    //insert product
    @PostMapping("/create")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert product successfully", repository.save(newProduct))
        );
    }

    @PutMapping("/{id}/edit")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable int id){
        Product updatedProduct = repository.findById(id).map(product -> {
            product.setPrice(newProduct.getPrice());
            product.setName(newProduct.getName());
            product.setGuide(newProduct.getGuide());
            product.setDescription(newProduct.getDescription());
            product.setUsageOfProduct(newProduct.getUsageOfProduct());
            product.setImage(newProduct.getImage());
            return repository.save(product);
        }).orElseGet(() -> {
            newProduct.setId(id);
            return repository.save(newProduct);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update product successfully", updatedProduct)
        );
    }

    //delete product
    @DeleteMapping("/{id}/delete")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable int id){
        boolean existed = repository.existsById(id);
        if (existed){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete product successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("false", "Cannot find product to delete", "")
        );
    }
	
	@GetMapping("productsupplier/supplier/{id}")
	public ResponseEntity<ResponseObject> getProductsBySupplier(@PathVariable int id) {
		return ResponseEntity.ok()
				.body(productService.getProductsBySupplier(id));
	}
}
