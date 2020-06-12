package com.bookshopapp.pacttest;

import java.util.Arrays;
import org.apache.http.HttpRequest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.bookshopapp.BookShopApplicationProvider;
import com.bookshopapp.entities.Book;
import com.bookshopapp.service.BookService;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.TargetRequestFilter;
import au.com.dius.pact.provider.junit.VerificationReports;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;

@RunWith(PactRunner.class)
@Provider("providerPact")
@PactBroker
@VerificationReports(value = { "console", "markdown", "json" })
public class BookShopProviderTest {
	private int PORT = 8090;
	@TestTarget
	public final Target target = new HttpTarget("http", "localhost", PORT);
	private static ConfigurableApplicationContext applicationContext;

	@BeforeClass
	public static void setVersions() {
		System.setProperty("pact.provider.version", "0.0.1");
		System.setProperty("pact.verifier.publishResults", "true");
	}

	@BeforeClass
	public static void start() {
		applicationContext = SpringApplication.run(BookShopApplicationProvider.class);
	}

	@AfterClass
	public static void stop() {
		SpringApplication.exit(applicationContext);
	}

	@TargetRequestFilter
	public void printTheRequestHeaders(HttpRequest request) {
		Arrays.asList(request.getAllHeaders())
				.forEach(header -> System.out.println(header.getName() + "->" + header.getValue()));
	}

	@State("SomeState")
	public void withSomeState() {
		System.out.println("do something with state");
		BookService bookService = applicationContext.getBean(BookService.class);
		bookService.addBook(new Book(1L, "C++", 433.0, "Krishna", 2007));
	}
}
