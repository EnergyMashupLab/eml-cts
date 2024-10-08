package org.theenergymashuplab.cts;

import org.theenergymashuplab.cts.controller.payloads.TickerPayloadBase;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:41 PM
 */
public class QuoteTickerType extends TickerPayloadBase {

	public EiQuoteType quote;

	public QuoteTickerType(){

	}

	public QuoteTickerType(EiQuoteType quote){
		this.quote = quote;
	}

	public void setQuote(EiQuoteType quote) { this.quote = quote; }

	public EiQuoteType getQuote() { return quote; }

}
