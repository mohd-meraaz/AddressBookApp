package com.addressbook.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AddressBookAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AddressBookAppApplication.class, args);
    }
}