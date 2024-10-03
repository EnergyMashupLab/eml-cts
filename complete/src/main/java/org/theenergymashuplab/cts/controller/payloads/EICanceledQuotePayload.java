package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.EiCanceledResponseType;
import org.theenergymashuplab.cts.EiResponseType;
import org.theenergymashuplab.cts.RefIdType;

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
}
