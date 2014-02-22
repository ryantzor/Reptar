package com.example.reptar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class InitialConditions extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_initial_conditions);
		
		Spinner spinnerEnvironments = (Spinner) findViewById(R.id.spinnerEvironmentTypes);
		Spinner spinnerGrowthLevels = (Spinner) findViewById(R.id.spinnerGrowthLevels);
		
		
		//populate the spinner with array from r.string for environment types
		ArrayAdapter<CharSequence> adapterEnvironments = ArrayAdapter.createFromResource(this, R.array.environments_array, android.R.layout.simple_spinner_dropdown_item);
		adapterEnvironments.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerEnvironments.setAdapter(adapterEnvironments);
		
		//populate the spinner with array from r.string for growth levels
		ArrayAdapter<CharSequence> adapterGrowthLevels = ArrayAdapter.createFromResource(this, R.array.growth_levels, android.R.layout.simple_spinner_dropdown_item);
		adapterGrowthLevels.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerGrowthLevels.setAdapter(adapterGrowthLevels);
		
		
		
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.initial_conditions, menu);
		return true;
	}

}
