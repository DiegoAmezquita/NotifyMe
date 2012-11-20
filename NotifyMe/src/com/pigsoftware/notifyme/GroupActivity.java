package com.pigsoftware.notifyme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

public class GroupActivity extends SherlockListActivity implements Callback {
	public static int THEME = R.style.Theme_Sherlock;

	String GROUP_ID;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent myIntent = getIntent();
		GROUP_ID = myIntent.getStringExtra("GROUP_ID");
		String GROUP_NAME = myIntent.getStringExtra("GROUP_NAME");

		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(GROUP_NAME);

		Notification groupsNots[] = Utils.getNotsFromGroup(GROUP_ID);

		ArrayAdapterNots adapter = new ArrayAdapterNots(this, groupsNots);
		setListAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		/*
		 * SubMenu sub = menu.addSubMenu("Theme"); sub.add(0,
		 * R.style.Theme_Sherlock, 0, "Default"); sub.add(0,
		 * R.style.Theme_Sherlock_Light, 0, "Light"); sub.add(0,
		 * R.style.Theme_Sherlock_Light_DarkActionBar, 0,
		 * "Light (Dark Action Bar)"); sub.getItem().setShowAsAction(
		 * MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		 */

		MenuItem populateItem = menu.add(Menu.NONE, 0, 0, "New");
		populateItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == 0) {
			Intent intent = new Intent(this, NewNotFragment.class);
			intent.putExtra("GROUP_ID", GROUP_ID);
			startActivity(intent);
			finish();
			Toast.makeText(this, "NUEVO", Toast.LENGTH_SHORT).show();
		}

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
	public void callback(String result, boolean image, Bitmap bitmap) {

	}
}
