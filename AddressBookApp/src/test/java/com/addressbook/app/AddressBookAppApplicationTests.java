package com.addressbook.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.addressbook.model.Contact;
import com.addressbook.repository.JSONServerRepository;

@SpringBootTest
class AddressBookAppApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
    public void givenContact_WhenUpdated_ShouldSyncWithMemory() {

        JSONServerRepository repo = new JSONServerRepository();

        AddressBookService service = new AddressBookService(repo);

        service.syncContacts();

        Contact contact = new Contact(
                "Amit Updated",
                "Delhi",
                "9999999999",
                "amit@gmail.com"
        );

        service.updateContact(contact);

        assertEquals("Amit Updated",
                service.getContacts().get(1).getFirstName());
    }
	@Test
	public void givenFirstName_WhenDeleted_ShouldSyncWithMemory() {

	    JSONServerRepository repo = new JSONServerRepository();

	    AddressBookService service = new AddressBookService(repo);

	    service.syncContacts();

	    int initialSize = service.getContacts().size();

	    String firstName = service.getContacts().get(0).getFirstName();

	    service.deleteContact(firstName);

	    assertEquals(initialSize - 1, service.getContacts().size());
	}
}
