/*
 * Copyright 2019-2020 The Energy Mashup Lab
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.theenergymashuplab.cts;

import java.util.*;

public class EiReplyPosition {
	private ActorIdType positionParty = null;
	private ActorIdType requestor = null;
	private Interval boundingInterval = null;
	private UidType request;
	private ArrayList<EiPosition> positionList = new ArrayList<EiPosition>();
	private UidType requestId = new UidType();
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
		String formattedString = 
				"EiReplyPosition party '%s' requestor '%s' request %d boundingInterval '%s' positionList %s";

		String.format(formattedString,
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

	public UidType getRequest() {
		return request;
	}

	public void setRequest(UidType request) {
		this.request = request;
	}

	public ArrayList<EiPosition> getPositionList() {
		return positionList;
	}

	public void setPositionList(ArrayList<EiPosition> positionList) {
		this.positionList = positionList;
	}

	public UidType getRequestId() {
		return requestId;
	}

	public void setRequestId(UidType requestId) {
		this.requestId = requestId;
	}

	public EiResponse getResponse() {
		return response;
	}

	public void setResponse(EiResponse response) {
		this.response = response;
	}
	
}
