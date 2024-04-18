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

//TODO: Add comments, set & get functions, EiRequestPositionPayload TODOs
package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.EiResponseType;

public class EiReplyPositionPayload {
    private IntervalType boundingInterval;
    private ActorIdType positionParty;
    private CtsStream positions;
    private ActorIdType requestor;
    private EiResponseType response;

    public EiReplyPositionPayload() {
        this.boundingInterval = new IntervalType();
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
}
