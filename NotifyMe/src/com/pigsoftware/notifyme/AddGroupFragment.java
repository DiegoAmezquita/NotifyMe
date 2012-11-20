package com.pigsoftware.notifyme;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class AddGroupFragment extends SherlockFragmentActivity {
	// Menu identifiers
	static final int POPULATE_ID = Menu.FIRST;
	static final int CLEAR_ID = Menu.FIRST + 1;

	

	EditText inputSearch;
	ArrayAdapter<String> adapter;
	ArrayList<String> lst;
	
	ArrayList<String> tempo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addgrouplayout);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.addGroup);

		inputSearch = (EditText)findViewById(R.id.inputSearch);
		
		ImageView buttonSearch = (ImageView)findViewById(R.id.searchButton);
		buttonSearch.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				search();
			}
		});
		
		ImageView buttonNewGroup = (ImageView)findViewById(R.id.newGroupButton);
		buttonNewGroup.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				newGroup();
			}
		});
		
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		String[] values = new String[Utils.GROUPS.length];
		
		for (int i = 0; i < Utils.GROUPS.length; i++) {
			values[i] = Utils.GROUPS[i].GROUP_NAME;
		}
		
		lst = new ArrayList<String>();
		lst.addAll(Arrays.asList(values));
		
		tempo = new ArrayList<String>();
		tempo.addAll(Arrays.asList(values));

		// First paramenter - Context
		// Second parameter - Layout for the row
		// Third parameter - ID of the TextView to which the data is written
		// Forth - the Array of data
		adapter = new ArrayAdapter<String>(this,
		  android.R.layout.simple_list_item_1, android.R.id.text1, lst);

		// Assign adapter to ListView
		listView.setAdapter(adapter);
		
		
		

	}
	
	public void search(){
		adapter.clear();
		String searchText = inputSearch.getEditableText().toString();
		
		for (int i = 0; i < tempo.size(); i++) {
			Log.v("TAG",tempo.get(i)+" BUSCANDO "+tempo.get(i).contains(searchText)+"");
			if(tempo.get(i).contains(searchText)){				
				adapter.add(tempo.get(i));
			}
		}		
		
		adapter.notifyDataSetChanged();
		Toast.makeText(this, inputSearch.getEditableText().toString(), Toast.LENGTH_SHORT).show();
	}
	
	public void newGroup(){
		Intent intent = new Intent(this, NewGroupFragment.class);
		startActivity(intent);
	}
	

	public boolean onCreateOptionsMenu(Menu menu) {
		//MenuItem populateItem = menu.add(Menu.NONE, POPULATE_ID, 0, "Sign up");
		//populateItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		return true;
	}
	
	
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return false;
	}

	
}
