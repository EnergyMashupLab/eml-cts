package org.theenergymashuplab.cts.controller.payloads;

import java.time.*;

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



public class EiCreatedTransaction {
	private TransactionId transactionId;
	private ActorId partyId;
	private ActorId counterPartyId;
	public EiResponse response;
//	public ArrayofResponses responses; NOT USED
	private final RefId refId = new RefId();

	// Default initializer for JSON serialization
	public EiCreatedTransaction() {
	}
	
	public TransactionId getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(TransactionId transactionId) {
		this.transactionId = transactionId;
	}

	public void setPartyId(ActorId partyId) {
		this.partyId = partyId;
	}

	public void setCounterPartyId(ActorId counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public EiCreatedTransaction(
			TransactionId transactionId,
			ActorId partyId,
			ActorId counterPartyId,
			EiResponse response) {

		this.transactionId = transactionId;
		this.partyId = partyId;
		this.counterPartyId = counterPartyId;
		this.response = response;
	}

	public long getId() {
		return transactionId.getTransactionId();
	}

	public void print() {		
		System.err.println(
				"EiCreatedTransaction transactionId " +
				transactionId.toString() +
				" partyId " + partyId.toString() +
				" counterPartyId " + counterPartyId.toString() +
				" refId " + refId.toString());
	}
	
	public String toString()	{
		return ("EiCreatedTransaction transactionId " +
				transactionId.toString() +
				" partyId " + partyId.toString() +
				" counterPartyId " + counterPartyId.toString() +
				" refId " + refId.toString());
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

	public RefId getRefId() {
		return refId;
	}
}
