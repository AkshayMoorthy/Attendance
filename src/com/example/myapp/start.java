package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;


public class start extends ActionBarActivity {
protected boolean _active=true;
protected int _splashtime=3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		StrictMode.enableDefaults();
		Handler handler=new Handler();
		handler.postDelayed(new Runnable(){
			public void run(){
				finish();
				Intent i2=new Intent(start.this,firstPage.class);
				startActivity(i2);
			}
		},_splashtime);
		
}
}