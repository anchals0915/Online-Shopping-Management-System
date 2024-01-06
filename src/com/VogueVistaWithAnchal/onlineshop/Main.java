package com.VogueVistaWithAnchal.onlineshop;
/*
 *  Author : Anchal Singh
 */

import java.io.IOException;

/*
 * Main functions = 
 * registration of customer or admin, 
 * login into system Entry through buffered Reader
 * 
 * Array List used in login function to store id, password, and user type 
 * ( C for the customer, A for admin ) as a list
 * 
 * Database tables used are login info, admin info, and 
 * cast info 
 * Login info = storing used id, password, and type of user 
 * Admininfo = storing all details of admin except password 
 * Custinfo = storing all details of the customer except password setUID() function 
 * sets the admin ID to store in database setCUID() function sets the customer ID to 
 * store in the database.
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	private static final String URL = "jdbc:mysql://localhost:3306/onlineshop";
	private static final String USERNAME = "root";

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ce) {
			System.out.println(ce.getMessage());
		}

		System.out.println("CONNECTING TO DATABASE......");
		Scanner sc = new Scanner(System.in);

		System.out.println("ENTER ROOT PASSWORD = ");
//		String password = sc.next();
		String password = "@N$i2003";
		try (Connection con = DriverManager.getConnection(URL, USERNAME, password)) {
			DatabaseTableCreation.makeTable(con);
			System.out.println("DATABASE CONNECTED SUCCESSFULLY .....\n");

			System.out.println("\033[1;36m********************************************");
			System.out.println(" *   \033[1;33mWelcome to VogueVista with Anchal    \033[1;36m*");
			System.out.println(" *   \033[1;33mour Shopping Management System       \033[1;36m*");
			System.out.println("********************************************\033[0m");

			do {
				System.out.println("\033[1;36m1. REGISTER AS ADMIN");
				System.out.println("2. REGISTER AS CUSTOMER");
				System.out.println("3. LOGIN TO SYSTEM");
				System.out.println("4. EXIT");
				System.out.println("*********************************************\033[0m");
				System.out.println("");
				System.out.print("ENTER YOUR CHOICE: ");

				int choice = sc.nextInt();

				switch (choice) {
				case 1 -> registerAdmin(con, sc);
				case 2 -> registerCustomer(con, sc);
				case 3 -> loginSystem(con, sc);
				case 4 -> {
					try {
						exit();
					} catch (InterruptedException e) {
						System.out.print(e.getMessage());
					}
					sc.close(); // closing the resource
					System.exit(0);
				}
				default -> System.out.println("Invalid Choice. Try again.");
				}

			} while (true);
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			sc.close(); // closing the resource
		}
	}

	protected static void exit() throws InterruptedException {
		System.out.print("Existing System");
		int i = 5;
		while (i != 0) {
			System.out.print(".");
			Thread.sleep(450);
			i--;
		}
		System.out.println();
		System.out.print("Thank you for using \033[1;33mVogueVista with Anchal\033[1;36m!!");

	}

	@SuppressWarnings("deprecation")
	public static void clearConsole() {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				// For Windows
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				// For Unix/Linux/MacOS
				Runtime.getRuntime().exec("clear");
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void loginSystem(Connection con, Scanner sc) {
		System.out.print("Enter User Id: ");
		int userid = sc.nextInt();
		System.out.print("Enter User Password : ");
		String password = sc.next();
		System.out.print("Enter User Type : ");
		char usertype = sc.next().charAt(0);

		if (checkCredentials(con, userid, password, usertype)) {
			if (usertype == 'A') {
				Admin adm = new Admin(con, sc, userid);
				adm.adminPage(sc);
			} else { // usertype == 'C'
				Customer cus = new Customer(con, sc, userid);
				cus.customerPage(sc);
			}
		} else {
			System.out.println("Invalid user id and password. Try again...");
		}
	}

	private static boolean checkCredentials(Connection con, int userid, String password, char usertype) {
		String query = "select * from logininfo where usertype = '" + usertype + "' having userid = " + userid
				+ " and password = '" + password + "'";
		try (Statement st = con.createStatement()) {
			ResultSet rs = st.executeQuery(query);
			return rs.next();
		} catch (SQLException s) {
			System.out.print(s.getMessage());
			return false;
		}
	}

	private static void registerAdmin(Connection con, Scanner sc) {
		System.out.println("=================================");
		System.out.println("     ADMIN REGISTRATION FORM     ");
		System.out.println("=================================");

		String query1 = "INSERT INTO logininfo (password, usertype) VALUES (?, 'A')";
		String query2 = "INSERT INTO admininfo VALUES(?, ?, ?, ?, ?, ?)";

		try (PreparedStatement st = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
			System.out.print("Enter your password : ");
			String pass = sc.next();
			st.setString(1, pass);

			int rowsAffected1 = st.executeUpdate();

			if (rowsAffected1 > 0) {
				ResultSet generatedKeys = st.getGeneratedKeys(); // Retrieve the generated userid
				if (generatedKeys.next()) {
					int userId = generatedKeys.getInt(1);
					System.out.println("| Your User ID is : " + userId + " |");

					try (PreparedStatement st2 = con.prepareStatement(query2)) {
						System.out.print("Enter Admin Name :");
						String name = sc.next();
						sc.nextLine();
						System.out.print("Enter Admin Age :");
						int age = sc.nextInt();
						System.out.print("Enter Admin Email Id :");
						String email = sc.next();
						System.out.print("Enter Admin Address :");
						String address = sc.next();
						System.out.print("Enter Contact Number :");
						String contactNo = sc.next();

						st2.setInt(1, userId);
						st2.setString(2, name);
						st2.setInt(3, age);
						st2.setString(4, email);
						st2.setString(5, address);
						st2.setString(6, contactNo);

						int rowsAffected2 = st2.executeUpdate();

						if (rowsAffected2 > 0) {
							System.out.println("Admin added successfully.");
						} else {
							System.out.println("Failed to add admin.");
						}
					}

				} else {
					System.out.println("Failed to retrieve generated userid.");
				}

			} else {
				System.out.println("Failed to insert into logininfo.");
			}

		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
	}

	protected static void registerCustomer(Connection con, Scanner sc) {
		System.out.println("====================================");
		System.out.println("     CUSTOMER REGISTRATION FORM     ");
		System.out.println("====================================");

		String query1 = "INSERT INTO logininfo (password, usertype) VALUES (?, 'C')";
		String query2 = "INSERT INTO custinfo VALUES(?, ?, ?, ?, ?, ?)";

		try (PreparedStatement st = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
			System.out.print("Enter your password : ");
			String pass = sc.next();
			st.setString(1, pass);

			int rowsAffected1 = st.executeUpdate();

			if (rowsAffected1 > 0) {
				ResultSet generatedKeys = st.getGeneratedKeys(); // Retrieve the generated userid
				if (generatedKeys.next()) {
					int userId = generatedKeys.getInt(1);
					System.out.println("| Your User ID is : " + userId + " |");

					try (PreparedStatement st2 = con.prepareStatement(query2)) {
						System.out.print("Enter Customer Name :");
						String name = sc.next();
						sc.nextLine();
						System.out.print("Enter Customer Age :");
						int age = sc.nextInt();
						System.out.print("Enter Customer Email Id :");
						String email = sc.next();
						System.out.print("Enter Customer Address :");
						String address = sc.next();
						System.out.print("Enter Customer Number :");
						String contactNo = sc.next();

						st2.setInt(1, userId);
						st2.setString(2, name);
						st2.setInt(3, age);
						st2.setString(4, email);
						st2.setString(5, address);
						st2.setString(6, contactNo);

						int rowsAffected2 = st2.executeUpdate();

						if (rowsAffected2 > 0) {
							System.out.println("Customer added successfully.");
						} else {
							System.out.println("Failed to add Customer.");
						}
					}

				} else {
					System.out.println("Failed to retrieve generated userid.");
				}

			} else {
				System.out.println("Failed to insert into logininfo.");
			}

		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
	}

	protected void logoutFromSystem() {

	}
}
