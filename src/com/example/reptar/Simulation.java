package com.example.reptar;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Timer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/*	Simulator Form Class
 *
 */
public class Simulation extends Activity {
	boolean isRunning = true;
	Activity mActivity;
	
	//Button replayButton = (Button)findViewById(R.id.replayButton);
	 int plantsMax = 5000;
     volatile  int gameSpeed=1;
	 volatile  Humans humans = new Humans();
	 volatile TRex trex = new TRex();
	 volatile Pterodactyl pterodactyl = new Pterodactyl();
	 volatile Stegosaurus  stegosaurus = new Stegosaurus();
	 volatile Plants plants = new Plants();
	 volatile int timeInWeeks = 0;
	 volatile float runningScore = 0;
 
	TextView hCount, hPC, pCount, pPC, sCount, sPC, tCount, tPC,plantCount, plantPC, weeksT, scoreValue;
	
	private void StartHighScoreIntent()
	{
		Intent intent = new Intent(Simulation.this, HighScore.class);
		try
		{
		intent.putExtra("NEW_SCORE", (int)runningScore);
		
		}catch(Exception e)
		{
			System.out.println(e);
			intent.putExtra("NEW_SCORE", -1);
		}
		
		startActivity(intent);
	}
    
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simulation);
		Bundle bundles = new Bundle();
	    bundles = getIntent().getExtras();
	
	    humans.count = bundles.getInt("human");
		trex.count = bundles.getInt("trex");
		pterodactyl.count = bundles.getInt("pterodactyl");
		stegosaurus.count =  bundles.getInt("stegosaurus");
		 
		 
	     //gameSpeed =  bundle.getInt("gamespeed");
		mActivity = this;
		
		weeksT = (TextView)findViewById(R.id.weeksT);
		hCount = (TextView)findViewById(R.id.hCount);
		hPC = (TextView)findViewById(R.id.hPC);
		pCount =(TextView)findViewById(R.id.pCount);
		pPC = (TextView)findViewById(R.id.pPC);
		sCount = (TextView)findViewById(R.id.sCount);
		sPC = (TextView)findViewById(R.id.sPC);
		tCount =(TextView)findViewById(R.id.tCount);
		tPC = (TextView)findViewById(R.id.tPC);
		plantCount =(TextView)findViewById(R.id.plantCount);
		plantPC =(TextView)findViewById(R.id.plantPC);
		scoreValue = (TextView)findViewById(R.id.scoreValue);
		final Simulator simulatorInstance = new Simulator();
		//simulatorInstance.instantiateSimulator();
		
		plantsMax = 5 * (humans.count + pterodactyl.count + stegosaurus.count + trex.count);
    	plants.count = plantsMax;
    	plants.growthRate = 1.4;
    	
    
    	
    	hPC.setText(String.format("%.2f",humans.growthRate) + "");
		hCount.setText(humans.count + "");
		pCount.setText(pterodactyl.count + "");
		pPC.setText(String.format("%.2f",pterodactyl.growthRate) + ""); 
		sCount.setText(stegosaurus.count + ""); 
		sPC.setText(String.format("%.2f",stegosaurus.growthRate) + "");  
		tCount.setText(trex.count + "");
		tPC.setText(String.format("%.2f",trex.growthRate) + ""); 
		plantCount.setText(plants.count + ""); 
		plantPC.setText(String.format("%.2f",plants.growthRate) + "");
		
		//sets up meteor button
		Button meteorB = (Button) findViewById(R.id.Meteor);
        meteorB.setOnClickListener(new OnClickListener(){
			public void onClick(View v)
			{
				Event meteor = new Event(1, "Meteor", 1);
				simulatorInstance.addEvent(meteor);
				//StartHighScoreIntent(timeInWeeks);
			}
		});
        
        //sets up Flood button
        Button floodB = (Button) findViewById(R.id.Flood);
        floodB.setOnClickListener(new OnClickListener(){
			public void onClick(View v)
			{
				Event flood = new Event(1, "Flood", 1);
				simulatorInstance.addEvent(flood);
				//StartHighScoreIntent(humans.count+pterodactyl.count+stegosaurus.count+trex.count+timeInWeeks);
			}
		});
        
        //sets up end button
        Button endB = (Button) findViewById(R.id.Ending);
        endB.setOnClickListener(new OnClickListener(){
			public void onClick(View v)
			{
				StartHighScoreIntent();
			}
		});
        
		simulatorInstance.start();
		
	}
	
	/*	Simulator Logic Class
	 * 	Controls the logic of the simulation as it runs.
	 */
	  public class Simulator extends Thread 
	{
		  Comparator<Event> comparator; //= new something;
		  PriorityQueue<Event> queue; //= new PriorityQueue<Event>(10, comparator);
		  double TimeSec = System.currentTimeMillis();
		  double TimeSecPlus = TimeSec+5000;
		  public Simulator()
		  {
			
			/*	Logic of the comparator for the Priority Queue.
			 * 
			 */
			comparator = new Comparator<Event>() {
				public int compare(Event a, Event b) {
					if (a.priority > b.priority)
						return 1;
					if (a.priority < a.priority)
						return 0;
					return -1;
				}
			};
			queue = new PriorityQueue<Event>(10, comparator);
		}
		  
		  /*  Ends the Simulation
		   * 
		   */
		void endSimulation()
		{
			StartHighScoreIntent();
		}
		
		// add a new event to the queue
		void addEvent(Event theEvent) {
			queue.add(theEvent);
		}
		
		public void FeedHumans()
		{
			if(humans.count <= 1)
			{
				humans.growthRate = 0;
				humans.count = 0;
				return;
			}
			
			float numOfOrganisms = (humans.count + trex.count + pterodactyl.count + stegosaurus.count + (plants.count / 2));
			float percentCanabalistic = (float).05;
			if(trex.count <= 0 && pterodactyl.count <=0 && stegosaurus.count <= 0) percentCanabalistic = (float)1.0;
			
			humans.count -= (int)(humans.count * ((((float)humans.count) * percentCanabalistic) / numOfOrganisms));
			trex.count -= (int)(humans.count * (((float)trex.count) / numOfOrganisms));
			pterodactyl.count -= (int)(humans.count * (((float)pterodactyl.count) / numOfOrganisms));
			stegosaurus.count -= (int)(humans.count * (((float)stegosaurus.count) / numOfOrganisms));
			plants.count -= (int)(humans.count * (((float)plants.count) / numOfOrganisms));
			
			if(trex.count < 0) 
			{
				humans.count += trex.count;
				trex.count = 0;
			}
			if(pterodactyl.count < 0)
			{
				humans.count += pterodactyl.count;
				pterodactyl.count = 0;
			}
			if(stegosaurus.count < 0)
			{
				humans.count += stegosaurus.count;
				stegosaurus.count = 0;
			}
			
			if(humans.growthRate < 2) humans.growthRate += .01;
		}
		
		private void FeedTRex()
		{
			if(trex.count <= 1)
			{
				trex.growthRate = 0;
				trex.count = 0;
				return;
			}
			
			float numOfOrganisms = (humans.count + trex.count + pterodactyl.count + stegosaurus.count);
			float percentCanabalistic = (float).2;
			if(humans.count <= 0 && pterodactyl.count <=0 && stegosaurus.count <= 0) percentCanabalistic = (float)1.0;
			
			trex.count -= (int)(trex.count * ((((float)trex.count) * percentCanabalistic) / numOfOrganisms));
			humans.count -= (int)(trex.count * (((float)humans.count) / numOfOrganisms));
			pterodactyl.count -= (int)(trex.count * (((float)pterodactyl.count) / numOfOrganisms));
			stegosaurus.count -= (int)(trex.count * (((float)stegosaurus.count) / numOfOrganisms));
			
			if(humans.count < 0) 
			{
				trex.count += humans.count;
				humans.count = 0;
			}
			if(pterodactyl.count < 0)
			{
				trex.count += pterodactyl.count;
				pterodactyl.count = 0;
			}
			if(stegosaurus.count < 0)
			{
				trex.count += stegosaurus.count;
				stegosaurus.count = 0;
			}
			
			if(trex.growthRate < 1.3) trex.growthRate += .01;
		}
		
		private void FeedPterodactyl()
		{
			if (pterodactyl.count > 1) {
				plants.count = plants.count - pterodactyl.count;
				if (plants.count < pterodactyl.count * 4) {
					pterodactyl.count = pterodactyl.count - (pterodactyl.count - (plants.count/4));
					if(pterodactyl.growthRate > .85) pterodactyl.growthRate -= .05;
				} 
				else if(pterodactyl.growthRate < 1.3) 
				{
					pterodactyl.growthRate += .01;
				}
			} else {
				pterodactyl.growthRate = 0;
				pterodactyl.count = 0;
			}
		}
		
		private void FeedStegosaurus()
		{
			if (stegosaurus.count > 1) {
				plants.count = plants.count - stegosaurus.count;
				if (plants.count < pterodactyl.count * 4) {
					stegosaurus.count = stegosaurus.count - (stegosaurus.count - (plants.count/4));
					if(stegosaurus.growthRate > .85) stegosaurus.growthRate -= .05;
				} 
				else if (stegosaurus.growthRate < 1.3)
				{
					stegosaurus.growthRate += .01;
				}
			} else {
				stegosaurus.growthRate = 0;
				stegosaurus.count = 0;
			}
		}
		
		private void AddToHighScore()
		{
			float temp = 0;
			temp += stegosaurus.count/2;
			temp += pterodactyl.count/2;
			temp += trex.count/10;
			temp += humans.count/10;
			temp += plants.count/100000;
			
			runningScore += ((temp * timeInWeeks)/10);
		}
		
		//starts the simulation
		public void run() {
			// t.schedule(new addQueue(), 0, 1000*gameSpeed); //5sec
			while (isRunning) {
				try {

					// Set Initial Display
					
					
					TimeSec = System.currentTimeMillis();
					if (TimeSec >= TimeSecPlus) {
						Event feed = new Event(1, "Feed", 2);
						this.addEvent(feed);

						Event react = new Event(1, "Reproduce/Death", 3);
						this.addEvent(react);

						Event humanInduce = new Event(1, "HumanInduced", 1);
						this.addEvent(humanInduce);

						// Ordered by "Adaptability"
						while (queue.size() > 0) {
							Event currentEvent = this.queue.poll();
							if (currentEvent.event.equals("Feed")) {
								FeedHumans();

								FeedTRex();

								FeedPterodactyl();
								
								FeedStegosaurus();

							}
							// Ordered by "Adaptability"
							else if (currentEvent.event.equals("Reproduce/Death")) {
								humans.count = (int) (humans.count * humans.growthRate);

								trex.count = (int) (trex.count * trex.growthRate);
								stegosaurus.count = (int) (stegosaurus.count * stegosaurus.growthRate);
								pterodactyl.count = (int) (pterodactyl.count * pterodactyl.growthRate);
								plants.count = (int) (plants.count * plants.growthRate);
								
								if(plants.count > plantsMax && plants.growthRate > .98) plants.growthRate -= .1;
								else if(plants.count < plantsMax && plants.growthRate < 1.4) plants.growthRate += .1;
							}
							else if(currentEvent.event.equals("Flood"))
							{
								trex.count = (int) (trex.count * .5);
								trex.growthRate -= 0.3;
								stegosaurus.count = (int) (stegosaurus.count * .4);
								stegosaurus.growthRate -= 0.15;
								pterodactyl.count = (int) (pterodactyl.count * .8);
								pterodactyl.growthRate -= 0.1;
								plants.count = (int) (plants.count * .3);
								plants.growthRate -= 0.15;
								humans.count = (int) (humans.count * .4);
								humans.growthRate -= 0.25;
							}
							else if(currentEvent.event.equals("Meteor"))
							{
								trex.count = (int) (trex.count * .3);
								trex.growthRate -= 0.15;
								stegosaurus.count = (int) (stegosaurus.count * .6);
								stegosaurus.growthRate -= 0.15;
								pterodactyl.count = (int) (pterodactyl.count * .4);
								pterodactyl.growthRate -= 0.2;
								plants.count = (int) (plants.count * .4);
								plants.growthRate -= 0.2;
								humans.count = (int) (humans.count * .5);
								humans.growthRate -= 0.1;
							}
							
							if(plants.count < 0) plants.count = 0;
						}
						mActivity.runOnUiThread(new Runnable() {
							public void run() {
								
								//updates text boxes
								weeksT.setText(timeInWeeks+"");
								hPC.setText(String.format("%.2f",humans.growthRate) + "");
								hCount.setText(humans.count + "");
								pCount.setText(pterodactyl.count + "");
								pPC.setText(String.format("%.2f",pterodactyl.growthRate) + ""); 
								sCount.setText(stegosaurus.count + ""); 
								sPC.setText(String.format("%.2f",stegosaurus.growthRate) + "");  
								tCount.setText(trex.count + "");
								tPC.setText(String.format("%.2f",trex.growthRate) + ""); 
								plantCount.setText(plants.count + ""); 
								plantPC.setText(String.format("%.2f",plants.growthRate) + "");  
								scoreValue.setText(((int)runningScore) + "");
							}
						});
						AddToHighScore();
						timeInWeeks += 1;
						TimeSecPlus = System.currentTimeMillis() + 5000;
						if(humans.count <= 0 && pterodactyl.count <=0 && stegosaurus.count <= 0 && trex.count <= 0)
						{
							StartHighScoreIntent();
							break;
						}
					}
				} catch (Exception e) {
					System.out.println(e);

				}

			}
			Simulation.this.finish();

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.simulation, menu);
		return true;
	}

}
