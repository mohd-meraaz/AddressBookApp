package com.addressbook.data;

import java.util.*;

public class AddressBookApp {

    

    public static void main(String[] args) {

        
        System.out.println("Enter your operation in integer:- ");
        System.out.println("1.add \n2. display all contacts \n3.update by firstName");
        Scanner Sc = new Scanner(System.in);
        int n = Sc.nextInt();
        
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
        		String firstName = Sc.nextLine();
        		Utility.updateContact(firstName);
        		
        }
       
    }
}