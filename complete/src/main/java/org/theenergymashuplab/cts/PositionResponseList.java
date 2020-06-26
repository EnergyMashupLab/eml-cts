package org.theenergymashuplab.cts;

import org.theenergymashuplab.cts.controller.payloads.*;
import java.util.ArrayList;
import java.util.List;


public class PositionResponseList {
	private ArrayList<PositionGetPayload> responseList;
	
	PositionResponseList()	{
		responseList = new ArrayList<>();
	}
	
	@Override
	public String  toString()	{
		String outString = new String();
		
		for (int i = 0; i < responseList.size(); i++)	{
			outString = outString + " '" + responseList.get(i);
		}
		
		return outString;
	}
	
	public long getFirstPositionQuantity()	{
		return responseList.get(0).getQuantity();
	}

	public ArrayList<PositionGetPayload> getResponseList() {
		return responseList;
	}

	public void setResponseList(ArrayList<PositionGetPayload> responseList) {
		this.responseList = responseList;
	}
	

}
