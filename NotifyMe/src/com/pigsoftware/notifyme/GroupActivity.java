package com.pigsoftware.notifyme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

public class GroupActivity extends SherlockListActivity implements Callback {
	public static int THEME = R.style.Theme_Sherlock;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		String path = intent.getStringExtra("com.example.android.apis.Path");

		if (path == null) {
			path = "";
		}
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("method", "getGroups"));
		
		Server server = new Server(this,nameValuePairs);
		server.execute(new String[]{});
		

	}

	public void fillAdapter(String result) {
		JSONArray jArray;
		try {
			jArray = new JSONArray(result);
			if (jArray.length() > 0) {				
				String[] values = new String[jArray.length()];
			
				for (int i = 0; i < jArray.length(); i++) {
					JSONObject json_data = jArray.getJSONObject(i);
					values[i]= json_data.getString("GROUP_NAME");
				}		 

			ArrayAdapterNots adapter = new ArrayAdapterNots(this, values);
			setListAdapter(adapter);
			}
		} catch (JSONException e) {

			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		SubMenu sub = menu.addSubMenu("Theme");
		sub.add(0, R.style.Theme_Sherlock, 0, "Default");
		sub.add(0, R.style.Theme_Sherlock_Light, 0, "Light");
		sub.add(0, R.style.Theme_Sherlock_Light_DarkActionBar, 0,
				"Light (Dark Action Bar)");
		sub.getItem().setShowAsAction(
				MenuItem.SHOW_AS_ACTION_ALWAYS
						| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home || item.getItemId() == 0) {
			return false;
		}
		THEME = item.getItemId();
		Toast.makeText(this, "Theme changed to \"" + item.getTitle() + "\"",
				Toast.LENGTH_SHORT).show();
		return true;
	}

	protected List<Map<String, Object>> getData(String prefix) {
		List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();

		Map<String, Object> entries = new HashMap<String, Object>();
		entries.put("DIEGO", "DIEGO");

		myData.add(entries);
		myData.add(entries);
		myData.add(entries);
		myData.add(entries);

		return myData;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void onListItemClick(ListView l, View v, int position, long id) {

	}

	@Override
	public void callback(String result) {
		fillAdapter(result);

	}
}
