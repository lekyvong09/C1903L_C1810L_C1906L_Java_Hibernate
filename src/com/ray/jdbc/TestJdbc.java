package com.ray.jdbc;

import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
		String user = "root";
		String pass = "ab123456..";
		
		try {
			System.out.println("Connect to database: " + jdbcUrl);
			
			DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("Connection successful");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
