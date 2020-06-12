package com.bookshopapp.pacttest;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.bookshopapp.service.BookConsumerService;
import com.bookshopapp.service.ProviderConnector;
import com.fasterxml.jackson.databind.ObjectMapper;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyTestConfig.class)
public class BookConsumerServiceTest {
	int port = 8053;
	@Rule
	public PactProviderRuleMk2 provider = new PactProviderRuleMk2("providerPact", "localhost", port, this);
	@Autowired
	private BookConsumerService bookConsumerService;

	@Pact(consumer = "ConsumerPact")
	public RequestResponsePact defineExpectation(PactDslWithProvider builder) {
		return builder.uponReceiving("request a particular book").path("/bookshopmaintenanceapplication/api/book/7")
				.method("GET").willRespondWith().status(200)
				.body("{\n" + "\t\"bookName\": \"Head First Java\",\n" + "\t\"bookPrice\": 498.0,\n"
						+ "\t\"publisherName\": \"Raj\",\n" + "\t\"publishingYear\": 2005\n" + "}")
				.toPact();
	}

	@Pact(consumer = "ConsumerPact") // will default to the provider name from mockProvider in Rule
	public RequestResponsePact defineExpectationWithState(PactDslWithProvider builder) {
		return builder.given("SomeState").uponReceiving("get Book data that was not in  provider")
				.path("/bookshopmaintenanceapplication/api/book/1").method("GET").willRespondWith().status(200)
				.body("{\n" + "\t\"bookName\": \"C++\",\n" + "\t\"bookPrice\": 433.0,\n"
						+ "\t\"publisherName\": \"Krishna\",\n" + "\t\"publishingYear\": 2007\n" + "}")
				.toPact();
	}

	@PactVerification(fragment = "defineExpectation")
	@Test
	public void userExist() throws IOException {
		Assert.assertTrue(bookConsumerService.getBook((long) 7).isPresent());
	}

	@PactVerification(fragment = "defineExpectationWithState")
	@Test
	public void runTestWithState() {
		Assert.assertTrue(bookConsumerService.getBook((long) 1).isPresent());
	}
}

@TestConfiguration
class MyTestConfig {
	@Bean
	public BookConsumerService getBookConsumerService(ProviderConnector providerConnector) {
		return new BookConsumerService(providerConnector);
	}

	@Bean
	public ProviderConnector getProviderConnector(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
		return new ProviderConnector("http://localhost:8053/bookshopmaintenanceapplication/api", restTemplateBuilder,
				objectMapper);
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public RestTemplateBuilder getRestTemplateBuilder() {
		return new RestTemplateBuilder();
	}
}
