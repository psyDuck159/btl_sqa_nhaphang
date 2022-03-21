package com.dacanh.SQANhapHang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "importedproduct")
public class ImportedProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private int quantity;
	private int price;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "importorder_id")
	private ImportOrder importOrder;
	
	public ImportedProduct() {
	}

	public ImportedProduct(int quantity, int price, Product product) {
		super();
		this.quantity = quantity;
		this.price = price;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ImportOrder getImportOrder() {
		return importOrder;
	}

	public void setImportOrder(ImportOrder importOrder) {
		this.importOrder = importOrder;
	}
	
	
}
