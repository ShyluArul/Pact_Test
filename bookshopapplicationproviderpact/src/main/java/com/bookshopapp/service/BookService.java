package com.bookshopapp.service;

import java.util.List;
import java.util.Optional;
import com.bookshopapp.entities.Book;

public interface BookService {
	public List<Book> getAllBooks();

	public Optional<Book> findBybookName(String bookName);

	public Optional<Book> findBookById(Long id);

	public void deleteBook(Long id);

	public Book addBook(Book book);

	public Book updateBook(Long id, Book book);
}
