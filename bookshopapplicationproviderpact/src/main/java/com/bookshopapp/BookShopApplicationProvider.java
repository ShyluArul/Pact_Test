package com.bookshopapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookshopapp.entities.Book;
import com.bookshopapp.repository.BookRepo;

@SpringBootApplication
public class BookShopApplicationProvider implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(BookShopApplicationProvider.class, args);
	}

	@Autowired
	private BookRepo bookRepo;

	@Override
	public void run(String... args) throws Exception {
		/*Book b1 = new Book(7L, "Head First Java", 498, "Raj", 2005);
		bookRepo.save(b1);
		Book b2 = new Book(8L, "Spring", 333, "Mhetha", 2008);
		bookRepo.save(b2);*/
	}
}