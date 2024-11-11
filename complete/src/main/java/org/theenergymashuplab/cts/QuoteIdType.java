package org.theenergymashuplab.cts;

public class QuoteIdType extends UidType {

    public long value() {
        return this.myUidId;
    }

    public QuoteIdType()	{
    }

    @Override
    public String toString()	{
        return String.valueOf(myUidId);
    }

    // NEED SETTERS AND GETTERS FOR JSON SERIALIZATION?

}
