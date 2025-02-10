package org.theenergymashuplab.cts.controller.payloads;


import org.theenergymashuplab.cts.EiResponseType;
import org.theenergymashuplab.cts.RefIdType;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:40 PM
 */
public class EiRejectedQuotePayload {

	public EiResponseType eiResponseType;
	public RefIdType inResponseTo;

	public EiRejectedQuotePayload(){

	}

	public EiRejectedQuotePayload(EiResponseType eiResponseType, RefIdType inResponseTo) {
		this.eiResponseType = eiResponseType;
		this.inResponseTo = inResponseTo;
	}

	public EiResponseType getEiResponseType() {
		return eiResponseType;
	}

	public void setEiResponseType(EiResponseType eiResponseType) {
		this.eiResponseType = eiResponseType;
	}

	public RefIdType getInResponseTo() {
		return inResponseTo;
	}

	public void setInResponseTo(RefIdType inResponseTo) {
		this.inResponseTo = inResponseTo;
	}

	@Override
	public String toString() {
		return "EiRejectedQuotePayload{" +
				"eiResponseType=" + eiResponseType +
				", inResponseTo=" + inResponseTo +
				'}';
	}
}
