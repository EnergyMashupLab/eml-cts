package com.example.restservice;

public class RefIdType extends UidType {

	public long value() {
		return this.myUidId;
	}
	
	@Override
	public String toString()	{
		return ("RefId: " + String.valueOf(myUidId));
	}
	
	// NEED SETTERS AND GETTERS FOR JSON SERIALIZATION?
}