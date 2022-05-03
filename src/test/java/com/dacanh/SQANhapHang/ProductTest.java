package com.dacanh.SQANhapHang;
import com.dacanh.SQANhapHang.controller.ProductController;
import com.dacanh.SQANhapHang.dto.ResponseObject;
import com.dacanh.SQANhapHang.model.Product;
import com.dacanh.SQANhapHang.repository.ProductRepository;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotEquals;

@SpringBootTest
public class ProductTest extends TestCase{

    @Autowired
    ProductController productController;
    @Autowired
    ProductRepository productRepository;

    @Test
    public void testGetAllProduct(){
        List<Product> list = productController.getAllProduct();
        assertEquals(1, list.size());
    }

    @Test
    public void testGetProductByID(){
       Product p = (Product) productController.findById(49).getBody().getData();
        assertEquals("abc", p.getName());
        assertEquals(1, p.getPrice());
        assertEquals("a", p.getDescription());

    }

    @Test
    public void testGetProductByIDFail(){
        ResponseObject responseObject = productController.findById(100).getBody();
        Product product = (Product) responseObject.getData();
        assertNull(product);
        assertEquals("Cannot find product with id = 100", responseObject.getMessage());
    }

    @Test
    @Transactional
    public void testInsertProductSuccess(){
        Product product = new Product("sp 1", 123, "usage", "abc", "def", "ghi");
        Product result = (Product) productController.insertProduct(product).getBody().getData();
        assertTrue(result.getId()>0);
    }

    @Test
    @Transactional
    public void testInsertProductNameIsEmptyValue(){
        Product product = new Product("", 123, "usage", "abc", "def", "ghi");
        Product result = (Product) productController.insertProduct(product).getBody().getData();
        assertTrue(result.getId()<=0);
    }

    @Test
    @Transactional
    public void testInsertProductPriceIs0(){
        Product product = new Product("abc", 0, "usage", "abc", "def", "ghi");
        Product result = (Product) productController.insertProduct(product).getBody().getData();
        assertTrue(result.getId()<=0);
    }

    @Test
    @Transactional
    public void testInsertProductPriceIsNegativeValue(){
        Product product = new Product("abc", -5, "usage", "abc", "def", "ghi");
        Product result = (Product) productController.insertProduct(product).getBody().getData();
        assertTrue(result.getId()<=0);
    }

    @Test
    @Transactional
    public void testInsertProductLengthOfUsageIs451(){
        Product product = new Product("abcd", 12, "1cYLdU0kpCdpCeKDdh0wC5lzp8nt67A1KCa6NcUmJDc1M15lwkE1rMrkD6rVQZQ0ZxgwMx7QMTNjoxtg85jkY5xQmG6tgQDsaHlmUdJvbFeODa3sYHLb9s77Ow7wirxfITjC5Bs976nbHi1J48vqvqcwUiNBPiBPZpFvdRypgoVLCcn0WP739V6WDYmxZ7c3yZWw1KOePxlGgOl1Hlt6Ad6kHtrGKkpkPikKYKDCy1h9tdDoVys6gvpNRXEyyK6uNDIreNVHj6Vt3BFoUSBWRusR0FqTNHPHBOvcbquXk3plpm7X8Gvp5SMlm0fu84ZKanLSGArG2fIW7GYkQgYo1hSYEjWQ13e6ckgwe8Od0CwW7GJA08NKzjxp7njG9gbYx9FR3Pkv5xqjyBDmVEtX7v9vGHkNF0wkVfyw1v7qmBecGEsAimRoam4vzu2Gsj9HS8l", "abc", "def", "ghi");
        Product result = (Product) productController.insertProduct(product).getBody().getData();
        assertTrue(result.getId()<=0);
    }

    @Test
    @Transactional
    public void testUpdateProductSuccess(){
        Product product = new Product("name", 1234, "a", "b", "c", "d");
        Product result = (Product) productController.updateProduct(product, 49).getBody().getData();
        assertEquals(product.getName(), result.getName());
    }

    @Test
    @Transactional
    public void testUpdatePriceIs0(){
        Product p = (Product) productController.findById(49).getBody().getData();
        Product product = new Product("name", 0, "a", "b", "c", "d");
        Product result = (Product) productController.updateProduct(product, 49).getBody().getData();
        assertEquals(p.getPrice(), result.getPrice());
    }

    @Test
    @Transactional
    public void testUpdatePriceIsNegativeValue(){
        Product p = (Product) productController.findById(49).getBody().getData();
        Product product = new Product("name", -5, "a", "b", "c", "d");
        Product result = (Product) productController.updateProduct(product, 49).getBody().getData();
        assertEquals(p.getPrice(), result.getPrice());
    }

    @Test
    @Transactional
    public void testDeletedProductSuccess(){
        String mess = productController.deleteProduct(49).getBody().getMessage();
        assertEquals("Delete product successfully", mess);
    }

    @Test
    @Transactional
    public void testDeletedProductFail(){
        String mess = productController.deleteProduct(50).getBody().getMessage();
        assertEquals("Cannot find product to delete", mess);
    }
}
