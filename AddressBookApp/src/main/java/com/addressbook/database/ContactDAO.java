package com.addressbook.database;

import com.addressbook.model.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/*
 DAO Layer
 Responsible for interacting with database
 Retrieves contacts from CONTACT table
*/

public class ContactDAO {

    public List<Contact> getAllContacts() {

        List<Contact> contacts = new ArrayList<>();

        String query = "SELECT * FROM CONTACT";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip = rs.getString("zip");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");

                // Convert DB row → Contact Object
                Contact contact = new Contact(
                        firstName,
                        lastName,
                        address,
                        city,
                        state,
                        zip,
                        phoneNumber,
                        email
                );

                contacts.add(contact);
            }

        } catch (Exception e) {
            System.out.println("Error retrieving contacts from database.");
            e.printStackTrace();
        }

        return contacts;
    }
}