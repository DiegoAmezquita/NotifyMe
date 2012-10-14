package com.pigsoftware.notifyme;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class NewNotFragment extends SherlockFragmentActivity {
	// Menu identifiers
	static final int POPULATE_ID = Menu.FIRST;
	static final int CLEAR_ID = Menu.FIRST + 1;

	private String array_spinner[];

	EditText inputSearch;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addnewnotlayout);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.newNot);
		
		array_spinner=new String[5];
		array_spinner[0]="1";
		array_spinner[1]="2";
		array_spinner[2]="3";
		array_spinner[3]="4";
		array_spinner[4]="5";
		
		
		
		
		
		
		Spinner s = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter adapter = new ArrayAdapter(this,
		android.R.layout.simple_spinner_item, array_spinner);
		s.setAdapter(adapter);

		
		
		
		
		
		
		
//		inputSearch = (EditText)findViewById(R.id.inputSearch);
//		
//		ImageView buttonSearch = (ImageView)findViewById(R.id.searchButton);
//		buttonSearch.setOnClickListener(new OnClickListener() {	
//			@Override
//			public void onClick(View v) {
//				search();
//			}
//		});

	}
	
	void search(){
		Toast.makeText(this, inputSearch.getEditableText().toString(), Toast.LENGTH_SHORT).show();
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
