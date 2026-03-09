package com.addressbook.app;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.*;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.addressbook.model.Contact;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class AddressBookService {

    private static final String FILE = "contacts.json";

    @Async
    public void addContactAsync(Contact contact) {

        try {

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .setPrettyPrinting()
                    .create();

            FileReader reader = new FileReader(FILE);

            Contact[] contacts = gson.fromJson(reader, Contact[].class);

            List<Contact> list = new ArrayList<>();

            if (contacts != null)
                list = new ArrayList<>(Arrays.asList(contacts));

            list.add(contact);

            FileWriter writer = new FileWriter(FILE);

            gson.toJson(list, writer);

            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}