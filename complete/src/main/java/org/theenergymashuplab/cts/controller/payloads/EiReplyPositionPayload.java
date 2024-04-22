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

//TODO: Add comments, create CtsStream class
package org.theenergymashuplab.cts.controller.payloads;

import org.hibernate.query.sqm.IntervalType;
import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.EiResponseType;

public class EiReplyPositionPayload {
    private IntervalType boundingInterval;
    private ActorIdType positionParty;
    private CtsStream positions;
    private ActorIdType requestor;
    private EiResponseType response;


    // Default initializer for JSON serialization
    public EiReplyPositionPayload() {
        this.boundingInterval = IntervalType.SECOND;
        this.positionParty = new ActorIdType();
        this.positions = CtsStream positions;
        this.requestor = new ActorIdType();
        this.response = new EiResponseType();
    }

    public EiReplyPositionPayload(IntervalType boundingInterval, ActorIdType positionParty, CtsStream positions, ActorIdType requestor, EiResponseType response) {
        this.boundingInterval = boundingInterval;
        this.positionParty = positionParty;
        this.positions = positions;
        this.requestor = requestor;
        this.response = response;
    }

    public void setBoundingInterval(IntervalType boundingInterval) {
        this.boundingInterval = boundingInterval;
    }
    
    public IntervalType getBoundingInterval() {
        return boundingInterval;
    }

    public void setPositionParty(ActorIdType positionParty) {
        this.positionParty = positionParty;
    }

    public ActorIdType getPositionParty() {
        return positionParty;
    }

    public void setPositions(CtsStream positions) {
        this.positions = positions;
    }
    
    public void getPositions() {
        return positions;
    }

    public void setRequestor(ActorIdType requestor) {
        this.requestor = requestor;
    }

    public ActorIdType getRequestor() {
        return requestor;
    }

    public void setResponse(EiResponseType response) {
        this.response = response;
    }
    
    public EiResponseType getResponse() {
        return response;
    }
}
