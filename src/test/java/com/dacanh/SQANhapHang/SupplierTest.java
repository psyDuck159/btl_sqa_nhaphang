package com.dacanh.SQANhapHang;

import com.dacanh.SQANhapHang.controller.ProductController;
import com.dacanh.SQANhapHang.controller.SupplierController;
import com.dacanh.SQANhapHang.dto.ResponseObject;
import com.dacanh.SQANhapHang.model.Product;
import com.dacanh.SQANhapHang.model.Supplier;
import com.dacanh.SQANhapHang.repository.ProductRepository;
import com.dacanh.SQANhapHang.repository.SupplierRepository;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class SupplierTest extends TestCase {

    @Autowired
    SupplierController supplierController;
    @Autowired
    SupplierRepository supplierRepository;

    @Test
    public void testGetAllSupplier() {
        List<Supplier> list = (List<Supplier>) supplierController.getSuppliers().getBody().getData();
        assertEquals(1, list.size());
    }

    @Test
    public void testGetSupplierByID() {
        Supplier s = (Supplier) supplierController.getSupplier(1).getBody().getData();
        assertEquals("abc", s.getName());
        assertEquals(1, s.getTel());
        assertEquals("a", s.getAddress());
    }

    @Test
    public void testGetSupplierByIDFail() {
        ResponseObject responseObject = supplierController.getSupplier(100).getBody();
        Supplier supplier = (Supplier) responseObject.getData();
        assertNull(supplier);
        assertEquals("Cannot find supplier with id = 100", responseObject.getMessage());
    }

    @Test
    @Transactional
    public void testInsertSupplierSuccess() {
        Supplier supplier = new Supplier("Hoang Van Long", "Ha Noi", "2346345646");
        Supplier result = (Supplier) supplierController.saveSupplier(supplier).getBody().getData();
        assertTrue(result.getId() > 0);
    }

    @Test
    @Transactional
    public void testInsertSupplierNameIsEmptyValue() {
        Supplier supplier = new Supplier("", "Ha Noi", "2346345646");
        Supplier result = (Supplier) supplierController.saveSupplier(supplier).getBody().getData();
        assertTrue(result.getId() <= 0);
    }

    @Test
    @Transactional
    public void testInsertSupplierLengthOfNameIs46() {
        Supplier supplier = new Supplier("5mis1HMTr5W29qzduicGN0OmiCrt9RhtBHu2LSXDsQKda3", "Ha Noi", "2346345646");
        Supplier result = (Supplier) supplierController.saveSupplier(supplier).getBody().getData();
        assertTrue(result.getId() <= 0);
    }

    @Test
    @Transactional
    public void testGetSupplierByProductID(){
        List<Supplier> list = (List<Supplier>) supplierController.getSupplierByProduct(1).getBody().getData();
        assertEquals(1,list.size());
    }
}

