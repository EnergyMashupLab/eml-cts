package org.theenergymashuplab.cts;

public class EICanceledTenderPayload {
	private final ActorIdType partyId;
	private final ActorIdType counterPartyId;
	public EiResponse response;
//	public ArrayofResponses responses; NOT USED
//	refId is in the EiResponse

	
	public EICanceledTenderPayload(ActorIdType partyId, ActorIdType counterPartyId, EiResponse response) {
		this.partyId = partyId;
		this.counterPartyId = counterPartyId;
		this.response = response;
	}

	//Default constructor for JSON serialization
	public EICanceledTenderPayload()	{
		this.partyId = new ActorIdType();
		this.counterPartyId = new ActorIdType();
		this.response = new EiResponse(200, "OK");
	}
	
	
	public void print() {
		String printStringFormat = 
				"EICanceledTenderPayload  partyId %d counterPartyId %d refId %d ";
		
		System.err.println(
				String.format(printStringFormat, 
				partyId.value(), 
				counterPartyId.value(), 
				response.getRefId()));
	}
	
	public String toString()	{
		return "EICanceledTenderPayload Soure RefId " +
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

	public ActorIdType getPartyId() {
		return partyId;
	}

	public ActorIdType getCounterPartyId() {
		return counterPartyId;
	}
	
	

}
