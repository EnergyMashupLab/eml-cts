package org.theenergymashuplab.cts.controller.payloads;

import java.time.*;
import java.util.Random;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

// from NIST-CTS-Agents
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.theenergymashuplab.cts.dao.*;
import org.theenergymashuplab.cts.model.*;
import java.util.List;





public class EiCreateTransaction {
	private ActorId counterPartyId;
	private ActorId partyId;
	private RefId requestId;
	private EiTransaction transaction;
	
	/*
	@JsonIgnore
	private final Random rand = new Random();
	 */
	
	// Default initializer for JSON serialization
	public EiCreateTransaction() {
	}

	public EiCreateTransaction(EiTransaction eiTransaction)	{
		this.counterPartyId = new ActorId();
		this.partyId = new ActorId();
		this.requestId = new RefId();
		this.transaction = eiTransaction;
	}

	/* 
	 * Parallel for EiCreateTransaction, EiCreateTender:
	 * 		pass in a completed Tender/Transaction which includes through its Tender interval, quantity, price,
	 * 		or for EiCancelTender only the TenderId.
	 * 
	 * Add party, counterParty, and requestId for the message payload.
	 */
	public EiCreateTransaction(EiTransaction transaction, ActorId party, ActorId counterParty) {

		this.transaction = transaction;
		this.partyId = party;
		this.counterPartyId = counterParty;
		this.requestId = new RefId();
		
//		System.err.println("EiCreateTransaction Constructor before print()");
//		this.print();
	}

	public void print() {
		String printStringFormat = "EiCreateTransaction.print() transactionId %d partyId %d counterPartyId %d requestId %d  dtStart %s";
		
		System.err.println(String.format(printStringFormat,
				transaction.getTransactionId().getTransactionId(),
				partyId.getActorId(), 
				counterPartyId.getActorId(),
				requestId.getRefId(),
				transaction.getTender().getInterval().dtStart.toString()));
	}
	
	public String toString() {
		String printStringFormat = "EiCreateTransaction.print() transactionId %d partyId %d counterPartyId %d requestId %d  dtStart %s";
		
		return ("EiCreateTransaction transactionId " + transaction.getTransactionId().toString() +
				" partyid " + partyId.toString() +
				" counterPartyid " + counterPartyId.toString() +			
				" requestId " + requestId.toString() +
				transaction.getTender().toString());
	}

	public ActorId getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(ActorId counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public ActorId getPartyId() {
		return partyId;
	}

	public void setPartyId(ActorId partyId) {
		this.partyId = partyId;
	}

	public RefId getRequestId() {
		return requestId;
	}

	public void setRequestId(RefId requestId) {
		this.requestId = requestId;
	}

	public EiTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(EiTransaction transaction) {
		this.transaction = transaction;
	}
	
	
}
