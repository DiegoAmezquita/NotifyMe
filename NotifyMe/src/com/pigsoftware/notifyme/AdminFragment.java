package com.pigsoftware.notifyme;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

public class AdminFragment extends SherlockFragment {

	private static final int YOUR_PI_REQ_CODE = 0;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setHasOptionsMenu(true);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.admin_layout, null);

		ImageView imageView1 = (ImageView) view.findViewById(R.id.add);
		imageView1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getSherlockActivity(), "Add Group",
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getSherlockActivity(),
						AddGroupFragment.class);
				startActivity(intent);

			}
		});

		ImageView imageView2 = (ImageView) view.findViewById(R.id.remove);
		imageView2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getSherlockActivity(), "Remove Group",
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getSherlockActivity(),
						NewNotFragment.class);
				startActivity(intent);
			}
		});

		ImageView imageView3 = (ImageView) view.findViewById(R.id.pause);
		imageView3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getSherlockActivity(), "Pause Nots",
						Toast.LENGTH_SHORT).show();

			}
		});

		ImageView imageView4 = (ImageView) view.findViewById(R.id.help);
		imageView4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getSherlockActivity(), "Help",
						Toast.LENGTH_SHORT).show();

			}
		});

		ImageView imageView5 = (ImageView) view.findViewById(R.id.rate);
		imageView5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getSherlockActivity(), "Rate App",
						Toast.LENGTH_SHORT).show();

			}
		});

		ImageView imageView6 = (ImageView) view.findViewById(R.id.feedback);
		imageView6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getSherlockActivity(), "Feedback",
						Toast.LENGTH_SHORT).show();				
			}
		});
		return view;

	}

	/**
	 * Create a new instance of DetailsFragment, initialized to show the text at
	 * 'index'.
	 */
	public static AdminFragment newInstance() {
		AdminFragment f = new AdminFragment();
		return f;
	}

	

}
// Menu identifiers

