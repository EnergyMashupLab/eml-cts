package com.example.restservice;

import java.time.*;

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
		Instant nowInstant;
		String tempString = "";
		LocalDateTime localDateTime;
		// use ZonedDateTime.ofLocal to take local DateTime and include Zone 
		ZonedDateTime zonedDateTime;
		ZonedDateTime now = ZonedDateTime.now();
		ZoneId zoneId = ZoneId.systemDefault();
		ZoneId zoneIdTemp, zoneIdTemp2, zoneIdTemp3;
		String zoneIdString = "";
		ZoneOffset zoneOffset;
		ZoneOffset current = zoneId.getRules().getOffset(Instant.now());
		
		zoneIdTemp = ZoneId.systemDefault().normalized();
		zoneIdTemp2 = zoneIdTemp.normalized();
		zoneIdTemp3 = ZoneId.of("America/New_York");
		System.out.println("zoneIdTemp normalized is " + zoneIdTemp2.toString());
		
		OffsetDateTime offsetDateTime;

		offsetDateTime = OffsetDateTime.of(2020, 3, 30, 17,0,0,0, current);
		
		// NOTE that OffsetDateTime seems to do what I'd like; need to verify parsing
		System.out.println("after offsetDateTime constructor. value is " + offsetDateTime.toString());
		
		
		dtStart = Instant.now();
		// of(int year, Month month, int dayOfMonth, int hour, int minute)
		localDateTime = LocalDateTime.of(2020,  Month.MARCH , 30, 17, 0);
		//	zonedDateTime = ZonedDateTime.of(2020,  Month.MARCH , 30, 17, 0); BAD
		zoneIdTemp = zoneId.normalized();
		zonedDateTime = ZonedDateTime.of(2020, 3, 30, 17, 0, 0, 0, zoneId );
		
		System.out.println("ZonedDateTime constructor using ZoneId.systemDefault() " + zonedDateTime.toString());
		zoneIdString = zoneId.getId();
		System.out.println("zoneIdString from getId() is " + zoneIdString);
		
		System.out.println("zoneIdTemp based on normalized zoneId is "
		+ zoneIdTemp.getId())
		;

		nowInstant = Instant.now();
		System.out.println("Before parse. dtStart == now " + dtStart.toString());
		System.out.println("     localDateTime " + localDateTime.toString());
		System.out.println("     nowInstant is " + nowInstant.toString());
		System.out.println("     zoneId.getId()" + zoneId.getId());
		
		/*		Error - 
		zoneOffset = ZoneOffset.of(zoneId.getId());
		System.out.println("ZoneOffset from ZoneId " + zoneOffset.toString() );
		*/


		// Instant.parse REQUIRES two digit hour, minute, seconds. All MUST be present
		dtStart = Instant.parse("2020-03-15T10:00:00.00Z");
		
		// Try without hyphens in date
		// Fails. "could not be parsed" dtStart = Instant.parse("20200315T10:00:00.00Z");

// fails	dtStart = Instant.parse("2020-03-15T10Z");
// fails	dtStart = Instant.parse("2020-03-15T10:00:00Z");
// fails	dtStart.parse("2020-03-15T10:00:00.00Z");
		
		System.out.print("After parse. dtStart == 2020-03-15 with single zeros ");
		System.out.println(dtStart.toString());
	}

}

