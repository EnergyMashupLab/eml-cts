package com.example.restservice;

/* for position manager interactions 
 * This will be a list, each within the bounding interval in EiRequestPostion
 */
public class PositionElement {
	Interval interval;
	long position;
	
	PositionElement(Interval interval, long position)	{
		this.interval = interval;
		this.position = position;
	}
}