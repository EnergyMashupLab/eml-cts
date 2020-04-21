package com.example.restservice;

//	import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.*;

@SpringBootApplication
public class RestServiceApplication {
	private static final Logger logger = LogManager.getLogger(
			RestServiceApplication.class);
	// Application general logger from NIST-CTS-Agents
	private static final Logger logger2 = LogManager.getLogger(
			"application_general_logger");
	
	private static RestTemplate restTemplate;
	final static RestTemplateBuilder builder = new RestTemplateBuilder();


    public static void main(String[] args) {
    	restTemplate = builder.build();
    	ActorId actorId;
 /*   	FAILS - unknown host. Timing in initialization?
		actorId = restTemplate.getForObject(
				"https://lma/party", ActorId.class);
		logger.info(actorId.toString());
		
		actorId = restTemplate.getForObject(
				"https://lme/party", ActorId.class);
		logger.info(actorId.toString());

		actorId = restTemplate.getForObject(
				"https://teua/party", ActorId.class);
		logger.info(actorId.toString());
 */    	
        SpringApplication.run(RestServiceApplication.class, args);
    }
    
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	// Set up the restTemplate - only for debug print. May not be necessary
	// EXPERIMENTAL - Candidate for deletion
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			logger.info("In CommandLineRunner before getForObject");	
//			ActorId actorId = restTemplate.getForObject(
//					"https://lma/party", ActorId.class);
//			logger.info(actorId.toString());	
	};

	/*
			System.err.println("before restTemplate in CommandLineRunner");
			ActorId actorId = restTemplate.getForObject(
					"http://localhost:8080/lma/party", ActorId.class);
			logger.info(actorId.toString());
	 */
}
	
    
	public static void WaitForAppReady() {
    	restTemplate = builder.build();
    	ActorId actorId;
 
		actorId = restTemplate.getForObject(
				"https://lma/party", ActorId.class);
		logger.info(actorId.toString());
		
		actorId = restTemplate.getForObject(
				"https://lme/party", ActorId.class);
		logger.info(actorId.toString());

		actorId = restTemplate.getForObject(
				"https://teua/party", ActorId.class);
		logger.info(actorId.toString());
	}
	
}