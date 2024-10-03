package org.theenergymashuplab.cts;


/**
 * HOW  -- the resource is packaged for trading
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:41 PM
 */
public class ProductType extends ResourceType {

	public DurationType duration;
	public int quantityScale;
	public WarantIdType warrantId;

	public ProductType(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}