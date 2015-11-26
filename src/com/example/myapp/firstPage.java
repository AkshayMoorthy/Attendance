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

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class firstPage extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		StrictMode.enableDefaults();
		Button signin=(Button)findViewById(R.id.pres);
		signin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final EditText editText1= (EditText)findViewById(R.id.editText1);
				final EditText editText2= (EditText)findViewById(R.id.editText2);
				String result = "";
				String c=String.valueOf(editText1.getText());
				String d=String.valueOf(editText2.getText());
			InputStream isr = null;
				try{
		            HttpClient httpclient = new DefaultHttpClient();
		            HttpPost httppost = new HttpPost("http://attendence.ddns.net/first.php"); //YOUR PHP SCRIPT ADDRESS 
		            HttpClient httpclient2 = new DefaultHttpClient();
		            HttpPost httppost2 = new HttpPost("http://attendence.ddns.net/date.php"); //YOUR PHP SCRIPT ADDRESS 
		            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
		            nameValuePair.add(new BasicNameValuePair("c",c));
		            nameValuePair.add(new BasicNameValuePair("d",d));
		            httppost.setEntity(new UrlEncodedFormEntity(nameValuePair));
		            
		            HttpResponse response = httpclient.execute(httppost);
		            HttpResponse response2 = httpclient2.execute(httppost2);
		            HttpEntity entity = response.getEntity();
		            isr = entity.getContent();
		          }
		    catch(Exception e){
		            Log.e("log_tag", "Error in http connection "+e.toString());
		           editText1.setText("Couldnt connect to database");
		    }
				try{
			            BufferedReader reader = new BufferedReader(new InputStreamReader(isr));
			            StringBuilder sb = new StringBuilder();
			            String line = null;
			            while ((line = reader.readLine()) != null) {
			                    sb.append(line);
			            }
			            isr.close();
			     
			            result=sb.toString();
			        	if(result.equals("true"))
			        	{
						Intent next=new Intent(firstPage.this,MainActivity.class);	
						startActivity(next);
						}
			        	else 
			        		   editText1.setText("Couldnt connect to database");
				}
				
				catch(Exception e){
		            Log.e("log_tag", "Error in http connection "+e.toString());
		           editText1.setText("Couldnt connect to database");
				}
		
			}
		});
	


}
}