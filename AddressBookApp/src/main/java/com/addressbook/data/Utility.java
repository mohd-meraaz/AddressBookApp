package com.addressbook.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utility {
	
	static List<Contacts> contacts = new ArrayList<>();

	static Scanner sc = new Scanner(System.in);
	
//	Method to add contact in the Contacts Application
	public static void addContact() {
    

        System.out.print("\nEnter First Name:- ");
        String firstName = sc.nextLine();

        System.out.print("Enter Last Name:- ");
        String lastName = sc.nextLine();

        System.out.print("Enter Address:- ");
        String address = sc.nextLine();

        System.out.print("Enter city:- ");
        String city = sc.nextLine();

        System.out.print("Enter state:- ");
        String state = sc.nextLine();

        System.out.print("Enter Zip code:- ");
        String zip = sc.nextLine();

        System.out.print("Enter Phone Number :- ");
        String phoneNumber = sc.nextLine();

        System.out.print("Enter email:- ");
        String email = sc.nextLine();

        Contacts contact = new Contacts(firstName, lastName, address, city, state, zip, phoneNumber, email);

        contacts.add(contact);
    
	}
	
	
//	Method to display all contacts in the Contacts Application
	public static void display() {

	    if (contacts.isEmpty()) {
	        System.out.println("\nNo contacts available.");
	        return;
	    }

	    System.out.println("\nSaved Contacts:\n");

	    for (Contacts c : contacts) {
	        System.out.println("First Name : " + c.getFirstName());
	        System.out.println("Last Name  : " + c.getLastName());
	        System.out.println("Address    : " + c.getAddress());
	        System.out.println("City       : " + c.getCity());
	        System.out.println("State      : " + c.getState());
	        System.out.println("Zip Code   : " + c.getZip());
	        System.out.println("Phone No   : " + c.getPhoneNumber());
	        System.out.println("Email      : " + c.getEmail());
	        System.out.println("------------------------------------");
	    }
	}
	
//	Method to update contact in the Contacts Application
	public static void updateContact(String firstName) {

	    boolean found = false;

	    for (Contacts contact : contacts) {

	        if (contact.getFirstName().equalsIgnoreCase(firstName)) {

	            found = true;

	            System.out.println("\nContact Found!");
	            System.out.println("What do you want to update?");
	            System.out.println("1. Address");
	            System.out.println("2. City");
	            System.out.println("3. State");
	            System.out.println("4. Zip");
	            System.out.println("5. Phone Number");
	            System.out.println("6. Email");

	            int choice = sc.nextInt();
	            sc.nextLine();

	            switch (choice) {

	                case 1:
	                    System.out.print("Enter new Address: ");
	                    contact.setAddress(sc.nextLine());
	                    break;

	                case 2:
	                    System.out.print("Enter new City: ");
	                    contact.setCity(sc.nextLine());
	                    break;

	                case 3:
	                    System.out.print("Enter new State: ");
	                    contact.setState(sc.nextLine());
	                    break;

	                case 4:
	                    System.out.print("Enter new Zip: ");
	                    contact.setZip(sc.nextLine());
	                    break;

	                case 5:
	                    System.out.print("Enter new Phone Number: ");
	                    contact.setPhoneNumber(sc.nextLine());
	                    break;

	                case 6:
	                    System.out.print("Enter new Email: ");
	                    contact.setEmail(sc.nextLine());
	                    break;

	                default:
	                    System.out.println("Invalid Choice");
	            }

	            System.out.println("Contact Updated Successfully!");
	            return;
	        }
	    }

	    if (!found) {
	        System.out.println("Contact not found.");
	    }
	}
	

}
