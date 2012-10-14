package com.pigsoftware.notifyme;

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

public class NewGroupFragment extends SherlockFragmentActivity {
	// Menu identifiers
	static final int POPULATE_ID = Menu.FIRST;
	static final int CLEAR_ID = Menu.FIRST + 1;

	

	EditText inputSearch;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addnewgrouplayout);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.newGroup);

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
