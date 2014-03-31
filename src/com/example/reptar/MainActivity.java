package com.example.reptar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        Button btnNewSimulation = (Button) findViewById(R.id.btnNewSim);
        btnNewSimulation.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				
				Intent intent = new Intent(MainActivity.this, InitialConditions.class);
				MainActivity.this.startActivity(intent);
				MainActivity.this.finish();
			}
		});

        Button loadPreviousButton = (Button) findViewById(R.id.btnLoadPreviousSim);
    	//this on click listener prompts the user to delete or load a previous
        loadPreviousButton.setOnClickListener(new View.OnClickListener() {	
    		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			    builder.setMessage(R.string.are_you_sure);
			    builder.setTitle(R.string.warning);
			    
			    builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// start the simulation activity
						Intent intent = new Intent(MainActivity.this, Simulation.class);
						startActivity(intent);
					}
				});
			    
			    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// do nothing 
					}
				});
			    
			    AlertDialog dialog = builder.create();
			    dialog.show();
			}
		});
   
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    

    
    			    
    			  	
}
    

    
    




