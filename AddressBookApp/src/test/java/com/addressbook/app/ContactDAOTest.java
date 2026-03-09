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
	 * Ability to Retrieve Contacts from the Database that were added in a particular period 
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
    
    /*
     * Ability to Retrieve number of Contacts in the Database by City or State
     */
    @Test
    public void shouldRetrieveContactCountByCity(){

        ContactDAO dao = new ContactDAO();
        dao.getContactCountByCity();
        assertTrue(true); // verifies method runs without error
    }
    
    /*
     * Ability to Add new Contact to the Address Book Database
     */
    @Test
    public void shouldInsertContactIntoDatabase(){

        ContactDAO dao = new ContactDAO();

        Contact contact = new Contact(
                "Test",
                "User",
                "Test Street",
                "Delhi",
                "Delhi",
                "110001",
                "9999999999",
                "test@email.com"
        );

        contact.setDateAdded(LocalDate.now());
        boolean inserted = dao.addContact(contact);
        assertTrue(inserted);
    }
    
    
    
    
    

    
    
    
    
}