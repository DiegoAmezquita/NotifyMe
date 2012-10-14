package com.pigsoftware.notifyme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
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

	}
	
	public void search(){
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
