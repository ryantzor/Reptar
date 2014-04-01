package com.example.reptar;

import java.util.Date;


public class Event {
	public SimulationDate simDate;

	Event() {
		simDate = new SimulationDate();
	}

	Event(int happenTime) {
		simDate = new SimulationDate(happenTime);
	}

	public void Happen(System x) {

	}

	public SimulationDate getDate() {
		return simDate;
	}

	public void setDate(SimulationDate x) {
		simDate = x;
	}
}

class displayEvent extends Event {



	displayEvent() {
		simDate = new SimulationDate();
	}


	displayEvent(int happenTime) {
		simDate = new SimulationDate(happenTime);
	}


	public void Happen(System x) {
		//wait
		Date Time = x.getRealTime();
		Date now = new Date();
		Time.setTime(Time.getTime() - now.getTime());
		// has the program wait (5 sec) - time 
		if (Time.getTime() < 5000) {
			try {
				Thread.sleep(5000 - Time.getTime());
			} catch (Exception e) {
				//todo message box
			}
		}

		//display function

		//update real time and add new displayEvent
		x.setRealTime();
		int eventTime = simDate.getTime() + x.GetSpeed();//sets eventTime to this events time + simulation speed
		x.addEvent(new displayEvent(eventTime));
	}
}