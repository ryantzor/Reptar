package com.example.reptar;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity {
	private static int SPLASH_TIME_OUT = 5000;
	private MediaPlayer zeldaTitleSong;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		//get rid of action bar at top
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		zeldaTitleSong = MediaPlayer.create(SplashScreen.this, R.raw.oot_theme_splash);
		zeldaTitleSong.start();
		
		Thread thread = new Thread(){
			public void run(){
				try{
					sleep(5000);
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
				finally{
					Intent intent = new Intent(SplashScreen.this,MainActivity.class);
					SplashScreen.this.startActivity(intent);
					SplashScreen.this.finish();
				}
			}

		};
		thread.start();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}

}
