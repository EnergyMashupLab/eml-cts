package org.theenergymashuplab.cts.controller.payloads;

import java.util.ArrayList;

public class ClientCancelQuotePayload{
	private ArrayList<Long> marketQuoteIds;

	//JSON
	public ClientCancelQuotePayload(){

	}

	public ArrayList<Long> getMarketQuoteIds(){
		return this.marketQuoteIds;
	}

	public void setMarketQuoteIds(ArrayList<Long> marketQuoteIds){
		this.marketQuoteIds = marketQuoteIds;
	}
}

