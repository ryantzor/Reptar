package com.example.reptar;

/**
 * This is the Event class. Every event will be associated
 * with an instance of it.
 */
public class Event {
	//public SimulationDate simDate;
	int duration; // Length of the event.
	String event; // Name of the event.
	int priority; // The events priority.
	
	/*
	 * The constructor for class Event.
	 * @param durations The duration that the event should last.
	 * @param events The name of the event.
	 * @param prioritiys The priority of the event.
	 */
	Event(int durations, String events, int prioritys) {
		duration = durations;
		event = events;
		priority = prioritys;
	}
	

}