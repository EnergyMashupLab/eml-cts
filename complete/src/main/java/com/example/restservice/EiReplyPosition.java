package com.example.restservice;

import java.util.*;

public class EiReplyPosition {
	private ActorIdType party = null;
	private ActorIdType requestor = null;
	private Interval boundingInterval = null;
	private RefIdType request;
	private ArrayList<EiPosition> positionList = new ArrayList<EiPosition>();
	
	/*
	 * For queries to the position manager - the bounding interval for position information.
	 * A position is for a particular time so product can be acquired in advance
	 * Initial draft is <Interval, value> pairs
	 */
	EiReplyPosition()	{
		// for JSON serialization - uses setters and getters
		// attrubutes left as initialized to null
	}
	
	EiReplyPosition(Interval boundingInterval, ArrayList<EiPosition> positionList)	{
		// will have a list of positions passed in as an ArrayList<EiPosition>
		this.boundingInterval = boundingInterval;
		this.positionList = positionList;
	}
	
	public String toString()	{
		String tempString;
		String formattedString = 
				"EiReplyPosition party '%s' requestor '%s' request %d boundingInterval '%s' positionList %s";

		formattedString.format(formattedString,
				party.toString(), requestor.toString(),
				request.toString(), positionList.toString());
		
		return formattedString;
	}
}