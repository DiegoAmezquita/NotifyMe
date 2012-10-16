package com.pigsoftware.notifyme;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

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
//			Log.v("TAG", result);
//			arrayBImage.add(tempo);
//			imageLoadFinish();
			classToCall.callback("",true,tempo);
		}
	}