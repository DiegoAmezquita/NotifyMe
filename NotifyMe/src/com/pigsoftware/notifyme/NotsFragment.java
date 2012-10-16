package com.pigsoftware.notifyme;

import java.util.ArrayList;


import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class NotsFragment extends SherlockListFragment implements Callback{

	// Menu identifiers
	static final int POPULATE_ID = Menu.FIRST;
	static final int CLEAR_ID = Menu.FIRST + 1;
	
	ArrayList<Bitmap> arrayBImage;
	int countImagesLoaded = 0;
	ArrayList<Bitmap> arrayGroupImage;
	int countImagesGroupLoaded = 0;
	
	ActionMode mMode;

	// This is the Adapter being used to display the list's data.
	SimpleCursorAdapter mAdapter;

	// If non-null, this is the current filter the user has provided.
	String mCurFilter;

	
	Notification[] values= null;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		arrayBImage = new ArrayList<Bitmap>();
		
		setEmptyText("No data.  Select 'Populate' to fill with data from Z to A at a rate of 4 per second.");
		setHasOptionsMenu(true);

		ArrayAdapterNots adapter = new ArrayAdapterNots(this.getSherlockActivity(), Utils.NOTS);
		setListAdapter(adapter);
		setListShown(true);	

	}
	
	

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		MenuItem populateItem = menu.add(Menu.NONE, POPULATE_ID, 0, "Populate");
		populateItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		MenuItem clearItem = menu.add(Menu.NONE, CLEAR_ID, 0, "Clear");
		clearItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case POPULATE_ID:
			
			return true;

		case CLEAR_ID:

			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// Insert desired behavior here.
		Log.i("NOTTIFYME", "Item clicked: " + id);
		Log.v("NOTTIFYME", this.getSherlockActivity().getPackageName());
		mMode = getSherlockActivity().startActionMode(new AnActionModeOfEpicProportions());

	}

	// These are the rows that we will retrieve.

	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		mAdapter.swapCursor(data);

		// The list should now be shown.
		if (isResumed()) {
			setListShown(true);
		} else {
			setListShownNoAnimation(true);
		}
	}

	public void onLoaderReset(Loader<Cursor> loader) {
		mAdapter.swapCursor(null);
	}
	
	
	
	private final class AnActionModeOfEpicProportions implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            //Used to put dark icons on light action bar
            boolean isLight = true;

            menu.add("Save")
                .setIcon(isLight ? R.drawable.ic_compose_inverse : R.drawable.ic_compose)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

            menu.add("Search")
                .setIcon(isLight ? R.drawable.ic_search_inverse : R.drawable.ic_search)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

            menu.add("Refresh")
                .setIcon(isLight ? R.drawable.ic_refresh_inverse : R.drawable.ic_refresh)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

            menu.add("Save")
                .setIcon(isLight ? R.drawable.ic_compose_inverse : R.drawable.ic_compose)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

            menu.add("Search")
                .setIcon(isLight ? R.drawable.ic_search_inverse : R.drawable.ic_search)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

            menu.add("Refresh")
                .setIcon(isLight ? R.drawable.ic_refresh_inverse : R.drawable.ic_refresh)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            mode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
        }
    }

	
	


	@Override
	public void callback(String result,boolean image,Bitmap bitmap) {
		
		
	}
	
}