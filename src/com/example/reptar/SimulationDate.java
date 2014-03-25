package com.example.reptar;

public class SimulationDate {
	private int time;
	
	SimulationDate() {
		time = 0;
	}
	
	SimulationDate(int EnterTime) {
		time = EnterTime;
	}
	
	int getTime() {
		return time;
	}
	
	void setTime(int newTime) {
		time = newTime;
	}
}
