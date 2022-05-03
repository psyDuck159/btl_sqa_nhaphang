package com.dacanh.SQANhapHang.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	public static Connection con = null;
	public DAO(){
	       if(con == null){
	           String dbUrl = "jdbc:mysql://localhost:3306/sqa_nhaphang?autoReconnect=true&useSSL=false";
	           String dbClass = "com.mysql.cj.jdbc.Driver";
	 
	           try {
	               Class.forName(dbClass);
	               con = DriverManager.getConnection (dbUrl, "root",
						   "CNPM@2021");
	           }catch(Exception e) {
	               e.printStackTrace();
	           }
	       }
	   }

}
