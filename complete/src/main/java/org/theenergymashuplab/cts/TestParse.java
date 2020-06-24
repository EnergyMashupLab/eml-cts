package org.theenergymashuplab.cts;


import java.time.*;

/*
	Tests the java.time parsing of 8601 strings and other indicators
*/

public class TestParse {

	public static void main(String[] args) {
		Instant dtStart;
		Instant nowInstant;
		LocalDateTime localDateTime;
		// use ZonedDateTime.ofLocal to take local DateTime and include Zone 
		ZonedDateTime zonedDateTime;
		ZoneId zoneId = ZoneId.systemDefault();
		ZoneId zoneIdTemp, zoneIdTemp2;
		String zoneIdString = "";
		ZoneOffset current = zoneId.getRules().getOffset(Instant.now());
		
		zoneIdTemp = ZoneId.systemDefault().normalized();
		zoneIdTemp2 = zoneIdTemp.normalized();
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

