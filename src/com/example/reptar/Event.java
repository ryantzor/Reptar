package com.example.reptar;
	

public class Event {
	public SimulationDate Time;
	
	Event() {
		Time = new SimulationDate();
	}
	
	Event(int happenTime) {
		Time = new SimulationDate(happenTime);
	}
	
	public void Happen() {
		
	}
	
	SimulationDate getTime() {
		return Time;
	}
}
