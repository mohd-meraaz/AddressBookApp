package com.addressbook.data;

import java.util.*;

public class AddressBookSystem {
	Map<String,AddressBook> addressBooks = new HashMap<>();
	
	public void addAddressBook(String name) {
		if(addressBooks.containsKey(name)) {
			System.out.println("This address book is already present");
			return;
		}
		addressBooks.put(name,new AddressBook());
	}
	
	public void displayAddressBook()
	{
		for(String name : addressBooks.keySet() ) {
			System.out.println(name);
		}
	}
	
	public AddressBook getAddressBook(String name) {
		
		return addressBooks.get(name);
		
	}
	public Set<String> listAllAddressBooks() {
		return addressBooks.keySet();
	}
	public void searchByCityAcrossAddressBooks(String city) {

	    addressBooks.values()
	            .stream()
	            .flatMap(ab -> ab.getContacts().stream())
	            .filter(c -> c.getCity().equalsIgnoreCase(city))
	            .forEach(System.out::println);
	}
	
	public void searchByStateAcrossAddressBooks(String state) {

	    addressBooks.values()
	            .stream()
	            .flatMap(ab -> ab.getContacts().stream())
	            .filter(c -> c.getState().equalsIgnoreCase(state))
	            .forEach(System.out::println);
	}
}












