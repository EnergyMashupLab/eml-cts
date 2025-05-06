package org.theenergymashuplab.cts;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum SubscriptionActionType {
    @JsonProperty("SNAPSHOT")
    SNAPSHOT((short) 1),

    @JsonProperty("SNAPSHOT_AND_UPDATES")
    SNAPSHOT_AND_UPDATES((short) 2),

    @JsonProperty("CANCEL")
    CANCEL((short) 3),

    NULL_VAL((short) 255); // Optional fallback

    private final short value;

    SubscriptionActionType(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public static SubscriptionActionType fromSbe(short value) {
        for (SubscriptionActionType type : values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return NULL_VAL;
    }
}
