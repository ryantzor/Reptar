package com.example.reptar;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Date;

public class System {
	Date Time;
	Comparator<Event> comparator; //= new something;
	PriorityQueue<Event> queue; //= new PriorityQueue<Event>(10, comparator);
	
	/* System Constructor
	 * Initiate Event Queue
	 * Initiate Environment
	 * Initiate Organism list
	 */
	System(Initial x) {
		
	}
	
	
	/* Check all the exit conditions and returns false 
	 * if simulation should stop.
	 */
	private boolean ExitCondistions(){
		
		return true;
	}
}
