package com.dacanh.SQANhapHang.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dacanh.SQANhapHang.model.Supplier;

public class SupplierDao extends DAO {
	public SupplierDao() {
		super();
	}
	
	public List<Supplier> getSuppliersByProduct(int productid) {
		List<Supplier> list = new ArrayList<Supplier>();
		String query = "SELECT * "
				+ "FROM supplier AS sp INNER JOIN productsupplier AS pdsp ON sp.id = pdsp.supplier_id "
				+ "WHERE pdsp.product_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, productid);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Supplier supplier = new Supplier(rs.getString("name"), rs.getString("address"), rs.getString("tel"));
				supplier.setId(rs.getInt("id"));
				list.add(supplier);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
