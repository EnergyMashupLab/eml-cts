package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

public class ActorId {
	private long actorId;
	private static final AtomicLong actorIdCounter = new AtomicLong();
	
	ActorId(long actorId){
		this.actorId = actorId;
		actorIdCounter.set(actorId + 1); 	//to avoid future collisions
		
//		System.err.format("Creating ActorId = %d", this.actorId); System.err.println();
	}
	
	/*
	 * No parameters - uses actorIdCounter to determine the actorId
	 */
	ActorId () {
		actorId = actorIdCounter.incrementAndGet();
//		System.err.format("Creating ActorId = %d", this.actorId); System.err.println();
	}

	public long getActorId() {
		return actorId;
	}

	public void setActorId(long actorId) {
		this.actorId = actorId;
	}

	public static AtomicLong getActoridcounter() {
		return actorIdCounter;
	}
}