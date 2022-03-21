package com.dacanh.SQANhapHang.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dacanh.SQANhapHang.model.Product;

public class ProductDao extends DAO {
	public ProductDao() {
		super();
	}
	
	public List<Product> getProductsBySupplier(int supplierid) {
		List<Product> list = new ArrayList<Product>();
		String query = "SELECT * "
				+ "FROM product AS pd INNER JOIN productsupplier AS pdsp ON pd.id = pdsp.product_id "
				+ "WHERE pdsp.supplier_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, supplierid);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				//Product(String name, int price, String usage, String guide, String image, String description)
				Product product = new Product(
						rs.getString("name"), 
						rs.getInt("price"),
						rs.getString("usageOfProduct"),
						rs.getString("guide"),
						rs.getString("image"),
						rs.getString("description"));
				product.setId(rs.getInt("id"));
				list.add(product);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
