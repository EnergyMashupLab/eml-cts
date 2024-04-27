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

package org.theenergymashuplab.cts;

/*
 * While EiTransaction extends EiTender in the base standards, in Java
 * we use the "has a" construction because the EiTender exists before the
 * EiTransaction does, so the constructor cannot create a new EiTender
 */

public class EiTransaction {
	private final TransactionIdType transactionId = new TransactionIdType();	// will be passed to constructor
	private EiTenderType tender;
	private TransactiveState transactiveState = TransactiveState.TRANSACTION;
	
	/*
	 * Default constructor for JSON deserialization.
	 */
	public EiTransaction()	{		
	}

	public EiTransaction(EiTenderType tender) {
//		System.err.println("in EiTransaction Constructor tenderId " + tender.getTenderId().getTenderId());
		this.tender = tender;
//		this.print();
	}

	public TransactionIdType getTransactionId() {
		return this.transactionId;
	}
	
	public EiTenderType getTender()	{
		return tender;
	}
	
	public String toString() {
		return ("EiTransaction transactionId " + this.transactionId.toString() +
				 " tenderId " + this.tender.getTenderId().value());
	}

	public TransactiveState getTransactiveState() {
		return transactiveState;
	}

	public void setTransactiveState(TransactiveState transactiveState) {
		this.transactiveState = transactiveState;
	}	
	
}
