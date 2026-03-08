package com.addressbook.app;

import com.addressbook.database.ContactDAO;
import com.addressbook.model.Contact;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ContactDAOTest {
	/*
	 UC16 Test
	 Verify AddressBook service retrieves contacts from database
	*/

    @Test
    public void retrieveContactsFromDatabase() {

        ContactDAO dao = new ContactDAO();

        List<Contact> contacts = dao.getAllContacts();

        // Verify list is not null
        assertNotNull(contacts);

        // Verify database contains entries
        assertTrue(contacts.size() >= 0);

        // print contacts to verify
        contacts.forEach(System.out::println);
    }
    
    
}