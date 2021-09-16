package com.ray.jdbc;

import java.sql.DriverManager;

public class TestJdbcMSSQL {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:sqlserver://localhost:1433;database=hb_student_tracker;encrypt=false;trustServerCertificate=false;loginTimeout=30;";
		String user = "sa";
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
