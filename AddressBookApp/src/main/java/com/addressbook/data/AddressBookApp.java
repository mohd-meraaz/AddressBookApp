package com.addressbook.data;

import java.util.Scanner;

public class AddressBookApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AddressBookSystem addressBookSystem = new AddressBookSystem();
        AddressBook newAddressbook = new AddressBook();
        while(true) {
        	System.out.println("\n1. Add new Address book \n2. Use existing Address book");
        	int choice = sc.nextInt();
        	sc.nextLine();
        	
        	switch(choice) {
        		case 1:
        			System.out.println("Enter the name of new adressbook");
        			String newName = sc.nextLine();
        			addressBookSystem.addAddressBook(newName);
        			System.out.println(newName + " address book added succesfully");
        			break;
        		case 2 :
        			System.out.println("Enter which addressBook you want to add contact.");
        			addressBookSystem.displayAddressBook();
        			String choiceAddressBook = sc.nextLine();
        			newAddressbook= addressBookSystem.getAddressBook(choiceAddressBook);
        			
        			while (true) {   

        	            System.out.println("\nEnter your operation:");
        	            System.out.println("1. Add new contact");
        	            System.out.println("2. Add multiple contacts");
        	            System.out.println("3. Display all contacts");
        	            System.out.println("4. Update by firstName");
        	            System.out.println("5. Delete contact");
        	            System.out.println("6. Exit");

        	            int n = sc.nextInt();
        	            sc.nextLine();   

        	            String firstName;

        	            switch (n) {

        	                case 1:
        	                	newAddressbook.addContact();
        	                    break;

        	                case 2:
        	                    System.out.println("Enter how many contacts you want to enter:");
        	                    int number = sc.nextInt();
        	                    sc.nextLine();   
        	                    newAddressbook.addMultipleContacts(number);
        	                    break;

        	                case 3:
        	                	newAddressbook.display();
        	                    break;

        	                case 4:
        	                    System.out.print("Enter firstname of the contact: ");
        	                    firstName = sc.nextLine();
        	                    newAddressbook.updateContact(firstName);
        	                    break;

        	                case 5:
        	                    System.out.print("Enter firstname of the contact: ");
        	                    firstName = sc.nextLine();
        	                    newAddressbook.deleteContact(firstName);
        	                    break;

        	                case 6:
        	                    System.out.println("Exiting application...");
        	                    sc.close();
        	                    return;

        	                default:
        	                    System.out.println("Invalid Input......");
        	            }
        	        }
        			
        			
        			
        	}
        	
        }
    }
}