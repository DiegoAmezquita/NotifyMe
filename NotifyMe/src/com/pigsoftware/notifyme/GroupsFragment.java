package com.pigsoftware.notifyme;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);

		GridView gridView = (GridView) getView().findViewById(R.id.grid_view);
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

	public class GridAdapter extends BaseAdapter {
		private Context mContext;

		// Keep all Images in array

		// Constructor
		public GridAdapter(Context c) {
			mContext = c;

		}

		@Override
		public int getCount() {
			return Utils.GROUPS.length;
		}

		@Override
		public Object getItem(int position) {
			return Utils.GROUPS[position];
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
				imageView.setImageBitmap(Utils.GROUPS[position].GROUP_IMAGE);
				// imageView.setImageDrawable(new BitmapDrawable(getResources()
				// .getAssets().open("ic_launcher.png")));

			} catch (Exception e) {
				Log.v("TAG", "LOAD IMAGEN FAIL " + e.getMessage());
			}
			TextView nameTextView = (TextView) view
					.findViewById(R.id.posterNameTextView);
			nameTextView.setText(Utils.GROUPS[position].GROUP_NAME);
			return view;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {			
		Intent intent = new Intent(getSherlockActivity(), GroupActivity.class);
		intent.putExtra("GROUP_ID", Utils.GROUPS[arg2].GROUP_ID);
		intent.putExtra("GROUP_NAME", Utils.GROUPS[arg2].GROUP_NAME);
		startActivity(intent);

	}

	@Override
	public void callback(String result, boolean image, Bitmap bitmap) {

	}
}
// Menu identifiers

