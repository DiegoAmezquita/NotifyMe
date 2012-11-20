package com.pigsoftware.notifyme;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;

public class MainActivity extends SherlockFragmentActivity implements Callback {
	TabHost mTabHost;
	ViewPager mViewPager;
	TabsAdapter mTabsAdapter;
	MainActivity main;
	
	Notification nots[];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_tabs_pager);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.app_name);

		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();

		mViewPager = (ViewPager) findViewById(R.id.pager);

		mTabsAdapter = new TabsAdapter(this, mTabHost, mViewPager);

		mTabsAdapter.addTab(mTabHost.newTabSpec("nots").setIndicator("Nots"),
				NotsFragment.class, null);
		mTabsAdapter.addTab(mTabHost.newTabSpec("groups")
				.setIndicator("Groups"), GroupsFragment.class, null);
		mTabsAdapter.addTab(mTabHost.newTabSpec("admin").setIndicator("Admin"),
				AdminFragment.class, null);

		main = this;

		timer = new Timer();
		timer.schedule(new secondTask(), 0, 2000);

		if (savedInstanceState != null) {
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		/*
		 * SubMenu sub = menu.addSubMenu("other");
		 * 
		 * sub.getItem().setShowAsAction( MenuItem.SHOW_AS_ACTION_ALWAYS |
		 * MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		 */
		return true;
	}

	public static class TabsAdapter extends FragmentPagerAdapter implements
			TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {
		private final Context mContext;
		private final TabHost mTabHost;
		private final ViewPager mViewPager;
		private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

		static final class TabInfo {
			// private final String tag;
			private final Class<?> clss;
			private final Bundle args;

			TabInfo(String _tag, Class<?> _class, Bundle _args) {
				// tag = _tag;
				clss = _class;
				args = _args;
			}
		}

		static class DummyTabFactory implements TabHost.TabContentFactory {
			private final Context mContext;

			public DummyTabFactory(Context context) {
				mContext = context;
			}

			@Override
			public View createTabContent(String tag) {
				View v = new View(mContext);
				v.setMinimumWidth(0);
				v.setMinimumHeight(0);
				return v;
			}
		}

		public TabsAdapter(FragmentActivity activity, TabHost tabHost,
				ViewPager pager) {
			super(activity.getSupportFragmentManager());
			mContext = activity;
			mTabHost = tabHost;
			mViewPager = pager;
			mTabHost.setOnTabChangedListener(this);
			mViewPager.setAdapter(this);
			mViewPager.setOnPageChangeListener(this);
		}

		public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
			tabSpec.setContent(new DummyTabFactory(mContext));
			String tag = tabSpec.getTag();

			TabInfo info = new TabInfo(tag, clss, args);
			mTabs.add(info);
			mTabHost.addTab(tabSpec);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mTabs.size();
		}

		@Override
		public Fragment getItem(int position) {
			TabInfo info = mTabs.get(position);

			return Fragment.instantiate(mContext, info.clss.getName(),
					info.args);
		}

		@Override
		public void onTabChanged(String tabId) {
			int position = mTabHost.getCurrentTab();
			if (Utils.mMode != null)
				Utils.mMode.finish();
			mViewPager.setCurrentItem(position);
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
		}

		@Override
		public void onPageSelected(int position) {
			// Unfortunately when TabHost changes the current tab, it kindly
			// also takes care of putting focus on it when not in touch mode.
			// The jerk.
			// This hack tries to prevent this from pulling focus out of our
			// ViewPager.
			TabWidget widget = mTabHost.getTabWidget();
			int oldFocusability = widget.getDescendantFocusability();
			widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
			mTabHost.setCurrentTab(position);
			widget.setDescendantFocusability(oldFocusability);
		}

		@Override
		public void onPageScrollStateChanged(int state) {
		}
	}

	Timer timer = new Timer();

	long starttime = 0;

	// tells activity to run on ui thread
	class secondTask extends TimerTask {

		@Override
		public void run() {
			main.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					if (starttime == 0) {
						starttime = System.currentTimeMillis();
					}
					long millis = System.currentTimeMillis() - starttime;
					int seconds = (int) (millis / 1000);
					int minutes = seconds / 60;
					seconds = seconds % 60;

					if (!Utils.serverBusy) {
						ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
						nameValuePairs.add(new BasicNameValuePair("method","getLastNots"));

						Server server = new Server(main, nameValuePairs);
						server.execute(new String[] {});
					}

					//Log.v("TAG", String.format("%d:%02d", minutes, seconds));
				}
			});
		}
	}

	@Override
	public void callback(String result, boolean Image, Bitmap bitmap) {
		JSONArray jArray;
		try {
			jArray = new JSONArray(result);
			//Log.v("TAG",jArray.length()+" --  "+Utils.NOTS.length);		
			if (jArray.length() > 0&&jArray.length()>Utils.NOTS.length) {
				nots = new Notification[jArray.length()];
				for (int i = 0; i < jArray.length(); i++) {
					JSONObject json_data = jArray.getJSONObject(i);
					Notification not = new Notification();
					not.GROUP_ID = json_data.getString("GROUP_ID");
					not.NOTIFICATION_DATE = json_data.getString("NOTIFICATION_DATE");
					not.NOTIFICATION_ID = json_data.getString("NOTIFICATION_ID");
					not.NOTIFICATION_MESSAGE = json_data.getString("NOTIFICATION_MESSAGE");
					not.NOTIFICATION_TIME = json_data.getString("NOTIFICATION_TIME");
					not.NOTIFICATION_TITLE= json_data.getString("NOTIFICATION_TITLE");
					not.NOTIFICATION_TYPE_ID= json_data.getString("NOTIFICATION_TYPE_ID");						
					nots[i] = not;	
					
				}
				Utils.NOTS = nots;
				Log.v("TAG","NUEVA NOTIFICACION");		
				Utils.createNotificationAndroid(getApplicationContext());
			}
		}catch(Exception e){
			
		}
		

	};

}
