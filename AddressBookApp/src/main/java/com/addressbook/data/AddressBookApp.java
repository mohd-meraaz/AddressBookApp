package com.addressbook.data;

import java.util.*;

public class AddressBookApp {

    static List<Contacts> contacts = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter how many contacts you want to enter :- ");
        int n = sc.nextInt();
        sc.nextLine();

        while (n-- > 0) {

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

        System.out.println("\nSaved Contacts:\n");

        for (Contacts c : contacts) {
            System.out.println("Name: " + c.getFirstName() + " " + c.getLastName());
            System.out.println("City: " + c.getCity());
            System.out.println("Phone: " + c.getPhoneNumber());
            System.out.println("-------------------------");
        }

        sc.close();
    }
}