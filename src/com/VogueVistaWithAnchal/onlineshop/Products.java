package com.VogueVistaWithAnchal.onlineshop;

import java.sql.Connection;
import java.util.Scanner;

public class Products {

	private final Connection con;
	private final Scanner sc;

	public Products(Connection con, Scanner sc) {
		this.con = con;
		this.sc = sc;
	}

	public void ProductsPage() {
		System.out.println("+=====================================================+");
		System.out.println("|         WELCOME TO PRODUCTS MANAGEMENT PAGE         |");
		System.out.println("+=====================================================+");
		do {
			System.out.println("*****************************************************");
			System.out.println("| 1 - ADD PRODUCTS                                |");
			System.out.println("| 2 - REMOVE PRODUCTS                                  |");
			System.out.println("| 3 - ALTER PRODUCT INFO                               |");
			System.out.println("| 4 - VIEW ALL PRODUCTS                                   |");
			System.out.println("| 5 - SEARCH A PARTICULAR PRODUCT                      |");
			System.out.println("| 6 - EXIT PAGE                             |");
			System.out.println("*****************************************************\n");

			System.out.print("Please enter your choice: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1 -> addProducts();
			case 2 -> removeProducts();
			case 3 -> alterProductInfo();
			case 4 -> viewAllProducts();
			case 5 -> searchParticularProduct();
			case 6 -> exitPage();
			default -> System.out.println("Invalid Choice. Try again.");
			}

		} while (true);

	}

	private void addProducts() {

	}

	private void removeProducts() {

	}

	private void alterProductInfo() {

	}

	private void viewAllProducts() {

	}

	private void searchParticularProduct() {

	}

	private void exitPage() {

	}
}
