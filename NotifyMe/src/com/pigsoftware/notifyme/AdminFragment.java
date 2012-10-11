package com.pigsoftware.notifyme;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
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



public class AdminFragment extends SherlockFragment{

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setHasOptionsMenu(true);
		

		
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.admin_layout, null);
			
		ImageView imageView1 = (ImageView)view.findViewById(R.id.imageView1);
		imageView1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getSherlockActivity(), "Add Group", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		ImageView imageView2 = (ImageView)view.findViewById(R.id.imageView2);
		imageView2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getSherlockActivity(), "Remove Group", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		ImageView imageView3 = (ImageView)view.findViewById(R.id.imageView3);
		imageView3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getSherlockActivity(), "Pause Nots", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		ImageView imageView4 = (ImageView)view.findViewById(R.id.imageView4);
		imageView4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getSherlockActivity(), "Help", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		ImageView imageView5 = (ImageView)view.findViewById(R.id.imageView5);
		imageView5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getSherlockActivity(), "Rate App", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		ImageView imageView6 = (ImageView)view.findViewById(R.id.imageView6);
		imageView6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getSherlockActivity(), "Feedback", Toast.LENGTH_SHORT).show();
				
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

