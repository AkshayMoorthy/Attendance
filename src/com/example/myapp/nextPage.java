package com.example.myapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class nextPage extends ActionBarActivity {
	int b=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page2);
		StrictMode.enableDefaults();

		final TextView tabsent= (TextView)findViewById(R.id.absentees);
		final TextView rabsent= (TextView)findViewById(R.id.textView1);
		
		
	
		String result = "";
	InputStream isr = null;
    	try{
    	       String pattern = "ddMMyyyy";
    	       SimpleDateFormat format = new SimpleDateFormat(pattern);
    	       // formatting
    	       String c=format.format(new Date());
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://attendence.ddns.net/access5.php"); //YOUR PHP SCRIPT ADDRESS
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
            tabsent.setText("Couldnt connect to database");
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

				  json.getInt("count")+"\n";
	   }

	   tabsent.setText(s);
	
   } catch (Exception e) {
	// TODO: handle exception
	   Log.e("log_tag", "Error Parsing Data "+e.toString());
   }
 

}
}