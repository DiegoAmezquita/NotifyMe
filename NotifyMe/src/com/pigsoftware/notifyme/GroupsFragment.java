package com.pigsoftware.notifyme;


import java.util.ArrayList;
import java.util.List;

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
		OnItemClickListener {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setHasOptionsMenu(true);
		GridView gridView = (GridView) getView().findViewById(R.id.grid_view);

		// Instance of ImageAdapter Class
		gridView.setAdapter(new GridAdapter(getActivity()));
		gridView.setOnItemClickListener(this);
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

	public class GridAdapter extends BaseAdapter {
		private Context mContext;

		// Keep all Images in array

		ArrayList<String> popups;

		// Constructor
		public GridAdapter(Context c) {
			Log.v("GROUPS","ACA");
			popups = new ArrayList<String>();
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
			popups.add("DIEGO");
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
			Log.v("GROUPS", "CARGA UNA IMAGEN");
			ImageView imageView = (ImageView) view
					.findViewById(R.id.imageView1);

			try {
				
					imageView.setImageDrawable(new BitmapDrawable(
							getResources().getAssets().open("ic_launcher.png")));
				

			} catch (Exception e) {

			}
			TextView nameTextView = (TextView) view
					.findViewById(R.id.posterNameTextView);
			nameTextView.setText("PRUEBA");
			return view;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent(getSherlockActivity(), GroupActivity.class);
		startActivity(intent);

	}
}
	// Menu identifiers

