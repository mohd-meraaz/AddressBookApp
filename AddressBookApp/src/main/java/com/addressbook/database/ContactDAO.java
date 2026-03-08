package com.addressbook.database;

import com.addressbook.model.Contact;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 DAO Layer
 Responsible for interacting with database
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
    
    // Method to update contacts
    public boolean updateContact(Contact contact) {

        String query = "UPDATE CONTACT SET address=?, city=?, state=?, zip=?, phoneNumber=?, email=? WHERE firstName=? AND lastName=?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)
        ) {

            ps.setString(1, contact.getAddress());
            ps.setString(2, contact.getCity());
            ps.setString(3, contact.getState());
            ps.setString(4, contact.getZip());
            ps.setString(5, contact.getPhoneNumber());
            ps.setString(6, contact.getEmail());
            ps.setString(7, contact.getFirstName());
            ps.setString(8, contact.getLastName());

            int rowsUpdated = ps.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public List<Contact> getContactsByDateRange(LocalDate startDate, LocalDate endDate) {

        List<Contact> contacts = new ArrayList<>();

        String query = "SELECT * FROM CONTACT WHERE date_added BETWEEN ? AND ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)
        ) {

            ps.setDate(1, Date.valueOf(startDate));
            ps.setDate(2, Date.valueOf(endDate));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Contact contact = new Contact(
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("zip"),
                        rs.getString("phoneNumber"),
                        rs.getString("email")
                );

                contact.setDateAdded(rs.getDate("date_added").toLocalDate());

                contacts.add(contact);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return contacts;
    }
    
}