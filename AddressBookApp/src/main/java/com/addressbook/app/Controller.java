package com.addressbook.app;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addressbook.model.Contact;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;




@RestController
@RequestMapping("/v1")
public class Controller {
	
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
}

