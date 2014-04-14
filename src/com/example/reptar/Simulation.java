package com.example.reptar;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Timer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/*	Simulator Form Class
 *
 */
public class Simulation extends Activity {
	boolean isRunning = true;
	Activity mActivity;
	
	//Button replayButton = (Button)findViewById(R.id.replayButton);
	  final int plantsMax = 4000;
     volatile  int gameSpeed=1;
	 volatile  Humans humans = new Humans();
	 volatile TRex trex = new TRex();
	 volatile Pterodactyl pterodactyl = new Pterodactyl();
	 volatile Stegosaurus  stegosaurus = new Stegosaurus();
	 volatile Plants plants = new Plants();
	 volatile int timeInWeeks = 0;
 
	TextView hCount, hPC, pCount, pPC, sCount, sPC, tCount, tPC,plantCount, plantPC, weeksT;
    
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
		Simulator simulatorInstance = new Simulator();
		//simulatorInstance.instantiateSimulator();
		
    	plants.count = plantsMax;
    	plants.growthRate = 1.75;
    	
    
    	
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
			public void onClick(View v){
				Intent intent = new Intent(Simulation.this, HighScore.class);
				try
				{
				intent.putExtra("NEW_SCORE", timeInWeeks);
				
				}catch(Exception e)
				{
					System.out.println(e);
					intent.putExtra("NEW_SCORE", -1);
				}
				
				startActivity(intent);
			}
		});
        
        //sets up Flood button
        Button floodB = (Button) findViewById(R.id.Flood);
        floodB.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(Simulation.this, HighScore.class);
				try
				{
				intent.putExtra("NEW_SCORE", humans.count+pterodactyl.count+stegosaurus.count+trex.count+timeInWeeks);
				
				}catch(Exception e)
				{
					System.out.println(e);
					intent.putExtra("NEW_SCORE", -1);
				}
				
				startActivity(intent);
			}
		});
        
        //sets up end button
        Button endB = (Button) findViewById(R.id.Ending);
        endB.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(Simulation.this, HighScore.class);
				try
				{
				intent.putExtra("NEW_SCORE", humans.count+pterodactyl.count+stegosaurus.count+trex.count+timeInWeeks);
				
				}catch(Exception e)
				{
					System.out.println(e);
					intent.putExtra("NEW_SCORE", -1);
				}
				
				startActivity(intent);
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
			Intent intent = new Intent(Simulation.this, HighScore.class);
			try
			{
			intent.putExtra("NEW_SCORE", humans.count+pterodactyl.count+stegosaurus.count+trex.count+timeInWeeks);
			
			}catch(Exception e)
			{
				System.out.println(e);
				intent.putExtra("NEW_SCORE", -1);
			}
			
			startActivity(intent);
		}
		
		// add a new event to the queue
		void addEvent(Event theEvent) {
			queue.add(theEvent);
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
								if (humans.count > 0) {
									plants.count = plants.count - humans.count;
									if (plants.count < 0) {
										humans.count = (humans.count+ plants.count);
										humans.growthRate -= .01;
										plants.count = 0;

									} else {
										humans.growthRate += .01;
									}
								} else {
									humans.growthRate = 0;
								}

								if (trex.count > 0) {
									plants.count = plants.count - trex.count;
									if (plants.count < 0) {
										trex.count = trex.count + plants.count;
										trex.growthRate -= .01;
										plants.count = 0;
									} else {
										trex.growthRate += .01;
									}
								} else {
									trex.growthRate = 0;
								}

								if (pterodactyl.count > 0) {
									plants.count = plants.count
											- pterodactyl.count;
									if (plants.count < 0) {
										pterodactyl.count = pterodactyl.count
												+ plants.count;
										pterodactyl.growthRate -= .01;
										plants.count = 0;
									} else {
										pterodactyl.growthRate += .01;
									}
								} else {
									pterodactyl.growthRate = 0;
								}

								if (stegosaurus.count > 0) {
									plants.count = plants.count
											- stegosaurus.count;
									if (plants.count < 0) {
										stegosaurus.count = stegosaurus.count
												+ plants.count;
										stegosaurus.growthRate -= .01;
										plants.count = 0;
									} else {
										stegosaurus.growthRate += .01;
									}
								} else {
									stegosaurus.growthRate = 0;
								}

							}
							// Ordered by "Adaptability"
							if (currentEvent.event.equals("Reproduce/Death")) {
								humans.count = (int) (humans.count * humans.growthRate);

								trex.count = (int) (trex.count * trex.growthRate);
								stegosaurus.count = (int) (stegosaurus.count * stegosaurus.growthRate);
								pterodactyl.count = (int) (pterodactyl.count * pterodactyl.growthRate);
								plants.count = (int) (plants.count * plants.growthRate);

								plants.count = (int)(plants.growthRate * plants.count);
								if(plants.count > plantsMax)
								{
									plants.count = plantsMax;
								}
							}
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
						    	
							}
						});
						timeInWeeks += 1;
						TimeSecPlus = System.currentTimeMillis() + 5000;
					}
				} catch (Exception e) {
					System.out.println(e);

				}

			}

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.simulation, menu);
		return true;
	}

}
