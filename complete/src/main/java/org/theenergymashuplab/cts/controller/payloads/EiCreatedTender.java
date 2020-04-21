package org.theenergymashuplab.cts.controller.payloads;

import java.time.*;

// from NIST-CTS-Agents
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.theenergymashuplab.cts.dao.EiTenderType;
import org.theenergymashuplab.cts.model.EiTenderModel;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;





public class EiCreatedTender {
	private TenderId tenderId;
	private ActorId partyId;
	private ActorId counterPartyId;
	public EiResponse response;
//	public ArrayofResponses responses; NOT USED
	private final RefId refId = new RefId();

	/*
	 * Default constructor for JSON deserialization.
	 * TO DO change to zero Id values in ActorId and RefId constructors
	 */
	public EiCreatedTender()	{		
	}
	
	public EiCreatedTender(
			TenderId tenderId,
			ActorId partyId,
			ActorId counterPartyId,
			EiResponse response) {

		this.tenderId = tenderId;
		this.partyId = partyId;
		this.counterPartyId = counterPartyId;
		this.response = response;
//		this.print();
	}

	public long getId() {
		return tenderId.getTenderId();
	}

	public void print() {		
		System.err.println(
				"EiCreatedTender tenderId " +
				tenderId.toString() +
				" partyId " + partyId.toString() +
				" counterPartyId " + counterPartyId.toString() +
				" refId " + refId.toString() +
				" response " + response.toString());
	}
	
	public String toString() {
		return "EiCreatedTender tenderId " +
				tenderId.toString() +
				" partyId " + partyId.toString() +
				" counterPartyId " + counterPartyId.toString() +
				" refId " + refId.toString() +
				" response " + response.toString();
	}
	
	public EiResponse getResponse() {
		return response;
	}

	public void setResponse(EiResponse response) {
		this.response = response;
	}

	public TenderId getTenderId() {
		return tenderId;
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
