package com.pigsoftware.notifyme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class NotActivity extends SherlockFragmentActivity {
	// Menu identifiers
	static final int POPULATE_ID = Menu.FIRST;
	static final int CLEAR_ID = Menu.FIRST + 1;




	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.app_name);
		
		Button buttonLogin = (Button)findViewById(R.id.loginButton);
		buttonLogin.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				login();
			}
		});

	}
	
	public void login(){
		//TODO validar si el usuario existe y cargar la informacion de dicho usuario
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem populateItem = menu.add(Menu.NONE, POPULATE_ID, 0, "Sign up");
		populateItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		return true;
	}
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return false;
	}

	
}