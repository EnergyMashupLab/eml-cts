package org.theenergymashuplab.cts;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:42 PM
 */
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TickerType {
	@JsonProperty("QUOTES")
	QUOTES,
	@JsonProperty("RFQS")
	RFQS,
	@JsonProperty("TENDERS")
	TENDERS,
	@JsonProperty("TRANSACTIONS")
	TRANSACTIONS
}
