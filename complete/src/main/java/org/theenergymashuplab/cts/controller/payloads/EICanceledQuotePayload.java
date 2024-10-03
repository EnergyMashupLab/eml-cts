package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:39 PM
 */
public class EICanceledQuotePayload {

	public ActorIdType counterPartyId;
	public EiCanceledResponseType eiCanceledResponse;
	public EiResponseType eiResponse;
	public RefIdType inResponseTo;
	public ActorIdType partyId;

	public EICanceledQuotePayload(){

	}

	public void finalize() throws Throwable {

	}

}