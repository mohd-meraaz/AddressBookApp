package com.addressbook.data;


import java.io.*;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

/* Represents a single Address Book */

public class AddressBook {

    private List<Contact> contacts = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    /* Add new contact */

    public void addContact() {

        System.out.print("First Name: ");
        String firstName = sc.nextLine();

        System.out.print("Last Name: ");
        String lastName = sc.nextLine();

        Contact temp = new Contact(firstName, lastName, "", "", "", "", "", "");

        if (contacts.contains(temp)) {
            System.out.println("Duplicate contact not allowed.");
            return;
        }

        System.out.print("Address: ");
        String address = sc.nextLine();

        System.out.print("City: ");
        String city = sc.nextLine();

        System.out.print("State: ");
        String state = sc.nextLine();

        System.out.print("Zip: ");
        String zip = sc.nextLine();

        System.out.print("Phone Number: ");
        String phone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        Contact contact = new Contact(firstName, lastName, address, city, state, zip, phone, email);

        contacts.add(contact);

        System.out.println("Contact Added Successfully.");
    }

    /* Add multiple contacts */

    public void addMultipleContacts(int number) {

        for (int i = 0; i < number; i++) {
            System.out.println("\nEnter Contact " + (i + 1));
            addContact();
        }
    }

    /* Display all contacts */

    public void displayContacts() {

        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        contacts.forEach(System.out::println);
    }

    /* Update contact */

    public void updateContact(String firstName) {

        Contact contact = contacts.stream()
                .filter(c -> c.getFirstName().equalsIgnoreCase(firstName))
                .findFirst()
                .orElse(null);

        if (contact == null) {
            System.out.println("Contact not found.");
            return;
        }

        System.out.println("1 Address\n2 City\n3 State\n4 Zip\n5 Phone\n6 Email");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {

            case 1:
                System.out.print("New Address: ");
                contact.setAddress(sc.nextLine());
                break;

            case 2:
                System.out.print("New City: ");
                contact.setCity(sc.nextLine());
                break;

            case 3:
                System.out.print("New State: ");
                contact.setState(sc.nextLine());
                break;

            case 4:
                System.out.print("New Zip: ");
                contact.setZip(sc.nextLine());
                break;

            case 5:
                System.out.print("New Phone: ");
                contact.setPhoneNumber(sc.nextLine());
                break;

            case 6:
                System.out.print("New Email: ");
                contact.setEmail(sc.nextLine());
                break;
        }

        System.out.println("Contact Updated.");
    }

    /* Delete contact */

    public void deleteContact(String firstName) {

        contacts.removeIf(c -> c.getFirstName().equalsIgnoreCase(firstName));

        System.out.println("Contact Deleted.");
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void sortByName() {
        contacts.stream()
                .sorted(Comparator.comparing(Contact::getFirstName).thenComparing(Contact::getLastName)) 
                .forEach(x-> System.out.println(x.getFirstName()+" "+x.getLastName())); 
    }

	public void sortByCity() {
		 contacts.stream()
         .sorted(Comparator.comparing(Contact::getCity))
         .forEach(x-> System.out.println(x.getFirstName()+" "+x.getLastName())); 
		
	}

	public void sortByState() {
		contacts.stream()
        .sorted(Comparator.comparing(Contact::getState))
        .forEach(x-> System.out.println(x.getFirstName()+" "+x.getLastName())); 
		
		
	}

	public void sortByZip() {
		contacts.stream()
        .sorted(Comparator.comparing(Contact::getZip))
        .forEach(x-> System.out.println(x.getFirstName()+" "+x.getLastName())); 
		
	}

	public void writeContactToFile(String filePath) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true))){
			for(Contact c: contacts) {
				String data = c.getFirstName()+":"+c.getLastName()+":"+c.getAddress()
				+":"+c.getCity()+":"+c.getState()+":"+c.getZip()+":"+c.getPhoneNumber()+":"+c.getEmail();
				
				bw.write(data);
				bw.newLine();
			}
			System.out.println("Contacts saved to file");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void readContactFile(String filePath) {
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
			String line;
			while((line = br.readLine())!=null) {
				String info[] = line.split(":");
				if(info.length!=8)
					continue;
				Contact c= new Contact(info[0], info[1], info[2], info[3], info[4],
						info[5], info[6], info[7]);
				contacts.add(c);
			}
			System.out.println("Contacts loaded from file");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void writeContactsToCSVFile(String filePath) {
		try(CSVWriter writer = new CSVWriter(new FileWriter(filePath))){
			String[] header = {"FirstName", "LastName", "Address", "City",
					"State", "Zip", "PhoneNumber", "Email"
			};
			writer.writeNext(header);
			
			for(Contact c:contacts) {
				String[] data = {
						c.getFirstName(), c.getLastName(),
						c.getAddress(), c.getCity(),
						c.getState(), c.getZip(),
						c.getPhoneNumber(), c.getEmail()
				};
				
				writer.writeNext(data);
			}
			System.out.println("Contacts saved to csv file");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void readContactsToCSVFile(String filePath) {
		contacts.clear();
		try(CSVReader reader = new CSVReader(new FileReader(filePath))){
			String[]  line;
			reader.readNext();
			while((line=reader.readNext())!=null) {
				if(line.length!=8) continue;
				Contact c= new Contact(line[0], line[1],
						line[2], line[3], line[4], line[5], line[6], line[7]);
				contacts.add(c);
			}
			System.out.println("Contacts loaded from CSV");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(CsvValidationException e) {
			e.printStackTrace();
		}
	}
    
}