package com.VogueVistaWithAnchal.onlineshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * Functions include managing products 
 * (add,delete,view,search) by calling 
 * productsPage() function of products.java
 * 
 * Other functions include adding customers, 
 * removing customers, editing profiles, view 
 * registered customers.
 * 
 * For registering customers, since admin.java 
 * is subclass of Shop.java , registerCustomer() 
 * function of Shop is called by 
 * Shop.registerCustomer(), hence the small use of 
 * inheritance is here as the function need not 
 * be rewritten.
*/

public class Admin extends Main {

	private final Connection con;
	private final Scanner sc;

	private int userid;

	public Admin(Connection con, Scanner sc, int userid) {
		this.con = con;
		this.sc = sc;
		this.userid = userid;
	}

	public void adminPage(Scanner sc) {
		clearConsole();
		System.out.println("+=====================================================+");
		System.out.println("|                 WELCOME TO ADMIN SECTION            |");
		System.out.println("+=====================================================+");

		do {
			System.out.println("*****************************************************");
			System.out.println("| 1 - MANAGE PRODUCTS                                |");
			System.out.println("| 2 - ADD CUSTOMERS                                  |"); // done
			System.out.println("| 3 - REMOVE CUSTOMERS                               |"); // done
			System.out.println("| 4 - EDIT PROFILE                                   |"); // done
			System.out.println("| 5 - VIEW REGISTERED CUSTOMERS                      |"); // done
			System.out.println("| 6 - LOGOUT FROM SYSTEM                             |"); // done
			System.out.println("*****************************************************\n");
			System.out.print("Please enter your choice: ");

			int choice = sc.nextInt();

			switch (choice) {
			case 1 -> manageProducts();
			case 2 -> registerCustomer(con, sc);
			case 3 -> removeCustomer();
			case 4 -> editProfile();
			case 5 -> viewCustomer();
			case 6 -> logoutFromSystem();
			default -> System.out.println("Invalid Choice. Try again.");
			}

		} while (true);

	}

	private void manageProducts() {
		Products pod = new Products(con, sc);
		pod.ProductsPage();
	}

	private void removeCustomer() {
		String query = "DELETE FROM logininfo WHERE usertype = 'C' AND userid = ?" + userid;
		try (PreparedStatement st = con.prepareStatement(query)) {
			st.setInt(1, userid);
			int affectedRows = st.executeUpdate();
			if (affectedRows > 0) {
				System.out.println("Customer Removed Sucessfully !!");
			} else {
				System.out.println("Failed to remove Customer !!");
			}
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
	}

	private void editProfile() {
		do {
			String newvalue = "";
			String query = "";
			System.out.println("\n\n*****************************************************");
			System.out.println("|  1 - EDIT NAME                                     |");
			System.out.println("|  2 - EDIT AGE                                      |");
			System.out.println("|  3 - EDIT EMAIL ID                                 |");
			System.out.println("|  4 - EDIT ADDRESS                                  |");
			System.out.println("|  5 - EDIT CONTACT NUMBER                           |");
			System.out.println("|  6 - CHANGE PASSWORD                               |");
			System.out.println("|  7 - EXIT                                          |");
			System.out.println("*****************************************************");
			System.out.print("Enter choice : ");
			int ch = sc.nextInt();
			switch (ch) {
			case 1 -> {
				System.out.print("Enter new Name : ");
				newvalue = sc.nextLine();
				query = "update admininfo set name='" + newvalue + "' where adminid = " + userid;
			}
			case 2 -> {
				System.out.print("Enter new Age : ");
				int age = sc.nextInt();
				query = "update admininfo set age=" + age + " where adminid = " + userid;
			}
			case 3 -> {
				System.out.print("Enter new Email Id : ");
				newvalue = sc.nextLine();
				query = "update admininfo set email='" + newvalue + "' where adminid = " + userid;
			}
			case 4 -> {
				System.out.print("Enter new Address : ");
				newvalue = sc.nextLine();
				query = "update admininfo set address='" + newvalue + "' where adminid = " + userid;
			}
			case 5 -> {
				System.out.print("Enter new Contact Number : ");
				newvalue = sc.nextLine();
				query = "update admininfo set contactnumber='" + newvalue + "' where adminid = " + userid;
			}
			case 6 -> {
				System.out.print("Enter new password: ");
				newvalue = sc.nextLine();
				query = "update logininfo set password='" + newvalue + "' where userid = " + userid;
			}
			case 7 -> {
				try {
					exit();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			}

			try (Statement st = con.createStatement()) {
				ResultSet rs = st.executeQuery(query);
				if (rs.next()) {
					System.out.println("Updated Profile Successfully !!!");
				} else {
					System.out.println("Not able to update your profile .........");
				}
			} catch (SQLException s) {
				System.out.print(s.getMessage());
			}

		} while (true);

	}

	private void viewCustomer() {
		String query = "select * from custinfo";
		try (Statement st = con.createStatement()) {
			ResultSet rs = st.executeQuery(query);

			System.out.println("REGISTERED CUSTOMERS : ");
			System.out.format(
					"+--------+---------------+-----+--------------------+--------------------+----------------+%n");
			System.out.format(
					"| Custid | Name          | Age | Email              | Address            | Contact Number |%n");
			System.out.format(
					"+--------+---------------+-----+--------------------+--------------------+----------------+%n");

			while (rs.next()) {

				int custid = rs.getInt("");
				String name = rs.getString("");
				int age = rs.getInt("");
				String email = rs.getString("");
				String address = rs.getString("");
				String contact = rs.getString("");

				System.out.printf("| %8d | %15s | %5d | %20s | %20s |%15n", custid, name, age, email, address, contact);
				System.out.format(
						"+--------+---------------+-----+--------------------+--------------------+----------------+%n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}