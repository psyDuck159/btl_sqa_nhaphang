package com.dacanh.SQANhapHang.model;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, unique = true, length = 45)
	private String name;
	@Column
	private int price;
	@Column(length = 450)
	private String usageOfProduct;
	@Column(length = 450)
	private String guide;
	@Column(length = 450)
	private String image;
	@Column(length = 1000)
	private String description;
	@JsonIdentityInfo(
			  generator = ObjectIdGenerators.PropertyGenerator.class, 
			  property = "id")
	@ManyToMany(mappedBy = "products")
	private Set<Supplier> suppliers;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<ImportedProduct> listImportedProducts;
	
	public Product() {}

	public Product(String name, int price, String usage, String guide, String image, String description) {
		super();
		this.name = name;
		this.price = price;
		this.usageOfProduct = usage;
		this.guide = guide;
		this.image = image;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getUsageOfProduct() {
		return usageOfProduct;
	}

	public void setUsageOfProduct(String usage) {
		this.usageOfProduct = usage;
	}

	public String getGuide() {
		return guide;
	}

	public void setGuide(String guide) {
		this.guide = guide;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Set<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price
				+ ", usage=" + usageOfProduct + ", guide=" + guide
				+ ", image=" + image + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, guide, id, image, name, price, usageOfProduct);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(description, other.description) && Objects.equals(guide, other.guide) && id == other.id
				&& Objects.equals(image, other.image) && Objects.equals(name, other.name) && price == other.price
				&& Objects.equals(usageOfProduct, other.usageOfProduct);
	}
	
	
}
