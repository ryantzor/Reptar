package com.example.reptar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/*	Menu Form Class
 * 
 */
public class MainActivity extends Activity {
	
	Boolean dataReady = false;
	String filename = "dinoData";
	String data = "";
	
	int[] dataArray = new int[6];
	/*
	int humansCount =0;
	int trexCount = 0;
	int pterodactylCount = 0;
	int stegosaurusCount = 0;
	int gameSpeed = 1;
	int weeks = 0;
	*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        
		Button loadPreviousButton = (Button) findViewById(R.id.btnLoadPreviousSim);
		Button btnNewSimulation = (Button) findViewById(R.id.btnNewSimulation);
		 btnNewSimulation.setOnClickListener(new OnClickListener(){
				public void onClick(View v){

					Intent intent = new Intent(MainActivity.this, InitialConditions.class);
					MainActivity.this.startActivity(intent);
					MainActivity.this.finish();
				}
			});
		 
		 Button btnHighScore = (Button) findViewById(R.id.btnHighScore);
	        btnHighScore.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					
					Intent intent = new Intent(MainActivity.this, HighScore.class);
					intent.putExtra("NEW_SCORE", -1); // -1 Here indicates that there is no new score. So it will just display the saved scores.
					MainActivity.this.startActivity(intent);
					MainActivity.this.finish();
				}
			});
		
		loadPreviousButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
		       
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setMessage(R.string.are_you_sure);
				builder.setTitle(R.string.warning);
				builder.setPositiveButton(R.string.yes,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) 
							{
								// start the simulation activity
								Intent intent = new Intent(MainActivity.this,
										Simulation.class);
								startActivity(intent);
							}
						});

				builder.setNegativeButton(R.string.cancel,
				new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// do nothing
							}
						});

				AlertDialog dialog = builder.create();
				dialog.show();
			}
		});
	}
	
	//runs getDate
    public void init()
    {
    	getData();
    	
    }
    /*Parse the Strings from the saved data
     *return 1 if successful,
     *returns -1 if not
     *
     * Saved Data Format
     * 0/1   <--indicate data saved
     * n <--Human Count
     * n <--T-Rex Count
     * n <-- Pterodactyl Count
     * n <-- Stegosaurus Count
     * 1/2/3 Game Speed
     * n <-- n weeks
     */
    
    public int parse()
    {
    	String[] lines = data.split(System.getProperty("line.separator"));
    	
    	//A '1' at the first line on the first char indicate that it has a saved data
    	if(lines[0].charAt(0) == 0|| data == null || data.equals(""))
		{
    		return -1; // should return an error or -1 to indicate so
		}
        
    	for(int i = 0;i<lines.length;i++)
    	{
    		dataArray[i] =	Integer.parseInt(lines[i]);
    	}
    
    	return 1;    	
    	
    }
	/*
	 * This method will first try to open the file highscore If this fails, it
	 * will create a new file name highscore If this doesn't fail, it will find
	 * all the user and score of each players and add it to an array list.
	 */

	// *Read the saved Data into a string

	public void getData() {
		Context context = getApplicationContext();
		int ch;
		StringBuffer fileContent = new StringBuffer("");
		FileInputStream fis;
		try {
			fis = context.openFileInput(filename);
			try {
				while ((ch = fis.read()) != -1)

					fileContent.append((char) ch);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		data = new String(fileContent);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}    			    
    			  	
}
    

    
    




