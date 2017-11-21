package co.miniforge.corey.mediatracker.media_recycler;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import co.miniforge.corey.mediatracker.MediaDetailActivity;
import co.miniforge.corey.mediatracker.MyListActivity;
import co.miniforge.corey.mediatracker.R;
import co.miniforge.corey.mediatracker.model.MediaItem;
import co.miniforge.corey.mediatracker.model.MediaItemType;
import co.miniforge.corey.mediatracker.ui_helpers.ThemeHelper;

/**
 * Created by corey on 10/15/17.
 */

public class MediaViewHolder extends RecyclerView.ViewHolder {
    TextView mediaName;
    TextView mediaDescription;
    ImageView mediaImageView;

    View inflated;

    Context context;

    ThemeHelper themeHelper;

    public MediaViewHolder(View itemView) {
        super(itemView);

        locateViews(itemView);

        themeHelper = new ThemeHelper(context);
    }

    private void locateViews(View itemView) {
        inflated = itemView;
        context = itemView.getContext();

        mediaName = itemView.findViewById(R.id.mediaName);
        mediaDescription = itemView.findViewById(R.id.mediaDescription);
        mediaImageView = itemView.findViewById(R.id.mediaImageView);
    }

    /**
     * Set the OnClickListener for this View to create an intent to go to MediaDetailActivity.
     * Put Extra into the intent with the media.toJson.toString.
     *
     * @param mediaItem - MediaItem - The Item that belongs to the View that was clicked.
     */
    public void bindData(final MediaItem mediaItem){
        this.mediaName.setText(mediaItem.title);
        this.mediaDescription.setText(mediaItem.description);

        themeHelper.themeTextView(mediaName, mediaDescription);

        inflated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Hint: mediaItem.toJson().toString() && context.startActivity);

                Intent intent = new Intent(context, MediaDetailActivity.class);
                intent.putExtra(MyListActivity.mediaExtra, mediaItem.toJson().toString());

                context.startActivity(intent);
            }
        });

        /*
            This is the OnLongClick that will delete a media item.
         */
        inflated.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ((MyListActivity)context).deleteMediaItem(mediaItem);
                return true;
            }
        });
    }
}
