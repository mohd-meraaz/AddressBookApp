package com.addressbook.model;

import java.util.HashMap;
import java.util.Map;

/* System containing multiple Address Books */

public class AddressBookSystem {

    private Map<String, AddressBook> addressBooks = new HashMap<>();

    /* Add Address Book */

    public void addAddressBook(String name) {

        if (addressBooks.containsKey(name)) {
            System.out.println("Address Book already exists.");
            return;
        }

        addressBooks.put(name, new AddressBook());
        System.out.println("Address Book Added.");
    }

    /* Display all Address Books */

    public boolean displayAddressBooks() {

        if(addressBooks.isEmpty()){
            
            return false;
        }

        System.out.println("Available Address Books:");
        addressBooks.keySet().forEach(System.out::println);
        return true;
    }

    public AddressBook getAddressBook(String name) {
        return addressBooks.get(name);
    }

    /* FIX: Display available cities before asking */

    public void displayAvailableCities() {

        System.out.println("Available Cities:");

        addressBooks.values()
                .stream()
                .flatMap(ab -> ab.getContacts().stream())
                .map(Contact::getCity)
                .distinct()
                .forEach(System.out::println);
    }

    /* FIX: Display available states before asking */

    public void displayAvailableStates() {

        System.out.println("Available States:");

        addressBooks.values()
                .stream()
                .flatMap(ab -> ab.getContacts().stream())
                .map(Contact::getState)
                .distinct()
                .forEach(System.out::println);
    }

    /* Search by City */

    public void searchByCity(String city) {

        addressBooks.values()
                .stream()
                .flatMap(ab -> ab.getContacts().stream())
                .filter(c -> c.getCity().equalsIgnoreCase(city))
                .forEach(System.out::println);
    }

    /* Search by State */

    public void searchByState(String state) {

        addressBooks.values()
                .stream()
                .flatMap(ab -> ab.getContacts().stream())
                .filter(c -> c.getState().equalsIgnoreCase(state))
                .forEach(System.out::println);
    }

    /* Count by City */

    public void countByCity(String city) {

        long count = addressBooks.values()
                .stream()
                .flatMap(ab -> ab.getContacts().stream())
                .filter(c -> c.getCity().equalsIgnoreCase(city))
                .count();

        System.out.println("Total Contacts in " + city + " : " + count);
    }

    /* Count by State */

    public void countByState(String state) {

        long count = addressBooks.values()
                .stream()
                .flatMap(ab -> ab.getContacts().stream())
                .filter(c -> c.getState().equalsIgnoreCase(state))
                .count();

        System.out.println("Total Contacts in " + state + " : " + count);
    }
}