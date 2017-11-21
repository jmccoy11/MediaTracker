package co.miniforge.corey.mediatracker.media_recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import co.miniforge.corey.mediatracker.R;
import co.miniforge.corey.mediatracker.model.MediaItem;
import co.miniforge.corey.mediatracker.model.MediaItemType;

/**
 * Created by corey on 10/15/17.
 */

/**
 * This adapter is used to populate the RecyclerView (which is an android UI list)
 * with the appropriate layouts, data bindings, and item count
 */
public class MediaRecyclerAdapter extends RecyclerView.Adapter {
    private List<MediaItem> mediaItems = new LinkedList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View inflated = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_item, parent, false);
        View inflated = LayoutInflater
                .from(parent.getContext())
                .inflate(layoutForItemType(viewType), parent, false);

        return new MediaViewHolder(inflated);
    }

    int layoutForItemType(int viewType){
        switch (viewType){
            case 2:
                return R.layout.media_item_tv;
            case 1:
                return R.layout.media_item_movie;
            default:
                return R.layout.media_item;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getViewTypeForMediaType(
                mediaItems.get(position).type
        );
    }

    public int getViewTypeForMediaType(MediaItemType type){
        switch (type){
            case TV:
                return 2;
            case Movie:
                return 1;
            default:
                return 0;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MediaViewHolder)holder).bindData(mediaItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mediaItems.size();
    }

    public void updateList(List<MediaItem> mediaItems){
        this.mediaItems = mediaItems;
        notifyDataSetChanged();
    }
}
