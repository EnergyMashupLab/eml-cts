package org.theenergymashuplab.cts;

/* for position manager interactions 
 * This will be a list, each within the bounding interval in EiRequestPostion
 */

/*
 * Old position manager design
 * TODO rationalize or delete or integrate
 */
public class PositionElement {
	Interval interval;
	long position;
	
	PositionElement(Interval interval, long position)	{
		this.interval = interval;
		this.position = position;
	}
}
