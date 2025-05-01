package org.theenergymashuplab.cts;

public enum ResourceDesignatorType {
	   POWER((short) 1),
	    ENERGY((short) 2),
	    TRANSPORT((short) 3),
	    WATER_PRESSURE((short) 4),
	    WATER_FLOW((short) 5),
	    GAS_PRESSURE((short) 6),
	    GAS_FLOW((short) 7),
	    BANDWIDTH((short) 8),
		NULL_VAL((short)255);

    private final short value;

    ResourceDesignatorType(short value) {
        this.value = value;
    }
    public short getValue() {
        return value;
    }
    
    public static ResourceDesignatorType fromSbe(short value) {
        return switch (value) {
            case 1 -> POWER;
            case 2 -> ENERGY;
            case 3 -> TRANSPORT;
            case 4 -> WATER_PRESSURE;
            case 5 -> WATER_FLOW;
            case 6 -> GAS_PRESSURE;
            case 7 -> GAS_FLOW;
            case 8 -> BANDWIDTH;
            case (short) 255 -> NULL_VAL;
            default -> throw new IllegalArgumentException("Unknown ResourceDesignatorType: " + value);
        };
    }

}