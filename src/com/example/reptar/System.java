package com.example.reptar;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Date;

public class System {
	int stop = 2620800;
	Date RealTime;
	SimulationDate SimTime;
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
		Event currentEvent = new Event();
		while (Continue()){
			currentEvent = queue.poll();
			if(currentEvent != null) {
				
			}else {
				
			}
		}
		
	}
	
	
	/* Check all the exit conditions and returns false 
	 * if simulation should stop.
	 */
	private boolean Continue(){
		if (SimTime.getTime() > stop ) return false;
		
		return true;
	}
}
