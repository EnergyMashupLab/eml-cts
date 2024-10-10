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
	public WarrantIdType warrantId;

	public ProductType(){

	}

	public ProductType(DurationType duration, int quantityScale, WarrantIdType warrantId) {
		this.duration = duration;
		this.quantityScale = quantityScale;
		this.warrantId = warrantId;
	}

	public DurationType getDuration() { return duration; }

	public void setDuration(DurationType duration) { this.duration = duration; }

	public int getQuantityScale() { return quantityScale; }

	public void setQuantityScale(int quantityScale) { this.quantityScale = quantityScale; }

	public WarrantIdType getWarrantId() { return warrantId; }

	public void setWarrantId(WarrantIdType warrantId) { this.warrantId = warrantId; }

	@Override
	public String toString() {
		return "ProductType {" +
				" duration = " + duration +
				", quantityScale = " + quantityScale +
				", warrantId = " + warrantId + " }";
	}
}
