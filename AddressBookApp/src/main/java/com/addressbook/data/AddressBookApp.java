package com.addressbook.data;

import java.util.Scanner;

public class AddressBookApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AddressBookSystem addressBookSystem = new AddressBookSystem();
        AddressBook addressBook = null;
        while (true) {

            System.out.println("\nMAIN MENU");
            System.out.println("1. Add new Address Book");
            System.out.println("2. Use existing Address Book");
            System.out.println("3. Search Contacts");
            System.out.println("4. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter the name of new address book: ");
                    String newName = sc.nextLine();

                    addressBookSystem.addAddressBook(newName);
                    System.out.println(newName + " address book added successfully");
                    break;

                case 2:
                    System.out.println("Available Address Books:");
                    addressBookSystem.displayAddressBook();

                    System.out.print("Enter address book name: ");
                    String name = sc.nextLine();

                    addressBook = addressBookSystem.getAddressBook(name);

                    if (addressBook == null) {
                        System.out.println("Address book not found.");
                        break;
                    }

                    manageAddressBook(addressBook, sc);
                    break;
                case 3:

                    System.out.println("1. Search By City");
                    System.out.println("2. Search By State");

                    int searchChoice = sc.nextInt();
                    sc.nextLine();

                    switch (searchChoice) {

                        case 1:
                            System.out.print("Enter city name: ");
                            String city = sc.nextLine();
                            addressBookSystem.searchByCityAcrossAddressBooks(city);
                            break;

                        case 2:
                            System.out.print("Enter state name: ");
                            String state = sc.nextLine();
                            addressBookSystem.searchByStateAcrossAddressBooks(state);
                            break;

                        default:
                            System.out.println("Invalid choice.");
                    }

                    break;
                	
                case 4:
                    System.out.println("Exiting system...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    private static void manageAddressBook(AddressBook addressBook, Scanner sc) {

        while (true) {

            System.out.println("\nCONTACT MENU");
            System.out.println("1. Add contact");
            System.out.println("2. Add multiple contacts");
            System.out.println("3. Display contacts");
            System.out.println("4. Update contact");
            System.out.println("5. Delete contact");
            System.out.println("6. Back to Main Menu");

            int n = sc.nextInt();
            sc.nextLine();

            String firstName;

            switch (n) {

                case 1:
                    addressBook.addContact();
                    break;

                case 2:
                    System.out.print("How many contacts to add: ");
                    int number = sc.nextInt();
                    sc.nextLine();
                    addressBook.addMultipleContacts(number);
                    break;

                case 3:
                    addressBook.display();
                    break;

                case 4:
                    System.out.print("Enter first name: ");
                    firstName = sc.nextLine();
                    addressBook.updateContact(firstName);
                    break;

                case 5:
                    System.out.print("Enter first name: ");
                    firstName = sc.nextLine();
                    addressBook.deleteContact(firstName);
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid input.");
            }
        }
    }
}