package com.pigsoftware.notifyme;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class Server extends AsyncTask<String, Void, String> {
	
	ArrayList<NameValuePair> nameValuePairs;
	Callback classToCall;
	
	public Server(Callback classToCall,ArrayList<NameValuePair> nameValuePairs) {
		this.nameValuePairs = nameValuePairs;
		this.classToCall = classToCall;
	}
	
	
	
	@Override
	protected String doInBackground(String... urls) {
		String result = "";	
		// http post
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(Utils.HOST);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
		} catch (Exception e) {
			Log.e("log_tag", "Error converting result " + e.toString());
			e.printStackTrace();
		}
		// parse json data

		return result;
	}

	@Override
	protected void onPostExecute(String result) {
		Log.v("TAG", result);
		classToCall.callback(result);
	}
}