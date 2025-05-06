package org.theenergymashuplab.cts;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TickerType {
    @JsonProperty("QUOTES")
    QUOTES((short) 1),
    @JsonProperty("RFQS")
    RFQS((short) 2),
    @JsonProperty("TENDERS")
    TENDERS((short) 3),
    @JsonProperty("TRANSACTIONS")
    TRANSACTIONS((short) 4);

    private final short value;

    TickerType(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    //For SBE
    public static TickerType fromSbe(short value) {
        for (TickerType type : TickerType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown TickerType value: " + value);
    }
}
