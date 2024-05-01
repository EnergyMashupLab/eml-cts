/*
 * Copyright 2019-2020 The Energy Mashup Lab
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * 
 * Updated by: Dhruvin Desai
 * 
 */

package org.theenergymashuplab.cts.model;

import java.time.Instant;

import org.theenergymashuplab.cts.ResourceDesignator;

// TODO extend columns here

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

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

	@Column(name = "Market_id")
	private long marketId;
	
	@Column(name = "Start_Time")
	@NotNull
	private Instant startTime;
	
	@Column(name = "End_Time")
	@NotNull
	private Instant endTime;

	@Column(name = "Resource_Designator")
	@NotNull
	@Enumerated(EnumType.STRING)
	private ResourceDesignator resourceDesignator;

	// Class Constructor
	public PositionManagerModel(
			long positionParty,
			long transaction_id,
			long quantity,
			long marketId,
			Instant startTime,
			long durationinSeconds,
			ResourceDesignator resourceDesignator) {
		this.positionParty = positionParty;
		this.transactionId = transaction_id; // Always 0 at this point of time.(Future updated)
		this.quantity = quantity;
		this.marketId = marketId;
		this.startTime = startTime;
		this.endTime = startTime.plusSeconds(durationinSeconds);
		this.resourceDesignator = resourceDesignator;
	}
	
	//Default Constructor.
	public PositionManagerModel() {
		this.positionParty = 0;
		this.transactionId = 0;
		this.quantity = 0;
		this.marketId = 1;
		this.startTime = null;
		this.endTime = null;
		this.resourceDesignator = ResourceDesignator.ENERGY;
	}
	
	@Override
	public String toString()	{
		return "PositionManagerModel counterId " + counterId +
				" positionParty " + positionParty +
				" transactionId " + transactionId +
				" quantity " + quantity +
				" startTime " + startTime.toString() +
				" endTime " + endTime.toString() +
				" resourceDesignator " + resourceDesignator;
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
	
	public ResourceDesignator getResourceDesignator() {
		return this.resourceDesignator;
	}

	public void setResourceDesignator(ResourceDesignator resourceDesignator) {
		this.resourceDesignator = resourceDesignator;
	}
}
