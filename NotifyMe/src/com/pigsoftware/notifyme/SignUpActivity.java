package com.pigsoftware.notifyme;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class SignUpActivity extends SherlockFragmentActivity implements Callback{
	// Menu identifiers
	static final int POPULATE_ID = Menu.FIRST;
	static final int CLEAR_ID = Menu.FIRST + 1;

	EditText userField;
	EditText passwordField;
	EditText password2Field;
	ProgressDialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registerlayout);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.app_name);

		userField = (EditText) findViewById(R.id.userField);
		passwordField = (EditText) findViewById(R.id.passwordField);
		password2Field = (EditText) findViewById(R.id.repeatPasswordField);

		Button buttonLogin = (Button) findViewById(R.id.signupButton);
		buttonLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				signup();
			}
		});

	}

	public void signup() {

		// TODO validar si el usuario existe y cargar la informacion de dicho
		// usuario

		if (!userField.getEditableText().toString().equals("")
				&& !passwordField.getEditableText().toString().equals("")&&passwordField.getEditableText().toString().equals(password2Field.getEditableText().toString())) {
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("method", "insert"));
			nameValuePairs.add(new BasicNameValuePair("user", userField
					.getEditableText().toString()));
			nameValuePairs.add(new BasicNameValuePair("password", passwordField
					.getEditableText().toString()));

			dialog = ProgressDialog.show(this, "", "Loading. Please wait...",
					true);

			Server server = new Server(this, nameValuePairs);
			server.execute(new String[] {});
		} else {
			Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// MenuItem populateItem = menu.add(Menu.NONE, POPULATE_ID, 0,
		// "Sign up");
		// populateItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return false;
	}

	@Override
	public void callback(String result, boolean Image, Bitmap bitmap) {
		dialog.dismiss();
		finish();
		
	}

}
