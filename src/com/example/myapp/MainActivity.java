package com.example.myapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	int b=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		StrictMode.enableDefaults();
		final TextView textView1= (TextView)findViewById(R.id.textView1);
		Button pres= (Button)findViewById(R.id.pres);
		Button abs=(Button)findViewById(R.id.abs);

		pres.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String result = "";
				String c=String.valueOf(b);
			InputStream isr = null;
		    	try{
		            HttpClient httpclient = new DefaultHttpClient();
		            HttpPost httppost = new HttpPost("http://attendence.ddns.net/access2.php"); //YOUR PHP SCRIPT ADDRESS
		            HttpClient httpclient2 = new DefaultHttpClient();
		            HttpPost httppost2 = new HttpPost("http://attendence.ddns.net/access.php"); //YOUR PHP SCRIPT ADDRESS
		            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
		            nameValuePair.add(new BasicNameValuePair("c",c));
		            httppost.setEntity(new UrlEncodedFormEntity(nameValuePair));
		            httppost2.setEntity(new UrlEncodedFormEntity(nameValuePair));
		            HttpResponse response = httpclient.execute(httppost);
		            HttpResponse response2 = httpclient2.execute(httppost2);
		            HttpEntity entity = response.getEntity();
		            isr = entity.getContent();
		            
		    }
		    catch(Exception e){
		            Log.e("log_tag", "Error in http connection "+e.toString());
		            textView1.setText("Couldnt connect to database");
		    }
		    //convert response to string
		    try{
		            BufferedReader reader = new BufferedReader(new InputStreamReader(isr,"iso-8859-1"),8);
		            StringBuilder sb = new StringBuilder();
		            String line = null;
		            while ((line = reader.readLine()) != null) {
		                    sb.append(line + "\n");
		            }
		            isr.close();
		     
		            result=sb.toString();
		    }
		    catch(Exception e){
		            Log.e("log_tag", "Error  converting result "+e.toString());
		    }
		     
		    //parse json data
		   try {
			   String s = "";
			   JSONArray jArray = new JSONArray(result);
			   
			   for(int i=0; i<jArray.length();i++){
				   JSONObject json = jArray.getJSONObject(i);
				   s = s + 

						   "Roll No. : "+json.getInt("S_NO")+"\n"+"Name : "+json.getString("NAME")+"\n";
			   }
			   
			   textView1.setText(s);
			
		   } catch (Exception e) {
			// TODO: handle exception
			   Log.e("log_tag", "Error Parsing Data "+e.toString());
		   }
		   if(b<5)
		    b=b+1;
		   else{
			   Intent next=new Intent(MainActivity.this,nextPage.class);	
			startActivity(next);}
			}
			});
		abs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String result = "";
				String c=String.valueOf(b);
			InputStream isr = null;
		    	try{
		            HttpClient httpclient = new DefaultHttpClient();
		            HttpPost httppost = new HttpPost("http://attendence.ddns.net/access3.php"); //YOUR PHP SCRIPT ADDRESS
		            HttpClient httpclient2 = new DefaultHttpClient();
		            HttpPost httppost2 = new HttpPost("http://attendence.ddns.net/access4.php"); //YOUR PHP SCRIPT ADDRESS
		            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
		            nameValuePair.add(new BasicNameValuePair("c",c));
		            httppost.setEntity(new UrlEncodedFormEntity(nameValuePair));
		            httppost2.setEntity(new UrlEncodedFormEntity(nameValuePair));
		            HttpResponse response = httpclient.execute(httppost);
		            HttpResponse response2 = httpclient2.execute(httppost2);
		            HttpEntity entity = response.getEntity();
		            isr = entity.getContent();
		            
		    }
		    catch(Exception e){
		            Log.e("log_tag", "Error in http connection "+e.toString());
		            textView1.setText("Couldnt connect to database");
		    }
		    //convert response to string
		    try{
		            BufferedReader reader = new BufferedReader(new InputStreamReader(isr,"iso-8859-1"),8);
		            StringBuilder sb = new StringBuilder();
		            String line = null;
		            while ((line = reader.readLine()) != null) {
		                    sb.append(line + "\n");
		            }
		            isr.close();
		     
		            result=sb.toString();
		    }
		    catch(Exception e){
		            Log.e("log_tag", "Error  converting result "+e.toString());
		    }
		     
		    //parse json data
		   try {
			   String s = "";
			   JSONArray jArray = new JSONArray(result);
			   
			   for(int i=0; i<jArray.length();i++){
				   JSONObject json = jArray.getJSONObject(i);
				   s = s + 

						   "Roll No. : "+json.getInt("S_NO")+"\n"+"Name : "+json.getString("NAME")+"\n";
			   }
			   
			   textView1.setText(s);
			
		   } catch (Exception e) {
			// TODO: handle exception
			   Log.e("log_tag", "Error Parsing Data "+e.toString());
		   }
		   if(b<5)
		    b=b+1;
		   else{
			   Intent next=new Intent(MainActivity.this,nextPage.class);	
			startActivity(next);}
			}
			
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu Refresh) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, Refresh);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			InputStream isr = null;
			try{
	            HttpClient httpclient = new DefaultHttpClient();
	            HttpPost httppost = new HttpPost("http://attendence.ddns.net/access6.php"); //YOUR PHP SCRIPT ADDRESS
	            HttpResponse response = httpclient.execute(httppost);
	            HttpEntity entity = response.getEntity();
	            isr = entity.getContent();
	            
	    }
	    catch(Exception e){
	            Log.e("log_tag", "Error in http connection "+e.toString());
	            
	    }

		}
		return super.onOptionsItemSelected(item);
	}
}





