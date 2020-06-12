package com.bookshopapp.exceptionhandler;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class ErrorResponseUrl {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorResponseUrl(String message) {
		super();
		this.message = message;
	}
}
