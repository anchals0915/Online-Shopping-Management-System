# Online Shopping Management System Java Console Application

This project simulates the working of an online shopping portal where customers can buy products. The Online Shopping Management System is a purely console-based application implemented using the programming language JAVA.

## Getting Started

### Prerequisites

Make sure you have Java installed on your machine.

### Running the Game

1. Clone this repository:

    ```bash
    git clone https://github.com/anchals0915/Online-Shopping-Management-System.git
    ```

2. Navigate to the project directory:

    ```bash
    cd Online-Shopping-Management-System
    ```

3. Compile the Java source files:

    ```bash
    javac Main.java
    ```

4. Run the program:

    ```bash
    java Main
    ```


## Project Output Snapshots

![]()

## Project Overview

- **Admin Panel:**
  - Functions include managing products and customers.
  
- **Customer Panel:**
  - Functions include buying products and making payments.


## Class Files

A total of 8 class files have been created:

1. **DatabaseConnection.java**
2. **Shop.java** (Main or starting point of the project)
3. **Admin.java**
4. **Customer.java**
5. **Products.java**
6. **Cart.java**
7. **Payment.java**
8. **Bills.java**

## Java Concepts Used

- String manipulations
- Collections framework (ArrayList)
- JDBC
- Exception Handling
- Inheritance
- Classes and Objects
- BufferedReader for input


## Details of Class Files

### **Shop.java (Main Class)**
- Main functions include registration of customers or admins, login, and entry through buffered reader.
- Uses ArrayList for login function to store id, password, and user type.

### **Admin.java (Subclass of Shop.java)**
- Functions include managing products and customers.
- Calls functions in Products.java for product management.

### **Customer.java (Subclass of Shop.java)**
- Main functions include viewing products, searching, adding/removing products from the cart, viewing the cart, and making payments.
- Uses ArrayList for storing product information.

### **Products.java**
- Main functions include adding, removing, altering product info, viewing, and searching products.
- Uses database table 'products'.

### **Cart.java**
- Functions include adding to cart, viewing cart, removing from the cart, and canceling cart.
- Interacts with the Customer.java class.

### **Payment.java**
- Intermediary class between Customer.java and Bills.java.
- Stores payment details and displays bills.

### **Bills.java**
- Contains details of a bill like billing id, products purchased, total amount, and customer details.
- Functions include generating, displaying, and adding to the database.

### **DatabaseConnection.java**
- Establishes a connection with MySQL server.
- Creates a database 'Onlineshop' and five tables.
- Handles exceptions like IOException, ClassNotFoundException, and SQLException.

## Exception Handling

- Uses try-catch blocks for IOException, ClassNotFoundException, SQLException, and other exceptions.
- Utilizes Exception class functions like `printStackTrace()`.
- `throws` keyword used where applicable.

## Collection Framework

- Implements ArrayList in various class files for easy access to fetched results from the database.

## Database Schema

- Implements JDBC with MySQL for database connectivity.
- Database: **Onlineshop**
- Tables: Login Info, Admin Info, Cust Info, Products, Bills

## Connectivity to MySQL

- Uses JDBC concepts with SQL Driver class to connect to MySQL.
- java.sql.* is imported for necessary classes.
- Creation of the database and tables done in DatabaseConnection.java.

