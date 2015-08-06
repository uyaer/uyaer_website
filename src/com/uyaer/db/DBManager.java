package com.uyaer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;

public class DBManager {

	private static final String URL = "jdbc:mysql://rdsqq2eimqq2eim.mysql.rds.aliyuncs.com/ry774i3ol52e8559";
	private static final String USER = "ry774i3ol52e8559";
	private static final String PWD = "pw286123468";

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(URL, USER, PWD);
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ResultSet query(String sql) {
		
		return null;
	}
}
