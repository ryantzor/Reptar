package com.example.reptar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This is the HighScore activity. Creating and
 * starting an activity of this type will display
 * the highscore screen and allow new scores to be
 * handled.
 * 
 * To add a new highscore do something like this
 * before starting the intent:
 * 		intent.putExtra("NEW_SCORE", 1000);
 * 
 */
public class HighScore extends Activity {

	private String _NewName = ""; // This will be the name that gets recorded for the high score.
	private int _NewScore = 0; // This will be the new highscore that gets added to the list.
	
	/**
	 * onCreate will set up all the buttons and everything.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_score);
		
		// Get any information passed to the intent.
		Intent intent = getIntent();
		_NewScore = intent.getIntExtra("NEW_SCORE", -1);
		
		// Set up the back button to send the user back to the main activity.
		final Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(HighScore.this,
						MainActivity.class);
				startActivity(intent);
				HighScore.this.finish();
			}
		});
        
       // Set up the reset button to reset all of the highscore entries.
       final Button btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				ResetScores();
				DisplayScores();
			}
		});
        
        // Set up the done button. This is the button a user presses when 
        // they are done entering their names if they got a highscore.
        final Button btnDone = (Button) findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				EditText enterName = (EditText) findViewById(R.id.etEnterName);
				_NewName = enterName.getText().toString();
				enterName.setVisibility(View.INVISIBLE);
				btnDone.setVisibility(View.INVISIBLE);
				AddNewScore();
				DisplayScores();
			}
		});
        
        // Display the current highscores to the screen.
        DisplayScores();
        
        // Retrieve the shared preferences file.
        Context context = this;
		SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.high_scores_file), Context.MODE_PRIVATE);
		int defaultValue = 0;
		
		// Get the lowest high score so it can be compared to any new score.
		int score3 = sharedPref.getInt("HighScore3", defaultValue);
		
		// If a new score was passed to the intent display it on screen.
		if(_NewScore >= 0)
		{
			TextView YourScore = (TextView) findViewById(R.id.tvYourScore);
			TextView ScoreOutput = (TextView) findViewById(R.id.tvScoreOutput);
			YourScore.setVisibility(View.VISIBLE);
			ScoreOutput.setVisibility(View.VISIBLE);
			ScoreOutput.setText(Integer.toString(_NewScore));
		}
		
		// If the new score is higher than any old score, replace the old
		// score with it.
        if(score3 < _NewScore) 
		{
			EditText enterName = (EditText) findViewById(R.id.etEnterName);
			enterName.setVisibility(View.VISIBLE);
			btnDone.setVisibility(View.VISIBLE);
		}
	}
    
	/**
	 * DisplayScores will display the 3 highscores to the screen.
	 */
	private void DisplayScores()
	{
		// Get the textview's where the scores will be outputed.
		TextView HighScore1 = (TextView) findViewById(R.id.tvHighScore1);
		TextView HighScore2 = (TextView) findViewById(R.id.tvHighScore2);
		TextView HighScore3 = (TextView) findViewById(R.id.tvHighScore3);
		
		// Retrieve the shared preferences file.
		Context context = this;
		SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.high_scores_file), Context.MODE_PRIVATE);
		int defaultValue = 0;
		
		// Get and store all the score values.
		int score1 = sharedPref.getInt("HighScore1", defaultValue);
		int score2 = sharedPref.getInt("HighScore2", defaultValue);
		int score3 = sharedPref.getInt("HighScore3", defaultValue);
		
		// Get and store all the name values.
		String name1 = sharedPref.getString("HighScore1Name", "No One");
		String name2 = sharedPref.getString("HighScore2Name", "No One");
		String name3 = sharedPref.getString("HighScore3Name", "No One");
		
		// Print the names plus highscores to the appropriate place.
		HighScore1.setText(name1 + " " + score1);
		HighScore2.setText(name2 + " " + score2);
		HighScore3.setText(name3 + " " + score3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.high_score, menu);
		return true;
	}
	
	/**
	 * AddNewScore will take the users name they entered and the score
	 * sent to the intent and add them to the shared preferences file in the
	 * appropriate slot.
	 */
	private void AddNewScore()
	{	
		Context context = this;
		SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.high_scores_file), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		
		int defaultValue = 0;
		for(int i=3; i>0; i--)
		{
			int highScore = sharedPref.getInt("HighScore" + i, defaultValue);
			String highScoreName = sharedPref.getString("HighScore" + i + "Name", "No One");
			// If new high score is greater than old high score handle it.
			if(_NewScore > highScore)
			{
				// For highscore's 1 and 2 move the scores in these slots down a slot.
				if(i<3)
				{
					editor.putInt("HighScore" + (i+1), highScore);
					editor.commit();
					editor.putString("HighScore" + (i+1) + "Name", highScoreName);
				}
				
				// Put new score in current slot.
				editor.putInt("HighScore" + i, _NewScore);
				editor.commit();
				editor.putString("HighScore" + i + "Name", _NewName);
				editor.commit();
			}
		}
	}
	
	/**
	 * ResetScores will reset all stored scores to 0 and all names to Empty.
	 */
	private void ResetScores()
	{
		Context context = this;
		SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.high_scores_file), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		
		for(int i=3; i>0; i--)
		{
			editor.putInt("HighScore" + i, 0);
			editor.commit();
			editor.putString("HighScore" + i + "Name", "Empty");
			editor.commit();
		}
	}
}
