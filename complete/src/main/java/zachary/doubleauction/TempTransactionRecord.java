package zachary.doubleauction;

import org.theenergymashuplab.cts.EiTenderType;

public record TempTransactionRecord(EiTenderType buyTender, EiTenderType sellTender, long quantity, int clearingPrice) {
    @Override
    public String toString() {
        return "TempTransactionRecord {" +
                "buyTender=" + buyTender +
                ", sellTender=" + sellTender +
                ", quantity=" + quantity +
                ", clearingPrice=" + clearingPrice +
                '}';
    }
}
