package org.theenergymashuplab.cts.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
//import javax.validation.constraints.NotNull;

// Compound Key for the Entity is position_party and start_time NOT USED

//@Entity
// not needed?		//@Table(name="Position")
//@IdClass(PositionId.class)
public class PositionSummaryModel {
	
	@Id
	@Column(name = "ID")
	private long id;
	
	@Column(name = "position_Party")
	private long positionParty;

	@Column(name = "start_time")
	private Instant startTime;
	
	@Column(name =  "duration_minutes")
	private long durationInMinutes;
	
	@Column(name = "TotalQuantity")
	private long totalQuantity;
	
	// Class Constructor
	public PositionSummaryModel(
			long positionParty,
			long totalQuantity,
			Instant startTime,
			long durationinSeconds,
			long unitPrice,
			long totalCost
			)	{
		this.positionParty = positionParty;
		this.totalQuantity = totalQuantity;
		this.startTime = startTime;
		this.durationInMinutes = durationinSeconds/60;
	}

	
	//Default Constructor.
	public PositionSummaryModel() {
		this.positionParty = 0;
		this.totalQuantity = 0;
		this.startTime = null;
		this.durationInMinutes = 0;
		this.durationInMinutes = 0;
	}
	
	@Override
	public String toString()	{
//		return "PositionManagerModel counterId " + counterId +
				
		return "PositionManagerModel " +
				" positionParty " + positionParty +
				" startTime " + startTime.toString()
				+ "durationInMinutes " + durationInMinutes;
	}

	public long getPositionParty() {
		return positionParty;
	}

	public long getTotalQuantity() {
		return totalQuantity;
	}

	public Instant getStartTime() {
		return startTime;
	}
	
	public long getDurationInMinutes() {
		return durationInMinutes;
	}
	
}
