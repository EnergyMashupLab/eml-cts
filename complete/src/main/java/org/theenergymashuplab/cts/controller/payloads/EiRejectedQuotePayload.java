package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.EiResponseType;
import org.theenergymashuplab.cts.RefIdType;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:40 PM
 */
public class EiRejectedQuotePayload {

	EiResponseType eiResponse;
	RefIdType inResponseTo;

	public EiRejectedQuotePayload() {

	}

	public EiRejectedQuotePayload(EiResponseType eiResponse, RefIdType inResponseTo) {
		this.eiResponse = eiResponse;
		this.inResponseTo = inResponseTo;
	}

	public EiResponseType getEiResponseType() {
		return eiResponse;
	}

	public void setEiResponseType(EiResponseType eiResponse) {
		this.eiResponse = eiResponse;
	}

	public RefIdType getInResponseTo() {
		return inResponseTo;
	}

	public void setInResponseTo(RefIdType inResponseTo) {
		this.inResponseTo = inResponseTo;
	}

	@Override
	public String toString() {
		return "EiRejectedQuotePayload{" + "eiResponse=" + eiResponse + ", inResponseTo=" + inResponseTo + '}';
	}
}
