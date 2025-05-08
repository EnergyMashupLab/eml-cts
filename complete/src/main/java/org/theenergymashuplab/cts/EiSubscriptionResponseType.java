/*
 * Copyright 2019-2025 The Energy Mashup Lab
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.theenergymashuplab.cts;

public abstract class EiSubscriptionResponseType {

	public String multicastListenReference;
	public EiResponseType response;
	public SubscriptionActionType subscriptionActionTaken;
	public RefIdType subscriptionRequestId;

	public EiSubscriptionResponseType() {

	}

	public EiSubscriptionResponseType(String multicastListenReference, SubscriptionActionType subscriptionActionTaken,
			EiResponseType response, RefIdType subscriptionRequestId) {
		this.multicastListenReference = multicastListenReference;
		this.subscriptionActionTaken = subscriptionActionTaken;
		this.response = response;
		this.subscriptionRequestId = subscriptionRequestId;
	}

	public String getMulticastListenReference() {
		return multicastListenReference;
	}

	public void setMulticastListenReference(String multicastListenReference) {
		this.multicastListenReference = multicastListenReference;
	}

	public EiResponseType getResponse() {
		return response;
	}

	public void setResponse(EiResponseType response) {
		this.response = response;
	}

	public SubscriptionActionType getSubscriptionActionTaken() {
		return subscriptionActionTaken;
	}

	public void setSubscriptionActionTaken(SubscriptionActionType subscriptionActionTaken) {
		this.subscriptionActionTaken = subscriptionActionTaken;
	}

	public RefIdType getSubscriptionRequestId() {
		return subscriptionRequestId;
	}

	public void setSubscriptionRequestId(RefIdType subscriptionRequestId) {
		this.subscriptionRequestId = subscriptionRequestId;
	}

	@Override
	public String toString() {
		return "EiSubscriptionResponseType{" + "multicastListenReference='" + multicastListenReference + '\''
				+ ", response=" + response + ", subscriptionActionTaken=" + subscriptionActionTaken
				+ ", subscriptionRequestId=" + subscriptionRequestId + '}';
	}
}
