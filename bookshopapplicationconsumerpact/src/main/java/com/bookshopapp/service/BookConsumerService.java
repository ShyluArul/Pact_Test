package com.bookshopapp.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookshopapp.entities.Book;

@Service
public class BookConsumerService {
	@Autowired
	ProviderConnector providerConnector;

	public BookConsumerService(ProviderConnector providerConnector) {
		this.providerConnector = providerConnector;
	}

	public Optional<Book> getDemoBook() {
		return Optional.of(new Book(5L, "c", 435, "John", 2000));
	}

	public Optional<Book> getBook(Long id) {
		Optional<Book> studentHolder = Optional.empty();
		try {
			studentHolder = Optional.ofNullable(providerConnector
					.serializeData(providerConnector.getData(String.format("/book/%d", id)).getBody(), Book.class));
		} catch (Exception e) {
			e.printStackTrace();
			return studentHolder;
		}
		return studentHolder;
	}
}
