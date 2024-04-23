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
//TODO: Add comments
 package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.Interval;
import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.MarketIdType;

public class EiRequestPositionPayload {
    private Interval boundingInterval;
    private MarketIdType marketId;
    private ActorIdType positionParty;
    private RefIdType requestId;
    private ActorIdType requestor;
    private ResourceDesignator resourceDesignator;
     
     // Default initializer for JSON serialization
    public EiRequestPositionPayload() {
        this.marketId = new MarketIdType();
        this.positionParty = new ActorIdType();
        this.requestor = new ActorIdType();
    }
 
    public EiRequestPositionPayload(Interval boundingInterval, MarketIdType marketId, ActorIdType positionParty, RefIdType requestId, ActorIdType requestor, ResourceDesignator resourceDesignator)	{
        this.boundingInterval = boundingInterval;
        this.marketId = marketId;
        this.positionParty = positionParty;
        this.requestId = requestId;
        this.requestor = requestor;
        this.resourceDesignator = resourceDesignator;
    }

    @Override
    public String toString() {
        return("EiRequestPosition boundingInterval " +
        boundingInterval.toString() +
        " marketId " +
        marketId.value() +
        " positionParty " +
        positionParty.value() + 
        " requestId " +
        requestId.toString() +
        " requestor " +
        requestor.value() +
        " resourceDesignator " +
        resourceDesignator.toString());
    }
     /* 
      * 
      * 
      * 
      * 
      * 
      */
    public void setBoundingInterval(Interval boundingInterval) {
        this.boundingInterval = boundingInterval;
    }
    
    public Interval getBoundingInterval() {
        return boundingInterval;
    }

    public void setMarketId(MarketIdType marketId) {
        this.marketId = marketId;
    }

    public MarketIdType getMarketId() {
        return marketId;
    }

    public void setPositionParty(ActorIdType positionParty) {
        this.positionParty = positionParty;
    }

    public ActorIdType getPositionParty() {
        return positionParty;
    }

    public void setRequestId(RefIdType requestId) {
        this.requestId = requestId;
    }

    public RefIdType getRequestId() {
        return requestId;
    }

    public void setRequestor(ActorIdType requestor) {
        this.requestor = requestor;
    }

    public ActorIdType getRequestor() {
        return requestor;
    }

    public void setResourceDesignator(ResourceDesignator resourceDesignator) {
        this.resourceDesignator = resourceDesignator;
    }

    public ResourceDesignator getResourceDesignator() {
        return resourceDesignator;
    } 
 }
 