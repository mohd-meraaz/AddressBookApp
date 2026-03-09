package com.addressbook.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static DBConnection instance;
	private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/addressbookapp";
    private static final String USER = "root";
    private static final String PASSWORD = "Meraaz@8904";

    private DBConnection() {
    	try {
    		 this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    		System.out.println("Database Connection established sucessfully!!");
    	}
    	catch(SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    public static DBConnection getInstance() {
    	if(instance==null) {
    		instance = new DBConnection();
    	}
    	return instance;
    }
    
    public Connection getConnection() {
    	return connection;
    }
}