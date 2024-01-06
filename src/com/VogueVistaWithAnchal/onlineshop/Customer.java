package com.VogueVistaWithAnchal.onlineshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Author : Anchal Singh
 */

/*
 * Database table custinfo accessed for editing profile function
 * 
 * The main functions are viewing products, searching for products, adding and removing 
 * products from the cart, view the cart, and proceeding to the payment function.
 * 
 * Here first initializeProducts() function is called to store all product info in array lists, 
 * so that database need not be accessed everytime, hence Concept of collection framework is used
 * here in form of ArrayList and through ArrayList functions .add(), .get(), .clear()
 * 
 * .add() = to add to ArrayList
 * .get(int i) = to get the element stored at index i in the ArrayList
 * 
 * Proceed to payment function calls payment.java class file and functions like add to cart, 
 * remove from the cart, and view cart call Cart.java. Calling is done via class objects like 
 * customerCart and p.
 * customerCart = object of Cart class p=object of Payment class
 * 
 * Customer.java is also the subclass of Shop.java where it calls the registerCustomer() 
 * function of the Shop.java through Classname.methodname like Shop.registerCustomer()
*/

public class Customer extends Main {

	private final Connection con;
	private final Scanner sc;
	private int userid;

	private ArrayList<Integer> pid = new ArrayList<Integer>();
	private ArrayList<String> name = new ArrayList<String>();
	private ArrayList<String> type = new ArrayList<String>();
	private ArrayList<Integer> qty = new ArrayList<Integer>();
	private ArrayList<Float> price = new ArrayList<Float>();

	public Customer(Connection con, Scanner sc, int userid) {
		this.con = con;
		this.sc = sc;
		this.userid = userid;
	}

	public void customerPage(Scanner sc) {
		clearConsole();
		System.out.println("++====================================================+");
		System.out.println("|            WELCOME TO CUSTOMER SECTION              |");
		System.out.println("+=====================================================+");

		do {
			System.out.println("*********************************************");
			System.out.println("| 1 - VIEW PRODUCTS LIST                    |");
			System.out.println("| 2 - SEARCH A PRODUCT NAMEWISE             |"); // done
			System.out.println("| 3 - SEARCH PRODUCTS TYPEWISE              |"); // done
			System.out.println("| 4 - ADD PRODUCT TO CART                   |");
			System.out.println("| 5 - REMOVE PRODUCT FROM CART              |");
			System.out.println("| 6 - VIEW CART                             |");
			System.out.println("| 7 - PROCEED TO PAYMENT                    |");
			System.out.println("| 8 - EDIT PROFILE                          |"); // done
			System.out.println("| 9 - LOGOUT FROM SYSTEM                    |"); // done
			System.out.println("*********************************************");
			System.out.print("Please enter your choice: ");

			System.out.println("Please enter your choice: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1 -> viewProductsList();
			case 2 -> searchProductNamewise();
			case 3 -> searchProductsTypewise();
			case 4 -> addProductToCart();
			case 5 -> removeProductFromCart();
			case 6 -> viewCart();
			case 7 -> proceedToPayment();
			case 8 -> editProfile();
			case 9 -> logoutFromSystem();
			default -> System.out.println("Invalid Choice. Try again.");
			}

		} while (true);

	}

	public void viewProductsList() {
		String query = "select name,type,unitprice from products   ;";
		try (PreparedStatement sk = con.prepareStatement(query)) {
			ResultSet rs = sk.executeQuery();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void searchProductNamewise() {
		String query = "select name,type,unitprice from products  where name = ? ;";
		try (PreparedStatement sk = con.prepareStatement(query)) {
			ResultSet rs = sk.executeQuery();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void searchProductsTypewise() {
		String query = "select name,type,unitprice from products  where type = ? ;";
		try (PreparedStatement sk = con.prepareStatement(query)) {
			ResultSet rs = sk.executeQuery();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void updateArrayList() throws IOException {
		pid.clear();
		name.clear();
		type.clear();
		qty.clear();
		price.clear();
		initializeProducts();
	}

	private void initializeProducts() {
		// TODO Auto-generated method stub

	}

	public void addProductToCart() {
//		billid | custid | productid | billdate            | totalamount

		String query = "";
		try (PreparedStatement sk = con.prepareStatement(query)) {

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void removeProductFromCart() {
		String query = "";
		try (PreparedStatement sk = con.prepareStatement(query)) {

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void viewCart() {
		String query = "";
		try (PreparedStatement sk = con.prepareStatement(query)) {

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void proceedToPayment() {
		String query = "";
		try (PreparedStatement sk = con.prepareStatement(query)) {

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void editProfile() {
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
				query = "update  custinfo set name='" + newvalue + "' where  custid = " + userid;
			}
			case 2 -> {
				System.out.print("Enter new Age : ");
				int age = sc.nextInt();
				query = "update  custinfo set age=" + age + " where  custid = " + userid;
			}
			case 3 -> {
				System.out.print("Enter new Email Id : ");
				newvalue = sc.nextLine();
				query = "update  custinfo set email='" + newvalue + "' where  custid = " + userid;
			}
			case 4 -> {
				System.out.print("Enter new Address : ");
				newvalue = sc.nextLine();
				query = "update  custinfo set address='" + newvalue + "' where  custid = " + userid;
			}
			case 5 -> {
				System.out.print("Enter new Contact Number : ");
				newvalue = sc.nextLine();
				query = "update  custinfo set contactnumber='" + newvalue + "' where  custid = " + userid;
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

}
