package co.miniforge.corey.mediatracker.ui_helpers;

/**
 * Created by Jonn on 11/20/2017.
 */

import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;

import co.miniforge.corey.mediatracker.MyListActivity;
import co.miniforge.corey.mediatracker.R;
import co.miniforge.corey.mediatracker.model.MediaItem;
import co.miniforge.corey.mediatracker.model.MediaItemType;
import co.miniforge.corey.mediatracker.model.MovieModel;
import co.miniforge.corey.mediatracker.model.TVModel;

public class AddPopUpMenuHelper implements PopupMenu.OnMenuItemClickListener {

    MyListActivity activity;

    public AddPopUpMenuHelper(MyListActivity activity){
        this.activity = activity;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Log.d("Debug: ", "AddPopUpMenuHelper: " + item.toString());
        switch (item.getItemId()){
            case R.id.generic:
                activity.addMediaItem(new MediaItem(MediaItemType.Generic));
                break;
            case R.id.tv:
                activity.addMediaItem(new TVModel(MediaItemType.TV));
                break;
            case R.id.movie:
                activity.addMediaItem(new MovieModel(MediaItemType.Movie));
                break;
            default:
                activity.addMediaItem(new MediaItem(MediaItemType.Generic));
                break;
        }

        return true;
    }
}