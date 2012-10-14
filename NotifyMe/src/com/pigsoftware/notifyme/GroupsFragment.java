package com.pigsoftware.notifyme;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class GroupsFragment extends SherlockFragment implements
		OnItemClickListener, Callback {
	
	ArrayList<String> popups;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setHasOptionsMenu(true);
		GridView gridView = (GridView) getView().findViewById(R.id.grid_view);

		// Instance of ImageAdapter Class
		gridView.setAdapter(new GridAdapter(getActivity()));
		gridView.setOnItemClickListener(this);

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("method", "getGroups"));

		Server server = new Server(this, nameValuePairs);
		server.execute(new String[] {});

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.catalogue_layout, null);
	}

	/**
	 * Create a new instance of DetailsFragment, initialized to show the text at
	 * 'index'.
	 */
	public static GroupsFragment newInstance() {
		GroupsFragment f = new GroupsFragment();
		return f;
	}

	public ArrayList<String> loadImagesPopup() {
		ArrayList<String> tempo = new ArrayList<String>();

		tempo.add("DIEGO");

		return tempo;
	}
	
	
	public void fillAdapter(String result) {
		JSONArray jArray;
		try {
			jArray = new JSONArray(result);
			if (jArray.length() > 0) {				
				popups = new ArrayList<String>();
				for (int i = 0; i < jArray.length(); i++) {
					JSONObject json_data = jArray.getJSONObject(i);
					popups.add(json_data.getString("GROUP_NAME"));
				}	
			}
		} catch (JSONException e) {

			e.printStackTrace();
		}
	}

	public class GridAdapter extends BaseAdapter {
		private Context mContext;

		// Keep all Images in array

		

		// Constructor
		public GridAdapter(Context c) {
			if(popups==null){
				popups = new ArrayList<String>();
			}
			mContext = c;

		}

		@Override
		public int getCount() {
			return popups.size();
		}

		@Override
		public Object getItem(int position) {
			return popups.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater layoutInflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = layoutInflater.inflate(R.layout.gridview_item_layout,
					null);
			ImageView imageView = (ImageView) view
					.findViewById(R.id.imageView1);

			try {

				imageView.setImageDrawable(new BitmapDrawable(getResources()
						.getAssets().open("ic_launcher.png")));

			} catch (Exception e) {

			}
			TextView nameTextView = (TextView) view
					.findViewById(R.id.posterNameTextView);
			nameTextView.setText(popups.get(position));
			return view;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent(getSherlockActivity(), GroupActivity.class);
		startActivity(intent);

	}

	@Override
	public void callback(String result) {
		fillAdapter(result);

	}
}
// Menu identifiers

