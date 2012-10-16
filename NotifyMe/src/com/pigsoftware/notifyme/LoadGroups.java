package com.pigsoftware.notifyme;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.graphics.Bitmap;
import android.util.Log;

public class LoadGroups implements Callback {

	Group groups[];
	int counter=0;
	public final static String TAG = "NOTIFYME";
	
	public LoadGroups() {

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("method", "getGroupsUser"));

		Server server = new Server(this, nameValuePairs);
		server.execute(new String[] {});
	}
	
	public void saveGroups(){
		
		Utils.GROUPS = groups;
		
		for (int i = 0; i < groups.length; i++) {
			Log.v(TAG,"NAME: "+groups[i].GROUP_NAME);
			Log.v(TAG,"BITMAP: "+groups[i].GROUP_IMAGE.toString());
			Log.v(TAG,"-");
		}
	}

	@Override
	public void callback(String result, boolean image,Bitmap bitmap) {
//		Log.v(TAG,result);
		if (!image) {
			JSONArray jArray;
			try {
				jArray = new JSONArray(result);
				if (jArray.length() > 0) {
					groups = new Group[jArray.length()];
					for (int i = 0; i < jArray.length(); i++) {
						JSONObject json_data = jArray.getJSONObject(i);
						Group group = new Group();
						group.GROUP_ID = json_data.getString("GROUP_ID");
						group.GROUP_NAME = json_data.getString("GROUP_NAME");
						group.GROUP_DESCRIPTION = json_data.getString("GROUP_DESCRIPTION");
						group.USER_ID = json_data.getString("USER_ID");
						groups[i] = group;
						ImageServer ss = new ImageServer(this,Utils.HOST_ROOT+"group"+group.GROUP_ID+".jpg");
						ss.execute(new String[] {});
					}
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Group group = groups[counter];
			group.GROUP_IMAGE = bitmap;
			groups[counter] = group;
			counter++;
			if(counter==groups.length){
				saveGroups();
			}
			
		}

	}

}
