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

public class CtsStreamIntervalType {
    private long streamIntervalPrice;
    private long streamIntervalQuantity;
    private int streamUid;

    public CtsStreamIntervalType() {
    }

    public CtsStreamIntervalType(long streamIntervalPrice, long streamIntervalQuantity, int streamUid) {
        this.streamIntervalPrice = streamIntervalPrice;
        this.streamIntervalQuantity = streamIntervalQuantity;
        this.streamUid = streamUid;
    }

    public long getStreamIntervalPrice() {
        return streamIntervalPrice;
    }

    public void setStreamIntervalPrice(long streamIntervalPrice) {
        this.streamIntervalPrice = streamIntervalPrice;
    }

    public long getStreamIntervalQuantity() {
        return streamIntervalQuantity;
    }

    public void setStreamIntervalQuantity(long streamIntervalQuantity) {
        this.streamIntervalQuantity = streamIntervalQuantity;
    }

    public int getStreamUid() {
        return streamUid;
    }

    public void setStreamUid(int streamUid) {
        this.streamUid = streamUid;
    }
}
