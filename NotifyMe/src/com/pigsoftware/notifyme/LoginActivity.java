package com.pigsoftware.notifyme;


import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class LoginActivity extends SherlockFragmentActivity implements Callback{
	// Menu identifiers
	static final int POPULATE_ID = Menu.FIRST;
	static final int CLEAR_ID = Menu.FIRST + 1;
	
	static final String TAG = "NOTIFYME";

	EditText userField;
	EditText passwordField;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.app_name);

		userField = (EditText) findViewById(R.id.userField);
		passwordField = (EditText) findViewById(R.id.passwordField);

		Button buttonLogin = (Button) findViewById(R.id.loginButton);
		buttonLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				test();
				
				/*
				DownloadWebPageTask task = new DownloadWebPageTask();
				task.execute(new String[] { "http://www.vogella.com" });
				*/

			}
		});

	}
	
	public void test(){
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("method", "login"));
		nameValuePairs.add(new BasicNameValuePair("user", userField.getEditableText().toString()));
		nameValuePairs.add(new BasicNameValuePair("password", passwordField.getEditableText().toString()));
		
		Server server = new Server(this, nameValuePairs);
		server.execute(new String[]{});
	}

	public void login(String result) {
		JSONArray jArray;
		try {
			jArray = new JSONArray(result);
			if(jArray.length()>0){
				JSONObject json_data = jArray.getJSONObject(0);
				if(!json_data.isNull("result")){
					Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
				}else{
					Utils.USER_ID = json_data.getString("USER_ID");
					Log.v(TAG,Utils.USER_ID);
					Intent intent = new Intent(this, MainActivity.class);
					startActivity(intent);
				}
			} else {
				Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
			}
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
	}

	

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem populateItem = menu.add(Menu.NONE, POPULATE_ID, 0, "Sign up");
		populateItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getTitle().equals("Sign up")) {
			Intent intent = new Intent(this, SignUpActivity.class);
			startActivity(intent);
		}

		return false;
	}

	@Override
	public void callback(String result) {
		Log.v(TAG,"EN EL CALLBACK DE LOGIN "+result);
		login(result);
		
	}

}
