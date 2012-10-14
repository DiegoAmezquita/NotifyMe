package com.pigsoftware.notifyme;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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
	ArrayList<Bitmap> arrayBImage;
	int countImagesLoaded = 0;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		arrayBImage = new ArrayList<Bitmap>();
		setHasOptionsMenu(true);

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("method", "getGroups"));
		Server server = new Server(this, nameValuePairs);
		server.execute(new String[] {});
		// Instance of ImageAdapter Class

	}

	public void imageLoadFinish() {
		countImagesLoaded++;
		Log.v("NOTIFYME", "" + countImagesLoaded);
		if (popups.size() == countImagesLoaded) {
			GridView gridView = (GridView) getView().findViewById(
					R.id.grid_view);
			gridView.setAdapter(new GridAdapter(getActivity()));
			gridView.setOnItemClickListener(this);
		}
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
					ImageServer ss = new ImageServer(this,Utils.HOST_ROOT+"group"+popups.size()+".jpg");
					ss.execute(new String[] {});
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
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

		// Constructor
		public GridAdapter(Context c) {
			if (popups == null) {
				popups = new ArrayList<String>();
			}
			mContext = c;

		}

		@Override
		public int getCount() {
			Log.v("TAG", "TAMAÑO " + popups.size());
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
			Log.v("TAG", "ENTRA ACA");
			LayoutInflater layoutInflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = layoutInflater.inflate(R.layout.gridview_item_layout,
					null);
			ImageView imageView = (ImageView) view
					.findViewById(R.id.imageView1);

			try {
				Log.v("TAG", "BITMAP");
				imageView.setImageBitmap(arrayBImage.get(position));
				// imageView.setImageDrawable(new BitmapDrawable(getResources()
				// .getAssets().open("ic_launcher.png")));

			} catch (Exception e) {
				Log.v("TAG", "FALLO CON LA IMAGEN " + e.getMessage());
			}
			TextView nameTextView = (TextView) view
					.findViewById(R.id.posterNameTextView);
			nameTextView.setText(popups.get(position));
			return view;
		}

	}

	public class ImageServer extends AsyncTask<String, Void, String> {

		Callback classToCall;
		Bitmap tempo;
		String url;

		public ImageServer(Callback classToCall,String url) {
			this.classToCall = classToCall;
			this.url=url;
		}

		@Override
		protected String doInBackground(String... urls) {
			String result = "";
			// http post
			try {
				Log.e("src", url);
				URL urlImage = new URL(url);
				HttpURLConnection connection = (HttpURLConnection) urlImage
						.openConnection();
				connection.setDoInput(true);
				connection.connect();
				InputStream input = connection.getInputStream();
				Bitmap myBitmap = BitmapFactory.decodeStream(input);
				Log.e("Bitmap", "returned");
				tempo = myBitmap;
			} catch (Exception e) {
				e.printStackTrace();
				Log.v("Exception", e.getMessage());
				return null;
			}

			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			Log.v("TAG", result);
			arrayBImage.add(tempo);
			imageLoadFinish();
			// classToCall.callback(result);
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

