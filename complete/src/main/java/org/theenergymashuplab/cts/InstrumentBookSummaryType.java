package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:41 PM
 */
public class InstrumentBookSummaryType {

	public BookEntryType bookEntries;

	public InstrumentBookSummaryType(){

	}

	public InstrumentBookSummaryType(BookEntryType bookEntries) {
		this.bookEntries = bookEntries;
	}

	public BookEntryType getBookEntries() {
		return bookEntries;
	}

	public void setBookEntries(BookEntryType bookEntries) {
		this.bookEntries = bookEntries;
	}

	@Override
	public String toString() {
		return "InstrumentBookSummaryType{" +
				"bookEntries=" + bookEntries +
				'}';
	}
}
