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
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.theenergymashuplab.cts;

/**
 * @author crossover
 * @version 1.0
 * @created 18-Feb-2025 11:30:37 AM
 */
public abstract class EiSubscriptionResponseType {

	public String multicastListenReference;
	public EiResponseType response;
	public SubscriptionActionType subscriptionActionTaken;
	public RefIdType subscriptionRequestId;

	public EiSubscriptionResponseType(){

	}

	public EiSubscriptionResponseType(String multicastListenReference, EiResponseType response,
									  SubscriptionActionType subscriptionActionTaken, RefIdType subscriptionRequestId) {
		this.multicastListenReference = multicastListenReference;
		this.response = response;
		this.subscriptionActionTaken = subscriptionActionTaken;
		this.subscriptionRequestId = subscriptionRequestId;
	}

	public void setMulticastListenReference(String multicastListenReference) {
		this.multicastListenReference = multicastListenReference;
	}

	public void setSubscriptionActionTaken(SubscriptionActionType subscriptionActionTaken) {
		this.subscriptionActionTaken = subscriptionActionTaken;
	}

	public void setResponse(EiResponseType response) {
		this.response = response;
	}

	public void setSubscriptionRequestId(RefIdType subscriptionRequestId) {
		this.subscriptionRequestId = subscriptionRequestId;
	}

	public RefIdType getSubscriptionRequestId() {
		return subscriptionRequestId;
	}

	public SubscriptionActionType getSubscriptionActionTaken() {
		return subscriptionActionTaken;
	}

	public EiResponseType getResponse() {
		return response;
	}

	public String getMulticastListenReference() {
		return multicastListenReference;
	}

	@Override
	public String toString() {
		return "EiSubscriptionResponseType{" +
				"multicastListenReference='" + multicastListenReference + '\'' +
				", response=" + response +
				", subscriptionActionTaken=" + subscriptionActionTaken +
				", subscriptionRequestId=" + subscriptionRequestId +
				'}';
	}

	//	public void finalize() throws Throwable {
//
//	}

}