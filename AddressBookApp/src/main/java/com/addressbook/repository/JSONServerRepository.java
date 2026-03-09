package com.addressbook.repository;

import io.restassured.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;

import com.addressbook.model.Contact;

public class JSONServerRepository implements AddressBookRepository {

    private static final String BASE_URL = "http://localhost:3000/contacts";

    @Override
    public List<Contact> getContacts() {

        Response response =
                given()
                .when()
                .get(BASE_URL)
                .then()
                .statusCode(200)
                .extract()
                .response();

        Contact[] contacts = response.as(Contact[].class);

        return Arrays.asList(contacts);
    }

    @Override
    public Contact updateContact(Contact contact) {

        Response response =
                given()
                    .contentType("application/json")
                    .body(contact)
                .when()
                    .put(BASE_URL + "/" + contact.getFirstName())
                .then()
                    .statusCode(200)
                    .extract()
                    .response();

        return response.as(Contact.class);
    }
    
    public boolean deleteContact(String firstName) {

        List<Contact> contacts = getContacts();

        Contact contactToDelete = null;

        for (Contact contact : contacts) {
            if (contact.getFirstName().equalsIgnoreCase(firstName)) {
                contactToDelete = contact;
                break;
            }
        }

        if (contactToDelete == null) {
            return false;
        }

        Response response =
                given()
                .when()
                .delete(BASE_URL + "/" + contactToDelete.getFirstName())
                .then()
                .statusCode(200)
                .extract()
                .response();

        return response.getStatusCode() == 200;
    }
}