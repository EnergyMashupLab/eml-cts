/*
 * 
 * Updated by: Dhruvin Desai
 * 
 */

package org.theenergymashuplab.cts.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Position")
public class PositionManagerModel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "counter_id")
	private long counterId;
	
	@Column(name = "position_Party")
	private long positionParty;

	@Column(name = "Transaction_id")
	private long transactionId;
	
	@Column(name = "Quantity")
	private long quantity;
	
	@Column(name = "Start_Time")
	@NotNull
	private Instant startTime;
	
	@Column(name = "End_Time")
	@NotNull
	private Instant endTime;
	
	@Column(name =  "duration_minutes")
	@NotNull
	private long durationInMinutes;
	
	@Column(name = "unitPrice")
	private long unitPrice;
	
	@Column(name = "counter_Party")
	private long counterParty;

	// Class Constructor
	public PositionManagerModel(
			long positionParty,
			long transaction_id,
			long quantity,
			Instant startTime,
			long durationinSeconds,
			long unitPrice,
			long counterParty) {
		this.positionParty = positionParty;
		this.transactionId = transaction_id; // Always 0 at this point of time.(Future updated)
		this.quantity = quantity;
		this.startTime = startTime;
		this.endTime = startTime.plusSeconds(durationinSeconds);
		this.durationInMinutes = durationinSeconds/60;
		this.unitPrice = unitPrice;
		this.counterParty = counterParty;
	}

	
	//Default Constructor.
	public PositionManagerModel() {
		this.positionParty = 0;
		this.transactionId = 0;
		this.quantity = 0;
		this.startTime = null;
		this.endTime = null;
		this.durationInMinutes = 0;
		this.unitPrice = 0;;
		this.counterParty = 0;
	}
	
	@Override
	public String toString()	{
		return "PositionManagerModel counterId " + counterId +
				" positionParty " + positionParty +
				" counterParty " + counterParty +
				" quantity " + quantity +
				" unitPrice " + unitPrice +
				" transactionId " + transactionId +
				" startTime " + startTime.toString() +
				" endTime " + endTime.toString()
				;
	}
	
	/**
	 * @return the startTime
	 */
	public Instant getStartTime() {
		return startTime;
	}

	/**
	 * @return the counterId
	 */
	public long getCounterId() {
		return counterId;
	}

	/**
	 * @param counterId the counterId to set
	 */
	public void setCounterId(long counterId) {
		this.counterId = counterId;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Instant startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the positionParty
	 */
	public long getPositionParty() {
		return positionParty;
	}

	/**
	 * @param positionParty the positionParty to set
	 */
	public void setPositionParty(long positionParty) {
		this.positionParty = positionParty;
	}

	/**
	 * @return the endTime
	 */
	public Instant getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Instant endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the transactionId
	 */
	public long getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the quantity
	 */
	public long getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}


	public long getDurationInMinutes() {
		return durationInMinutes;
	}


	public void setDurationInMinutes(long durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}


	public long getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(long unitPrice) {
		this.unitPrice = unitPrice;
	}


	public long getCounterParty() {
		return counterParty;
	}


	public void setCounterParty(long counterParty) {
		this.counterParty = counterParty;
	}
	
}
