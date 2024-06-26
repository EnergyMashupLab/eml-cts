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

import org.theenergymashuplab.cts.Interval;

import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.CtsStreamType;
import org.theenergymashuplab.cts.EiResponse;

public class EiReplyPositionPayload {
    private Interval boundingInterval;
    private ActorIdType positionParty;
    private CtsStreamType positions;
    private ActorIdType requestor;
    private EiResponse response;


    // Default initializer for JSON serialization
    public EiReplyPositionPayload() {
        this.positionParty = new ActorIdType();
        this.positions = new CtsStreamType();
        this.requestor = new ActorIdType();
        this.response = new EiResponse();
    }

    public EiReplyPositionPayload(Interval boundingInterval, ActorIdType positionParty, CtsStreamType positions, ActorIdType requestor, EiResponse response) {
        this.boundingInterval = boundingInterval;
        this.positionParty = positionParty;
        this.positions = positions;
        this.requestor = requestor;
        this.response = response;
    }

    public void setBoundingInterval(Interval boundingInterval) {
        this.boundingInterval = boundingInterval;
    }
    
    public Interval getBoundingInterval() {
        return boundingInterval;
    }

    public void setPositionParty(ActorIdType positionParty) {
        this.positionParty = positionParty;
    }

    public ActorIdType getPositionParty() {
        return positionParty;
    }

    public void setPositions(CtsStreamType positions) {
        this.positions = positions;
    }
    
    public CtsStreamType getPositions() {
        return positions;
    }

    public void setRequestor(ActorIdType requestor) {
        this.requestor = requestor;
    }

    public ActorIdType getRequestor() {
        return requestor;
    }

    public void setResponse(EiResponse response) {
        this.response = response;
    }
    
    public EiResponse getResponse() {
        return response;
    }
}
