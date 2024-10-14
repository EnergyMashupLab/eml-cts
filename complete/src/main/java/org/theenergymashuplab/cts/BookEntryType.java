package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:39 PM
 */
public class BookEntryType {

	public ActorIdType counterParty;
	public ActorIdType party;
	public long price;
	public long quantity;
	public SideType side;

	public BookEntryType(){

	}

	public BookEntryType(ActorIdType counterParty, ActorIdType party, long price, long quantity, SideType side) {
		this.counterParty = counterParty;
		this.party = party;
		this.price = price;
		this.quantity = quantity;
		this.side = side;
	}

	public ActorIdType getCounterParty() {
		return counterParty;
	}

	public void setCounterParty(ActorIdType counterParty) {
		this.counterParty = counterParty;
	}

	public ActorIdType getParty() {
		return party;
	}

	public void setParty(ActorIdType party) {
		this.party = party;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public SideType getSide() {
		return side;
	}

	public void setSide(SideType side) {
		this.side = side;
	}

	@Override
	public String toString() {
		return "BookEntryType{" +
				"counterParty=" + counterParty +
				", party=" + party +
				", price=" + price +
				", quantity=" + quantity +
				", side=" + side +
				'}';
	}
}
