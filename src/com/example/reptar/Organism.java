package com.example.reptar;

public class Organism {
	
	Boolean isAlive;
	
	int count;
	
	double growthRate;
	double terrainAdaptability;
	double threatLevel;
	String typeOfFood;
	
	
	public Organism()
	{
		isAlive = true;
		growthRate = 0.0;
		count = 1;
		
		terrainAdaptability = 100.0;
		threatLevel = 100.0;
		
	
	}

}
