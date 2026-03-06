package com.addressbook.data;

import java.util.*;

public class AddressBookApp {

    

    public static void main(String[] args) {

        
        System.out.println("Enter your operation in integer:- ");
        System.out.println("1.Add new contact \n2. Display all contacts \n3.Update by firstName \n4.Delete");
        Scanner Sc = new Scanner(System.in);
        int n = Sc.nextInt();
        String firstName;
        switch (n)
        {
        	case 1:
        		Utility.addContact();
        		break;
        	case 2:
        		Utility.display();
        		break;
        	
        	case 3:
        		System.out.println("Enter firstname of the contact");
        		firstName = Sc.nextLine();
        		Utility.updateContact(firstName);
        		break;
        		
        	case 4:
        		System.out.println("Enter firstname of the contact");
        		firstName = Sc.nextLine();
        		Utility.deleteContact(firstName);
        		break;
        		
        	default:
        		System.out.println("Invalid Input......");
        }
       
    }
}