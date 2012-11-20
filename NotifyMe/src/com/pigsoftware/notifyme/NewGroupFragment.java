package com.pigsoftware.notifyme;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class NewGroupFragment extends SherlockFragmentActivity implements Callback {
	// Menu identifiers
	static final int POPULATE_ID = Menu.FIRST;
	static final int CLEAR_ID = Menu.FIRST + 1;

	

	EditText nameET;
	EditText subjectET;
	
	ImageView imagen;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addnewgrouplayout);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.newGroup);
		
		
		nameET = (EditText)findViewById(R.id.nameInput);
		subjectET = (EditText)findViewById(R.id.subjectInput);
		
		imagen = (ImageView)findViewById(R.id.imageView1);
		
		
		Button buttonCreate = (Button) findViewById(R.id.button1);
		buttonCreate.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				createNewGroup();
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
	
	
	void createNewGroup(){
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("method", "createGroup"));
		nameValuePairs.add(new BasicNameValuePair("name", nameET.getEditableText().toString()));
		nameValuePairs.add(new BasicNameValuePair("subject", subjectET.getEditableText().toString()));
		
		
		Server server = new Server(this, nameValuePairs);
		server.execute(new String[]{});
	}
	
	
	public static String getHexString(byte[] b) throws Exception {
		  String result = "";
		  for (int i=0; i < b.length; i++) {
		    result +=
		          Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		  }
		  return result;
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
		Log.v("TAG",result);
		Toast.makeText(this, "GROUP CREATED", Toast.LENGTH_SHORT).show();
		new LoadGroups();
		
	}

	
}

