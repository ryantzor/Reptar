package com.example.reptar;

/*	Organism Class
 * 
 */

public class Organism {
	
	Boolean isAlive;
	
	int count;
	double growthRate;
	double terrainAdaptability;
	double threatLevel;
	double foodSource;

	public Organism()
	{
		isAlive = true;
		growthRate = 1.0;
		count = 1;
		
		terrainAdaptability = 100.0;
		threatLevel = 100.0;
		foodSource = 100.0;
	
	}

}
