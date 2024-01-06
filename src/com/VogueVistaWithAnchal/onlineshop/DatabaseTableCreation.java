package com.VogueVistaWithAnchal.onlineshop;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseTableCreation {

	public static void makeTable(Connection con) {
		try (Statement st = con.createStatement()) {

			String batchStatement = "drop database onlineshop ; " + "create database onlineshop ;" + "use onlineshop ; "
					+ "CREATE TABLE logininfo (userid INT AUTO_INCREMENT PRIMARY KEY,password VARCHAR(50) NOT NULL,usertype CHAR(1) NOT NULL CHECK (usertype IN ('A', 'C')));"
					+ "CREATE TABLE admininfo ( adminid INT PRIMARY KEY, name VARCHAR(50) NOT NULL, age INT NOT NULL CHECK (age >= 18 AND age <= 120), email VARCHAR(50) NOT NULL UNIQUE, address VARCHAR(100) NOT NULL, contactnumber VARCHAR(20) NOT NULL CHECK (LENGTH(contactnumber) = 10),FOREIGN KEY (adminid) REFERENCES logininfo(userid) ON DELETE CASCADE);"
					+ "CREATE TABLE custinfo (custid INT PRIMARY KEY,name VARCHAR(50) NOT NULL,age INT NOT NULL CHECK (age >= 18 AND age <= 120),email VARCHAR(50) NOT NULL UNIQUE,address VARCHAR(100) NOT NULL,contactnumber VARCHAR(20) NOT NULL CHECK (LENGTH(contactnumber) = 10),FOREIGN KEY (custid) REFERENCES logininfo(userid) ON DELETE CASCADE);"
					+ "CREATE TABLE products ( productid INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50) NOT NULL, type VARCHAR(50) NOT NULL, quantity INT NOT NULL CHECK (quantity >= 0),  unitprice DECIMAL(10, 2) NOT NULL CHECK (unitprice >= 0));"
					+ "CREATE TABLE bills ( billid int AUTO_INCREMENT PRIMARY KEY, custid INT, productid INT,  FOREIGN KEY (custid) REFERENCES custinfo(custid), FOREIGN KEY (productid) REFERENCES products(productid),  billdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, totalamount DECIMAL(10, 2) NOT NULL CHECK (totalamount >= 0)  );";

			// Execute the batch
			st.executeUpdate(batchStatement);
		} catch (Exception e) {
			System.out.println("Tables not created !!!");
		}
	}
}