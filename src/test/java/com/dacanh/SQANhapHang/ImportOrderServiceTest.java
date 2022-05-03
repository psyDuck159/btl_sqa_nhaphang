package com.dacanh.SQANhapHang;

import com.dacanh.SQANhapHang.dto.ResponseObject;
import com.dacanh.SQANhapHang.model.*;
import com.dacanh.SQANhapHang.repository.*;
import com.dacanh.SQANhapHang.service.ImportOrderService;
import com.dacanh.SQANhapHang.service.UserService;
import junit.framework.TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

//@RunWith(SpringRunner.class)
//@RunWith(JUnitPlatform.class)
@SpringBootTest()
public class ImportOrderServiceTest extends TestCase {
    @Autowired
    ImportOrderService importOrderService;
    @Autowired
    ImportedProductRepository importedProductRepository;
    @Autowired
    ImportOrderRepository importOrderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void testGetImportOrderById() {
        ResponseObject ro = importOrderService.getImportOrder(10);
        ImportOrder iod = (ImportOrder) ro.getData();
        Assertions.assertEquals(1, iod.getListProduct().size());
        Assertions.assertEquals(5, iod.getStaff().getId(), "wrong staff");
        Assertions.assertEquals(2, iod.getSupplier().getId(), "wrong supplier");
    }

    @Test
    public void testGetImportOrderByIdNotFound() {
        int id = 100;
        ResponseObject ro = importOrderService.getImportOrder(id);
        ImportOrder iod = (ImportOrder) ro.getData();
        Assertions.assertEquals(null, iod);
        Assertions.assertEquals("Cannot get order " + id, ro.getMessage());
    }

    @Test
    @Transactional
    @DisplayName("insert normal import order")
    public void testInsertImportOrderSuccess() {
        Product product1 = productRepository.getById(1);
        ImportedProduct importedProduct1 = new ImportedProduct(2, product1.getPrice(), product1);

        ImportOrder importOrder = new ImportOrder();

        List<ImportedProduct> importedProducts = new ArrayList<>();
        importedProducts.add(importedProduct1);
        importOrder.setListProduct(importedProducts);

        Supplier supplier = supplierRepository.getById(1);
        importOrder.setSupplier(supplier);

        User staff = userRepository.getById(5);
        importOrder.setStaff(staff);

        ResponseObject responseObject = importOrderService.insertImportOrder(importOrder);
        ImportOrder ipod = (ImportOrder) responseObject.getData();

        Assertions.assertTrue(ipod.getId() > 0 );
        Assertions.assertEquals(1,ipod.getListProduct().size());
    }
    @Test
    @Transactional
    public void testInsertImportOrderNonPositiveQuantity() {
        Product product1 = productRepository.getById(1);
        ImportedProduct importedProduct1 = new ImportedProduct(0, product1.getPrice(), product1);

        ImportOrder importOrder = new ImportOrder();
        List<ImportedProduct> importedProducts = new ArrayList<>();
        importedProducts.add(importedProduct1);

        importOrder.setListProduct(importedProducts);

        Supplier supplier = supplierRepository.getById(1);
        importOrder.setSupplier(supplier);

        User staff = userRepository.getById(5);
        importOrder.setStaff(staff);

        ResponseObject responseObject = importOrderService.insertImportOrder(importOrder);
        ImportOrder ipod = (ImportOrder) responseObject.getData();
        System.out.println(ipod.getId());
        Assertions.assertFalse(ipod.getId() > 0 );
    }
    @Test
    @Transactional
    public void testInsertImportOrderNullStaff() {
        Product product1 = productRepository.getById(1);
        ImportedProduct importedProduct1 = new ImportedProduct(10, product1.getPrice(), product1);

        ImportOrder importOrder = new ImportOrder();

        List<ImportedProduct> importedProducts = new ArrayList<>();
        importedProducts.add(importedProduct1);
        importOrder.setListProduct(importedProducts);

        Supplier supplier = supplierRepository.getById(1);
        importOrder.setSupplier(supplier);

        ResponseObject responseObject = importOrderService.insertImportOrder(importOrder);
        ImportOrder ipod = (ImportOrder) responseObject.getData();
        System.out.println(ipod.getId());
        Assertions.assertFalse(ipod.getId() > 0 );
    }

    @Test
    @Transactional
    public void testInsertImportOrderNullSupplier() {
        Product product1 = productRepository.getById(1);
        ImportedProduct importedProduct1 = new ImportedProduct(10, product1.getPrice(), product1);

        ImportOrder importOrder = new ImportOrder();

        List<ImportedProduct> importedProducts = new ArrayList<>();
        importedProducts.add(importedProduct1);
        importOrder.setListProduct(importedProducts);

        User staff = userRepository.getById(5);
        importOrder.setStaff(staff);

        ResponseObject responseObject = importOrderService.insertImportOrder(importOrder);
        ImportOrder ipod = (ImportOrder) responseObject.getData();
        System.out.println(ipod.getId());
        Assertions.assertFalse(ipod.getId() > 0 );
    }

    @Test
    @Transactional
    public void testInsertImportOrderEmptyCart() {
        ImportOrder importOrder = new ImportOrder();
        List<ImportedProduct> importedProducts = new ArrayList<>();

        importOrder.setListProduct(importedProducts);

        Supplier supplier = supplierRepository.getById(1);
        importOrder.setSupplier(supplier);

        User staff = userRepository.getById(5);
        importOrder.setStaff(staff);

        ResponseObject responseObject = importOrderService.insertImportOrder(importOrder);
        ImportOrder ipod = (ImportOrder) responseObject.getData();
        System.out.println(ipod.getId());
        Assertions.assertFalse(ipod.getId() > 0 );
    }
}
