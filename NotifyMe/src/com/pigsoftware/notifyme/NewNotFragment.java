package com.pigsoftware.notifyme;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class NewNotFragment extends SherlockFragmentActivity implements Callback{
	// Menu identifiers
	static final int POPULATE_ID = Menu.FIRST;
	static final int CLEAR_ID = Menu.FIRST + 1;
	private static final int YOUR_PI_REQ_CODE = 0;

	private String array_spinner[];

	EditText nameInput;
	EditText notInput;

	String GROUP_ID;

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
		
		
		GROUP_ID = getIntent().getStringExtra("GROUP_ID");
		
		
		
		Spinner s = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter adapter = new ArrayAdapter(this,
		android.R.layout.simple_spinner_item, array_spinner);
		s.setAdapter(adapter);

		
		
		
		nameInput = (EditText) findViewById(R.id.nameInput);
		notInput = (EditText) findViewById(R.id.notInput);
		
		Button buttonCreate = (Button) findViewById(R.id.button1);
		buttonCreate.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				createNotification();
			}
		});
		
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
		//Toast.makeText(this, inputSearch.getEditableText().toString(), Toast.LENGTH_SHORT).show();
	}
	
	public void createNotification(){
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("method", "createNotification"));
		nameValuePairs.add(new BasicNameValuePair("title", nameInput.getEditableText().toString()));
		nameValuePairs.add(new BasicNameValuePair("message", notInput.getEditableText().toString()));
		nameValuePairs.add(new BasicNameValuePair("group", GROUP_ID));
		
		
		Server server = new Server(this, nameValuePairs);
		server.execute(new String[]{});
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

	@Override
	public void callback(String result, boolean Image, Bitmap bitmap) {
		finish();
		//Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
		//Utils.createNotificationAndroid(getApplicationContext());
		
		
	}
	
	

	
}
