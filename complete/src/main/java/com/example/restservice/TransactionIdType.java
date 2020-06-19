package com.example.restservice;

public class TransactionIdType extends UidType {
	
	public long value() {
		return this.myUidId;
	}
	
	@Override
	public String toString()	{
		return (String.valueOf(myUidId));
	}

	// NEED SETTERS AND GETTERS FOR JSON SERIALIZATION?

}
