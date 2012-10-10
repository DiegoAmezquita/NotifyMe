package com.pigsoftware.notifyme;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

public class GroupActivity extends SherlockListActivity {
	public static int THEME = R.style.Theme_Sherlock;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String path = intent.getStringExtra("com.example.android.apis.Path");

        if (path == null) {
            path = "";
        }

        
        String[] values = new String[] { "Check the newspaper",
				"Check the newspaper", "Check the newspaper",
				"Check the newspaper", "Check the newspaper",
				"Check the newspaper", "Check the newspaper",
				"Check the newspaper", "Check the newspaper",
				"Check the newspaper" };

		// Create an empty adapter we will use to display the loaded data.
		ArrayAdapterNots adapter = new ArrayAdapterNots(
				this, values);
       /* setListAdapter(new SimpleAdapter(this, getData(path),
                android.R.layout.simple_list_item_1, new String[] { "title" },
                new int[] { android.R.id.text1 }));
                */
		
		setListAdapter(adapter);
		
        //getListView().setTextFilterEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu sub = menu.addSubMenu("Theme");
        sub.add(0, R.style.Theme_Sherlock, 0, "Default");
        sub.add(0, R.style.Theme_Sherlock_Light, 0, "Light");
        sub.add(0, R.style.Theme_Sherlock_Light_DarkActionBar, 0, "Light (Dark Action Bar)");
        sub.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home || item.getItemId() == 0) {
            return false;
        }
        THEME = item.getItemId();
        Toast.makeText(this, "Theme changed to \"" + item.getTitle() + "\"", Toast.LENGTH_SHORT).show();
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
}