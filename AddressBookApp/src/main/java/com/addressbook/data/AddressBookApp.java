package com.addressbook.data;

import java.util.Scanner;

/* Main Application */

public class AddressBookApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AddressBookSystem system = new AddressBookSystem();

        while (true) {

            System.out.println("\nMAIN MENU");
            System.out.println("1 Add Address Book");
            System.out.println("2 Open Address Book");
            System.out.println("3 Search Person");
            System.out.println("4 Count Contacts");
            System.out.println("5 Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Address Book Name: ");
                    String name = sc.nextLine();

                    system.addAddressBook(name);
                    break;

                case 2:

                    /* FIX: show existing address books */
                    if(system.displayAddressBooks()) {

                    	System.out.print("Enter Address Book Name: ");
                    	String bookName = sc.nextLine();

                    	AddressBook book = system.getAddressBook(bookName);

                    	if (book == null) {
                    		System.out.println("Address Book not found.");
                    		break;
                    	}

                    	manageAddressBook(book, sc);
                    	break;
                    }
                    else {
                    	System.out.println("No Address Books available.");
                    	break;
                    }

                case 3:

                    System.out.println("1 Search By City");
                    System.out.println("2 Search By State");

                    int search = sc.nextInt();
                    sc.nextLine();

                    if (search == 1) {

                        /* FIX: show existing cities */
                        system.displayAvailableCities();

                        System.out.print("Enter City: ");
                        system.searchByCity(sc.nextLine());

                    } else {

                        /* FIX: show existing states */
                        system.displayAvailableStates();

                        System.out.print("Enter State: ");
                        system.searchByState(sc.nextLine());
                    }

                    break;

                case 4:

                    System.out.println("1 Count By City");
                    System.out.println("2 Count By State");

                    int countChoice = sc.nextInt();
                    sc.nextLine();

                    if (countChoice == 1) {

                        /* FIX: show existing cities */
                        system.displayAvailableCities();

                        System.out.print("Enter City: ");
                        system.countByCity(sc.nextLine());

                    } else {

                        /* FIX: show existing states */
                        system.displayAvailableStates();

                        System.out.print("Enter State: ");
                        system.countByState(sc.nextLine());
                    }

                    break;

                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
            }
        }
    }

    /* AddressBook Menu */

    private static void manageAddressBook(AddressBook book, Scanner sc) {

        while (true) {

            System.out.println("\nCONTACT MENU");
            System.out.println("1 Add Contact");
            System.out.println("2 Add Multiple Contacts");
            System.out.println("3 Display Contacts");
            System.out.println("4 Update Contact");
            System.out.println("5 Delete Contact");
            System.out.println("6 Back");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    book.addContact();
                    break;

                case 2:
                    System.out.print("How many contacts: ");
                    int num = sc.nextInt();
                    sc.nextLine();
                    book.addMultipleContacts(num);
                    break;

                case 3:
                    book.displayContacts();
                    break;

                case 4:
                    System.out.print("Enter First Name: ");
                    book.updateContact(sc.nextLine());
                    break;

                case 5:
                    System.out.print("Enter First Name: ");
                    book.deleteContact(sc.nextLine());
                    break;

                case 6:
                    return;
            }
        }
    }
}