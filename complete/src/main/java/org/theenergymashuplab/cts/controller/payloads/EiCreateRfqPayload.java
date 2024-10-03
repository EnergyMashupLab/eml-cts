package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:39 PM
 */
public class EiCreateRfqPayload {

	public ActorIdType counterPartyId;
	public ActorIdType partyId;
	public RefIdType requestId;
	public boolean requestPrivate;
	public boolean requestPublication;
	public EiRfqType rfq;

	public EiCreateRfqPayload(){

	}

	public void finalize() throws Throwable {

	}

}