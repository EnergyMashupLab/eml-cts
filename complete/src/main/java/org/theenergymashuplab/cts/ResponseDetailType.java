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

public enum ResponseDetailType {
    UNSPECIFIED((short) 1),
    RULES_VIOLATION((short) 2),
    INVALID_REFERENCE((short) 3),
    DUPLICATE((short) 4),
    TRADING_CLOSED((short) 5),
    PARTY_RESTRICTED((short) 6),
    INVALID_INSTRUMENT((short) 7),
    FORCE_MAJEURE((short) 8),
    INVALID_MARKET((short) 9),
    INVALID_SEGMENT((short) 10),
    SUCCESS((short) 11),
    NOT_AUTHORIZED((short) 12),
    INVALID_ARTIFACT((short) 13),
    NULL_VAL((short) 255); // Optional: for matching SBE NULL

    private final short value;

    ResponseDetailType(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public static ResponseDetailType fromSbe(short sbeValue) {
        for (ResponseDetailType type : values()) {
            if (type.value == sbeValue) return type;
        }
        throw new IllegalArgumentException("Unknown ResponseDetailType SBE value: " + sbeValue);
    }
}
