package com.pigsoftware.notifyme;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.graphics.Bitmap;
import android.util.Log;

public class LoadNots implements Callback {

	Notification nots[];
	int counter=0;
	public final static String TAG = "NOTIFYME";
	
	public LoadNots() {

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("method", "getLastNots"));

		Server server = new Server(this, nameValuePairs);
		server.execute(new String[] {});
	}
	
	public void saveNots(){
		
		Utils.NOTS = nots;
		Log.v(TAG,"NOTIFICATIONS");
		for (int i = 0; i < nots.length; i++) {
			Log.v(TAG,"TITLE: "+nots[i].NOTIFICATION_TITLE);
			Log.v(TAG,"MSG: "+nots[i].NOTIFICATION_MESSAGE);
			Log.v(TAG,"-");
		}
	}

	@Override
	public void callback(String result, boolean image,Bitmap bitmap) {
//		Log.v(TAG,result);		
			JSONArray jArray;
			try {
				jArray = new JSONArray(result);
				if (jArray.length() > 0) {
					nots = new Notification[jArray.length()];
					for (int i = 0; i < jArray.length(); i++) {
						JSONObject json_data = jArray.getJSONObject(i);
						Notification not = new Notification();
						not.GROUP_ID = json_data.getString("GROUP_ID");
						not.NOTIFICATION_DATE = json_data.getString("NOTIFICATION_DATE");
						not.NOTIFICATION_ID = json_data.getString("NOTIFICATION_ID");
						not.NOTIFICATION_MESSAGE = json_data.getString("NOTIFICATION_MESSAGE");
						not.NOTIFICATION_TIME = json_data.getString("NOTIFICATION_TIME");
						not.NOTIFICATION_TITLE= json_data.getString("NOTIFICATION_TITLE");
						not.NOTIFICATION_TYPE_ID= json_data.getString("NOTIFICATION_TYPE_ID");						
						nots[i] = not;				
					}
					saveNots();
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		

	}

}
