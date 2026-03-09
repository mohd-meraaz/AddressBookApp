package com.addressbook.app;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.addressbook.model.Contact;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/v1")
public class Controller {

	@Autowired
    AddressBookService service;
	
    @GetMapping()
    public String helthCheckup() {
        return "working";
    }

    @GetMapping("/contacts")
    public List<Contact> getDetails() throws FileNotFoundException {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        FileReader reader = new FileReader("contacts.json");

        Contact[] contacts = gson.fromJson(reader, Contact[].class);

        return Arrays.asList(contacts);
    }

    @PostMapping("/addContact")
    public String setDetails(@RequestBody Contact contact) {

        service.addContactAsync(contact);

        return "Contact is being added asynchronously";
    }
}