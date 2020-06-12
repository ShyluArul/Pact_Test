package com.bookshopapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.bookshopapp.entities.Book;
import com.bookshopapp.service.BookConsumerService;

@RestController
public class BookConsumerController {
	@Autowired
	BookConsumerService bookConsumerService;

	@GetMapping("/demo")
	public ResponseEntity<Book> getDemoBook() {
		return bookConsumerService.getDemoBook().map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("book/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") Long id) {
		return bookConsumerService.getBook(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}
}
