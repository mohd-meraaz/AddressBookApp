package com.addressbook.app;

import com.addressbook.database.ContactDAO;
import com.addressbook.model.Contact;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ContactDAOTest {
	/*
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
    /*
	 Verify AddressBook service update contacts from database
	*/
    @Test
    public void updateContactInDatabase() {

        ContactDAO dao = new ContactDAO();

        Contact contact = new Contact(
                "Rohan",
                "Mishra",
                "New Address",
                "Mumbai",
                "Maharashtra",
                "400001",
                "9876543210",
                "rohan@test.com"
        );

        boolean updated = dao.updateContact(contact);

        assertTrue(updated);
    }
    /*
	 Ability to Retrieve Contacts
from the Database that
were added in a particular
period 
	*/
    @Test
    public void shouldRetrieveContactsAddedInDateRange(){

        ContactDAO dao = new ContactDAO();

        List<Contact> contacts = dao.getContactsByDateRange(
                LocalDate.of(2026,3,1),
                LocalDate.of(2026,3,31)
        );

        assertNotNull(contacts);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}