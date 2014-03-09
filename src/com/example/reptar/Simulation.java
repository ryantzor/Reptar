package com.example.reptar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Simulation extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simulation);
		SimulatorStart simulatorInstance = new SimulatorStart();
		simulatorInstance.run();
	}
	  public class SimulatorStart extends Thread 
	{
		    
		  public void run()
		  {
			  while(true)
			  {
				  
			  }
		   
		  }
		  
		  public void instantiateSimulator()
		  {
			  
		  }
	}
	 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.simulation, menu);
		return true;
	}
	 
	

}
