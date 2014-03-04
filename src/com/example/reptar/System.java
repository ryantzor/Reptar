package com.example.reptar;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Date;

public class System {
	Date Time;
	Comparator<Event> comparator; //= new somthing;
	PriorityQueue<Event> queue; //= new PriorityQueue<Event>(10, comparator);
	
	/* System Constructor
	 * Initiate Event Queue
	 * Initiate Environment
	 * Initiate Organism list
	 */
	System(Initial x) {
		
	}
	
	private boolean ExitCondistions(){
		
		return true;
	}
}
