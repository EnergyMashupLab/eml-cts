package org.theenergymashuplab.cts.controller.payloads;

import java.time.*;


// from NIST-CTS
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.theenergymashuplab.cts.dao.*;
import org.theenergymashuplab.cts.model.*;


public class EICanceledTender {
	private final ActorId partyId;
	private final ActorId counterPartyId;
	public EiResponse response;
//	public ArrayofResponses responses; NOT USED
//	refId is in the EiResponse

	
	public EICanceledTender(ActorId partyId, ActorId counterPartyId, EiResponse response) {
		this.partyId = partyId;
		this.counterPartyId = counterPartyId;
		this.response = response;
	}

	//Default constructor for JSON serialization
	public EICanceledTender()	{
		this.partyId = new ActorId();
		this.counterPartyId = new ActorId();
		this.response = new EiResponse(200, "OK");
	}
	
	
	public void print() {
		String printStringFormat = 
				"EiCanceledTender  partyId %d counterPartyId %d refId %d ";
		
		System.err.println(
				String.format(printStringFormat, 
				partyId.getActorId(), 
				counterPartyId.getActorId(), 
				response.getRefId()));
	}
	
	public String toString()	{
		return "EiCanceledTender Soure RefId " +
				"PENDING" +
				" partyId " + partyId.toString() +
				" counterPartyId " + counterPartyId.toString() +
				" response " + response.toString();
	}

	public EiResponse getResponse() {
		return response;
	}

	public void setResponse(EiResponse response) {
		this.response = response;
	}

	public ActorId getPartyId() {
		return partyId;
	}

	public ActorId getCounterPartyId() {
		return counterPartyId;
	}
	
	

}
