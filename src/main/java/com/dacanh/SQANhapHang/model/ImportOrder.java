package com.dacanh.SQANhapHang.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "importorder")
public class ImportOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = false, length = 45)
	private String code;
	@CreationTimestamp
	@JsonFormat(shape = Shape.STRING, pattern = "HH:mm dd-MM-yyyy")
	private Timestamp date;
	@ManyToOne
	@JoinColumn(name = "staff_id")
	private User staff;
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;
	
	@OneToMany(mappedBy = "importOrder")
	private List<ImportedProduct> listProduct;
	
	@Transient
	private int total;
	public int getTotal() {
		int total = 0;
		for (ImportedProduct p : this.listProduct) {
			total += p.getPrice() * p.getQuantity();
		}
		return total;
	}
	
	public ImportOrder() {
		this.code = UUID.randomUUID().toString();
	}

	public ImportOrder(String code, Timestamp date, User staff, Supplier supplier, List<ImportedProduct> listProduct) {
		super();
		this.code = code;
		this.date = date;
		this.staff = staff;
		this.supplier = supplier;
		this.listProduct = listProduct;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public User getStaff() {
		return staff;
	}

	public void setStaff(User staff) {
		this.staff = staff;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<ImportedProduct> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<ImportedProduct> listProduct) {
		this.listProduct = listProduct;
	}
	
	
	
	
}
