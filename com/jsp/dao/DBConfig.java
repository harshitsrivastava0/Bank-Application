package com.jsp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {

	public static Connection createConnection() {

		String url = "jdbc:postgresql://localhost:5432/bankapp";
		String user = "postgres";
		String password = "root";
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bankapp", "postgres", "root");

			return DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}