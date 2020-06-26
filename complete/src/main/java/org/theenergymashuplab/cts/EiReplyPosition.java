package org.theenergymashuplab.cts;

import java.util.*;

public class EiReplyPosition {
	private ActorIdType positionParty = null;
	private ActorIdType requestor = null;
	private Interval boundingInterval = null;
	private RefIdType request;
	private ArrayList<EiPosition> positionList = new ArrayList<EiPosition>();
	private RefIdType requestId = new RefIdType();
	public EiResponse response;
	
	// Superceded by updated PositionManager - delete in future release
	
	/*
	 * For queries to the position manager - the bounding interval for position information.
	 * A position is for a particular time so product can be acquired in advance
	 * Initial draft is <Interval, value> pairs
	 */
	EiReplyPosition()	{
		// for JSON serialization - uses setters and getters
		// attrubutes left as initialized to null
	}
	
	EiReplyPosition(Interval boundingInterval, ArrayList<EiPosition> positionList, EiResponse response)	{
		// will have a list of positions passed in as an ArrayList<EiPosition>
		this.boundingInterval = boundingInterval;
		this.positionList = positionList;
		this.response = response;
	}
	
	public String toString()	{
		String tempString;
		String formattedString = 
				"EiReplyPosition party '%s' requestor '%s' request %d boundingInterval '%s' positionList %s";

		formattedString.format(formattedString,
				positionParty.toString(), requestor.toString(),
				request.toString(), positionList.toString());
		
		return formattedString;
	}

	public ActorIdType getPositionParty() {
		return positionParty;
	}

	public void setPositionParty(ActorIdType positionParty) {
		this.positionParty = positionParty;
	}

	public ActorIdType getRequestor() {
		return requestor;
	}

	public void setRequestor(ActorIdType requestor) {
		this.requestor = requestor;
	}

	public Interval getBoundingInterval() {
		return boundingInterval;
	}

	public void setBoundingInterval(Interval boundingInterval) {
		this.boundingInterval = boundingInterval;
	}

	public RefIdType getRequest() {
		return request;
	}

	public void setRequest(RefIdType request) {
		this.request = request;
	}

	public ArrayList<EiPosition> getPositionList() {
		return positionList;
	}

	public void setPositionList(ArrayList<EiPosition> positionList) {
		this.positionList = positionList;
	}

	public RefIdType getRequestId() {
		return requestId;
	}

	public void setRequestId(RefIdType requestId) {
		this.requestId = requestId;
	}

	public EiResponse getResponse() {
		return response;
	}

	public void setResponse(EiResponse response) {
		this.response = response;
	}
	
}
