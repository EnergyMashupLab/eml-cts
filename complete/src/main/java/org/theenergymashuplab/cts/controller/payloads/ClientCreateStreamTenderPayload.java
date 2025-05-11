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

package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.BridgeInstant;
import org.theenergymashuplab.cts.CtsStreamIntervalType;
import org.theenergymashuplab.cts.SideType;

import java.util.List;

public class ClientCreateStreamTenderPayload {
	private String info = "ClientCreateStreamTenderPayload";
	// Side that we are on
	private SideType side;
	private long ctsTenderId;

	// A list of our streamIntervals
	private List<CtsStreamIntervalType> streamIntervals;

	private BridgeInstant streamStart;
	private BridgeInstant bridgeExpireTime;
	private long intervalDurationInMinutes;

	// JSON
	public ClientCreateStreamTenderPayload() {}

	public ClientCreateStreamTenderPayload(String info, SideType side, long ctsTenderId,
			List<CtsStreamIntervalType> streamIntervals, BridgeInstant streamStart, BridgeInstant bridgeExpireTime,
			long intervalDurationInMinutes) {
		this.info = info;
		this.side = side;
		this.ctsTenderId = ctsTenderId;
		this.streamIntervals = streamIntervals;
		this.streamStart = streamStart;
		this.bridgeExpireTime = bridgeExpireTime;
		this.intervalDurationInMinutes = intervalDurationInMinutes;
	}

	@Override
	public String toString() {
		return "ClientCreateStreamTenderPayload{" + "info='" + info + '\'' + ", side=" + side + ", ctsTenderId="
				+ ctsTenderId + ", streamIntervals=" + streamIntervals + ", streamStart=" + streamStart
				+ ", bridgeExpireTime=" + bridgeExpireTime + ", intervalDurationInMinutes=" + intervalDurationInMinutes
				+ '}';
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public SideType getSide() {
		return side;
	}

	public void setSide(SideType side) {
		this.side = side;
	}

	public long getCtsTenderId() {
		return ctsTenderId;
	}

	public void setCtsTenderId(long ctsTenderId) {
		this.ctsTenderId = ctsTenderId;
	}

	public List<CtsStreamIntervalType> getStreamIntervals() {
		return this.streamIntervals;
	}

	public void setStreamIntervals(List<CtsStreamIntervalType> streamIntervals) {
		this.streamIntervals = streamIntervals;
	}

	public BridgeInstant getStreamStart() {
		return this.streamStart;
	}

	public void setStreamStart(BridgeInstant streamStart) {
		this.streamStart = streamStart;
	}

	public BridgeInstant getBridgeExpireTime() {
		return this.bridgeExpireTime;
	}

	public void setBridgeExpireTime(BridgeInstant bridgeExpireTime) {
		this.bridgeExpireTime = bridgeExpireTime;
	}

	public long getIntervalDurationInMinutes() {
		return this.intervalDurationInMinutes;
	}

	public void setIntervalDurationInMinutes(long intervalDurationInMinutes) {
		this.intervalDurationInMinutes = intervalDurationInMinutes;
	}

}
