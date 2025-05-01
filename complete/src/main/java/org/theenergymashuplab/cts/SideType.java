package org.theenergymashuplab.cts;

public enum SideType {
    BUY((byte) 66),
    SELL((byte) 83);

    private final byte value;

    SideType(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    public static SideType fromSbe(org.theenergymashuplab.cts.generated_files.SideType sbeEnum) {
        return switch (sbeEnum) {
            case BUY -> BUY;
            case SELL -> SELL;
            default -> null;
        };
    }
}
