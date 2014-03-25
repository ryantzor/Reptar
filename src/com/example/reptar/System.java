package com.example.reptar;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Date;

public class System {
	int stopTime = 2620800;
	boolean stopNow = false;
	Date RealTime;
	
	SimulationDate simTime;
	Comparator<Event> comparator; //= new something;
	PriorityQueue<Event> queue; //= new PriorityQueue<Event>(10, comparator);
	
	/* System Constructor
	 * Initiate Event Queue
	 * Initiate Environment
	 * Initiate Organism list
	 */
	System(Initial x) {
		
	}
	
	/* Starts the simulation
	 * Loops while Continue() is True
	 * gets next event form queue
	 * set simTime to time of new event
	 * Runs the event.
	 */
	void start() {
		Event currentEvent;// = new Event();
		while (Continue()){
			currentEvent = queue.poll();
			if(currentEvent != null) {
				simTime = currentEvent.getTime();
				currentEvent.Happen();
			}else {
				stopNow = true;
			}
		}
		
	}
	
	
	/* Check all the exit conditions and returns false 
	 * if simulation should stop.
	 */
	private boolean Continue(){
		if (simTime.getTime() > stopTime ) return false;
		if (stopNow) return false;
		
		return true;
	}
}
