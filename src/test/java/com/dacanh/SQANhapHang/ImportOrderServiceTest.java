package com.dacanh.SQANhapHang;

import com.dacanh.SQANhapHang.dto.ResponseObject;
import com.dacanh.SQANhapHang.model.ImportOrder;
import com.dacanh.SQANhapHang.model.ImportedProduct;
import com.dacanh.SQANhapHang.model.Product;
import com.dacanh.SQANhapHang.repository.ImportOrderRepository;
import com.dacanh.SQANhapHang.repository.ImportedProductRepository;
import com.dacanh.SQANhapHang.repository.ProductRepository;
import com.dacanh.SQANhapHang.repository.SupplierRepository;
import com.dacanh.SQANhapHang.service.ImportOrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig()
@SpringBootTest
public class ImportOrderServiceTest {
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

    @Test
    private void testGetImportOrder() {
        ResponseObject ro = importOrderService.getImportOrder(15);
        ImportOrder iod = (ImportOrder) ro.getData();
    }

    @Test
    @Transactional
    @DisplayName("insert normal import order")
    public void testInsertImportOrder() {
        Product product1 = productRepository.getById(1);
        Product product2 = productRepository.getById(2);
        Product product3 = productRepository.getById(3);
        ImportedProduct importedProduct1 = new ImportedProduct(2, 12, product1);
        ImportedProduct importedProduct2 = new ImportedProduct(10, 12, product2);
        ImportedProduct importedProduct3 = new ImportedProduct(4, 12, product3);
        ImportOrder importOrder = new ImportOrder();
        List<ImportedProduct> importedProducts = new ArrayList<>();
        importedProducts.add(importedProduct1);
        importedProducts.add(importedProduct2);
        importedProducts.add(importedProduct3);
        importOrder.setListProduct(importedProducts);

        ResponseObject responseObject = importOrderService.insertImportOrder(importOrder);
        ImportOrder ipod = (ImportOrder) responseObject.getData();

        assertEquals(3,ipod.getListProduct().size(), "order do not have enough product");
    }
}
