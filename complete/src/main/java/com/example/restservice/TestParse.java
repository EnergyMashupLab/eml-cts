package com.example.restservice;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/*
NOTES: 	private final Instant dtStart = Instant.parse("2020-03-15T10:0:0.00Z"); fails Instant.parse.
MUST have complete 8601 string down to subsecond level for Instant.parse to not thrown an exception.

PRESENTS as 

	Starting Servlet engine
	Initializing Spring embedded WebApplicationContext
	Root WebApplicationContext : initialization completed
	Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'greetingController' defined in file [/Users/wtcox/git/gs-rest-service-Return-EiCreatedTender/complete/target/classes/com/example/restservice/GreetingController.class]: Instantiation of bean failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.example.restservice.GreetingController]: Constructor threw exception; nested exception is java.time.format.DateTimeParseException: Text '2020-03-15T10:0:0.00Z' could not be parsed at index 14
2020-03-12 12:59:29.328  INFO 43561 --- [           main] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]

The critical information was at the end of an 800 character very long line.

In Eclipse Preferences "Run/Debug > Console" set fixed line length to 160, buffer size is OK. Apply and Close.

*/

public class TestParse {


	public static void main(String[] args) {
		Instant dtStart;
		String tempString = "";

		dtStart = Instant.now();
		
		System.out.println("before body of main");
		System.out.print("Before parse. dtStart == now ");
		System.out.println(dtStart.toString());

		// Instant.parse REQUIRES two digit hour, minute, seconds. All MUST be present
		dtStart = Instant.parse("2020-03-15T10:00:00.00Z");

// fails	dtStart = Instant.parse("2020-03-15T10Z");


// fails	dtStart = Instant.parse("2020-03-15T10:00:00Z");

// fails	dtStart.parse("2020-03-15T10:00:00.00Z");
		
		System.out.print("After parse. dtStart == 2020-03-15 with single zeros ");
		System.out.println(dtStart.toString());
	}

}

