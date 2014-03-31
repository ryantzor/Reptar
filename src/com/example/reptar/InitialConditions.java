package com.example.reptar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class InitialConditions extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_initial_conditions);
		
		
		
		Spinner spinnerGrowthLevels = (Spinner) findViewById(R.id.spinnerGrowthLevels);
		Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
		final EditText etHuman = (EditText) findViewById(R.id.etHumanCount);
		final EditText etStegosaurus = (EditText) findViewById(R.id.etStegosaurusCount);
		final EditText etPterodactyl = (EditText) findViewById(R.id.etPterodactylCount);
		final EditText etTrex = (EditText) findViewById(R.id.etTrexCount);
		
		btnSubmit.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				//Bundle will hold all values of animals to be sent to the next intent
				Bundle B = new Bundle();
				B.putInt("human", Integer.parseInt(etHuman.getText().toString()));
				B.putInt("pterodactyl",Integer.parseInt(etPterodactyl.getText().toString()));
				B.putInt("stegosaurus", Integer.parseInt(etStegosaurus.getText().toString()));
				B.putInt("trex", Integer.parseInt(etTrex.getText().toString()));
				//B.putString("gamespeed", spinnerGrowthLevels;
				
				//TODO ADD IF NULL
				Intent intent = new Intent(InitialConditions.this, Simulation.class);
				intent.putExtras(B);
				startActivity(intent);
				
					
				
			}
		});
		
		
		
		/* this code is for later implementations when we have multiple environments 
		//populate the spinner with array from r.string for environment types
		Spinner spinnerEnvironments = (Spinner) findViewById(R.id.spinnerEvironmentTypes);
		ArrayAdapter<CharSequence> adapterEnvironments = ArrayAdapter.createFromResource(this, R.array.environments_array, android.R.layout.simple_spinner_dropdown_item);
		adapterEnvironments.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerEnvironments.setAdapter(adapterEnvironments);
		*/
		
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
