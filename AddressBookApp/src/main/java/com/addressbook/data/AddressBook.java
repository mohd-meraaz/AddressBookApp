package com.addressbook.data;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/* Represents a single Address Book */

public class AddressBook {

    private List<Contact> contacts = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    /* Add new contact */

    public void addContact() {

        System.out.print("First Name: ");
        String firstName = sc.nextLine();

        System.out.print("Last Name: ");
        String lastName = sc.nextLine();

        Contact temp = new Contact(firstName, lastName, "", "", "", "", "", "");

        if (contacts.contains(temp)) {
            System.out.println("Duplicate contact not allowed.");
            return;
        }

        System.out.print("Address: ");
        String address = sc.nextLine();

        System.out.print("City: ");
        String city = sc.nextLine();

        System.out.print("State: ");
        String state = sc.nextLine();

        System.out.print("Zip: ");
        String zip = sc.nextLine();

        System.out.print("Phone Number: ");
        String phone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        Contact contact = new Contact(firstName, lastName, address, city, state, zip, phone, email);

        contacts.add(contact);

        System.out.println("Contact Added Successfully.");
    }

    /* Add multiple contacts */

    public void addMultipleContacts(int number) {

        for (int i = 0; i < number; i++) {
            System.out.println("\nEnter Contact " + (i + 1));
            addContact();
        }
    }

    /* Display all contacts */

    public void displayContacts() {

        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        contacts.forEach(System.out::println);
    }

    /* Update contact */

    public void updateContact(String firstName) {

        Contact contact = contacts.stream()
                .filter(c -> c.getFirstName().equalsIgnoreCase(firstName))
                .findFirst()
                .orElse(null);

        if (contact == null) {
            System.out.println("Contact not found.");
            return;
        }

        System.out.println("1 Address\n2 City\n3 State\n4 Zip\n5 Phone\n6 Email");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {

            case 1:
                System.out.print("New Address: ");
                contact.setAddress(sc.nextLine());
                break;

            case 2:
                System.out.print("New City: ");
                contact.setCity(sc.nextLine());
                break;

            case 3:
                System.out.print("New State: ");
                contact.setState(sc.nextLine());
                break;

            case 4:
                System.out.print("New Zip: ");
                contact.setZip(sc.nextLine());
                break;

            case 5:
                System.out.print("New Phone: ");
                contact.setPhoneNumber(sc.nextLine());
                break;

            case 6:
                System.out.print("New Email: ");
                contact.setEmail(sc.nextLine());
                break;
        }

        System.out.println("Contact Updated.");
    }

    /* Delete contact */

    public void deleteContact(String firstName) {

        contacts.removeIf(c -> c.getFirstName().equalsIgnoreCase(firstName));

        System.out.println("Contact Deleted.");
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void sortByName() {
        contacts.stream()
                .sorted(Comparator.comparing(Contact::getFirstName).thenComparing(Contact::getLastName)) 
                .forEach(x-> System.out.println(x.getFirstName()+" "+x.getLastName())); 
    }
    
}