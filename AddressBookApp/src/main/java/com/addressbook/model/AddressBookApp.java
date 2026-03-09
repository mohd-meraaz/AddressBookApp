package com.addressbook.model;

import java.time.LocalDate;
import java.util.*;

import com.addressbook.database.ContactDAO;

/* Main Application */

public class AddressBookApp {

    static ContactDAO dao = new ContactDAO();

    // FIX*: Scanner should be global so we don't repeatedly create/close it
    static Scanner sc = new Scanner(System.in);

    public static Contact getContact() {

        System.out.println("Enter First Name:");
        String firstName = sc.nextLine();

        System.out.println("Enter Last Name:");
        String lastName = sc.nextLine();

        System.out.println("Enter Address:");
        String address = sc.nextLine();

        System.out.println("Enter City:");
        String city = sc.nextLine();

        System.out.println("Enter State:");
        String state = sc.nextLine();

        System.out.println("Enter Zip:");
        String zip = sc.nextLine();

        System.out.println("Enter Phone Number:");
        String phoneNumber = sc.nextLine();

        System.out.println("Enter Email:");
        String email = sc.nextLine();

        Contact contact = new Contact(
                firstName,
                lastName,
                address,
                city,
                state,
                zip,
                phoneNumber,
                email
        );

        // FIX*: Do NOT close scanner here (closing System.in breaks program)
        return contact;
    }

    public static void main(String[] args) {

        AddressBookSystem system = new AddressBookSystem();
        AddressBook book = new AddressBook();

        while (true) {

            System.out.println("\nCONTACT MENU");
            System.out.println("1 Add Contact");
            System.out.println("2 Add Multiple Contacts");
            System.out.println("3 Display Contacts");
            System.out.println("4 Update Contact");
            System.out.println("5 Delete Contact");
            System.out.println("6 Print Sorted Contacts");
            System.out.println("7 Save Contacts To File ");
            System.out.println("8 Load Contacts From File");
            System.out.println("9 Save Contacts To CSV ");
            System.out.println("10 Load Contacts From CSV");
            System.out.println("11 Save Contacts To JSON ");
            System.out.println("12 Load Contacts From JSON");
            System.out.println("13 Get all contacts from database");
            System.out.println("14 Update Contact in the database");
            System.out.println("15 Get Contacts by Date Range"); // FIX*: corrected text
            System.out.println("16 Get Contact count by city");
            System.out.println("17 Get Contact count by state");
            System.out.println("18 Add multiple contacts to database"); // FIX*: corrected menu
            System.out.println("19 Exit");

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

                    System.out.println("1.By Name");
                    System.out.println("2.By City");
                    System.out.println("3.By State");
                    System.out.println("4.By Zip");
                    System.out.println("5.Go Back");

                    int input = sc.nextInt();
                    sc.nextLine();

                    switch (input) {

                        case 1:
                            book.sortByName();
                            break; // FIX*: prevent fallthrough

                        case 2:
                            book.sortByCity();
                            break;

                        case 3:
                            book.sortByState();
                            break;

                        case 4:
                            book.sortByZip();
                            break;

                        case 5:
                            break;

                        default:
                            System.out.println("Invalid input.");
                    }

                    break;

                case 7:
                    book.writeContactToFile("contacts.txt");
                    break;

                case 8:
                    book.readContactFile("contacts.txt");
                    break;

                case 9:
                    book.writeContactsToCSVFile("contacts.csv");
                    break;

                case 10:
                    book.readContactsToCSVFile("contacts.csv");
                    break;

                case 11:
                    book.writeContactsToJSONFile("contacts.json");
                    break;

                case 12:
                    book.readContactsFromJSONFile("contacts.json");
                    break;

                case 13:

                    List<Contact> result = dao.getAllContacts();

                    // FIX*: Print contacts properly
                    for (Contact c : result) {
                        System.out.println(c);
                    }

                    break;

                case 14:

                    boolean updated = dao.updateContact(getContact());

                    if (updated) {
                        System.out.println("Contact updated successfully.");
                    } else {
                        System.out.println("Contact not found or update failed.");
                    }

                    break; // FIX*: prevent fallthrough

                case 15:

                    System.out.println("Enter start date (YYYY-MM-DD):");
                    String start = sc.nextLine();

                    System.out.println("Enter end date (YYYY-MM-DD):");
                    String end = sc.nextLine();

                    LocalDate startDate = LocalDate.parse(start);
                    LocalDate endDate = LocalDate.parse(end);

                    List<Contact> contacts = dao.getContactsByDateRange(startDate, endDate);

                    if (contacts.isEmpty()) {
                        System.out.println("No contacts found in this date range.");
                    } else {
                        for (Contact c : contacts) {
                            System.out.println(c);
                        }
                    }

                    break;

                case 16:
                    dao.getContactCountByCity();
                    break;

                case 17:
                    dao.getContactCountByState();
                    break;

                case 18:

                    // FIX*: initialize list (previously null)
                    List<Contact> multipleContacts = new ArrayList<>();

                    System.out.println("\nEnter how many contacts you want to enter: ");
                    int count = sc.nextInt();
                    sc.nextLine();

                    for (int i = 1; i <= count; i++) {

                        System.out.println("Enter the Details of contact " + i + ":");

                        Contact contact = getContact();

                        multipleContacts.add(contact);
                    }

                    dao.addMultipleContacts(multipleContacts);

                    break;

                case 19:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid input.");
            }
        }
    }
}