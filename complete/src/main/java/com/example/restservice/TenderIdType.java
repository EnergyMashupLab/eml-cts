package com.example.restservice;

public class TenderIdType extends RefIdType	{
	
	public long value() {
		return this.myUidId;
	}
	
	public TenderIdType()	{
	}
	
	@Override
	public String toString()	{
		return String.valueOf(myUidId);
	}

	// NEED SETTERS AND GETTERS FOR JSON SERIALIZATION?
	
}