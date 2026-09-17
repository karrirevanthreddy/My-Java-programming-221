package record;
import java.util.Scanner;

//Base Class
class RBI {
 // Default RBI interest rate
 public double getRateOfInterest() {
     return 4.0;
 }
}

//Derived Class 1
class SBI extends RBI {
 // Method Overriding
 @Override
 public double getRateOfInterest() {
     return 7.0;
 }
}

//Derived Class 2
class ICICI extends RBI {
 // Method Overriding
 @Override
 public double getRateOfInterest() {
     return 6.5;
 }
}

//Derived Class 3
class PNB extends RBI {
 // Method Overriding
 @Override
 public double getRateOfInterest() {
     return 5.5;
 }
}

//Customer details class
class Customer {
 private String name;

 public Customer(String name) {
     this.name = name;
 }

 public String getName() {
     return name;
 }
}

//Account details class using composition with RBI
class Account {
 private String accountNumber;
 private Customer customer;
 private RBI bank;

 public Account(String accountNumber, Customer customer, RBI bank) {
     this.accountNumber = accountNumber;
     this.customer = customer;
     this.bank = bank;
 }

 public void displayInterestRate() {
     // Dynamic binding happens here based on the bank object passed
     System.out.println("RBI rate of interest is : " + (int)bank.getRateOfInterest() + "%");
 }
}

//Main class to demonstrate Dynamic Polymorphism
public class Bank {
 public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);

     System.out.print("Enter the Bank name to find the rate of Interest : ");
     String bankName = scanner.next();

     // Base class reference variable (Demonstrates Dynamic Polymorphism)
     RBI bank;

     // Dynamic binding based on input
     if (bankName.equalsIgnoreCase("SBI")) {
         bank = new SBI();
     } else if (bankName.equalsIgnoreCase("ICICI")) {
         bank = new ICICI();
     } else if (bankName.equalsIgnoreCase("PNB")) {
         bank = new PNB();
     } else {
         bank = new RBI(); // Defaults to RBI standard
     }

     // Output matching the required test cases
     System.out.println("RBI rate of interest is : " + (double) bank.getRateOfInterest() + "%");

     scanner.close();
 }
}