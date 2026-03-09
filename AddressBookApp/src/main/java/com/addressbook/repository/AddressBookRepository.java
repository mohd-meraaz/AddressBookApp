package com.addressbook.repository;

import java.util.List;
import com.addressbook.model.Contact;

public interface AddressBookRepository {

    List<Contact> getContacts();

    Contact updateContact(Contact contact);
    boolean deleteContact(String firstName);
}