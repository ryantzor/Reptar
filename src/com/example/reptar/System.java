package com.example.reptar;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Date;

public class System extends Thread {
	int stopTime;// = 2620800;
	boolean stopNow = false;
	private Date RealTime;
	private SimulationDate simTime;
	Comparator<Event> comparator; //= new something;
	private PriorityQueue<Event> queue; //= new PriorityQueue<Event>(10, comparator);
	private int speed = 10080;

	/* System Constructor
	 * Initiate Event Queue
	 * Initiate Environment
	 * Initiate Organism list
	 */
	System() {
		RealTime = new Date();
		comparator = new Comparator<Event>() {
			public int compare(Event a, Event b){
				if (a.getDate().getTime() > b.getDate().getTime()) return 1;
				if (a.getDate().getTime() < a.getDate().getTime()) return 0;
				return -1;
			}
		};
		queue = new PriorityQueue<Event>(10, comparator);
		stopTime = 2620800;
		//add start events to queue
		this.addEvent(new displayEvent(speed));//adds display Event at speed minutes
	}

	void addEvent(Event theEvent) {
		queue.add(theEvent);
	}

	/* Starts the simulation
	 * Loops while Continue() is True
	 * gets next event form queue
	 * set simTime to time of new event
	 * Runs the event.
	 */
	public void run() {
		Event currentEvent;// = new Event();
		while (Continue()){
			currentEvent = queue.poll();
			if(currentEvent != null) {
				simTime = currentEvent.getDate();
				currentEvent.Happen(this);
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

	public Date getRealTime() {
		return RealTime;
	}

	public void setRealTime() {
		RealTime = new Date();
	}

	public int GetSpeed() {
		return speed;
	}
}