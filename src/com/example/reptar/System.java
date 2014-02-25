package com.example.reptar;

import java.util.Comparator;
import java.util.PriorityQueue;

public class System {
	Comparator<Event> comparator;
	PriorityQueue<Event> queue;
	
	void start() {
		//comparator = new StringLengthComparator();
		queue = new PriorityQueue<Event>(10, comparator);
		
	}
}
