package com.example.restservice;

import java.util.*;

public class EiPosition {
	private static Interval boundingInterval;
	private static PositionElement positions[];
	private static ArrayList<PositionElement> positionList = new ArrayList<PositionElement>();
	/*
	 * For queries to the position manager - the bounding interval for position information.
	 * A position is for a particular time so product can be acquired in advance
	 * Initial draft is <Interval, value> pairs
	 */

	
	EiPosition(Interval boundingInterval, PositionElement positions[])	{
		/* will have an ordered list of positions - consider Java collection classes */
		this.boundingInterval = boundingInterval;
		this.positions = positions;
	}

}
