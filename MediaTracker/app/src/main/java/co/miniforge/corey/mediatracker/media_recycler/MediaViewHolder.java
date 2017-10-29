package co.miniforge.corey.mediatracker.media_recycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import co.miniforge.corey.mediatracker.MediaDetailActivity;
import co.miniforge.corey.mediatracker.MyListActivity;
import co.miniforge.corey.mediatracker.R;
import co.miniforge.corey.mediatracker.model.MediaItem;

/**
 * Created by corey on 10/15/17.
 */

public class MediaViewHolder extends RecyclerView.ViewHolder {
    TextView mediaName;
    TextView mediaDescription;

    View inflated;

    Context context;

    public MediaViewHolder(View itemView) {
            super(itemView);

        locateViews(itemView);
    }

    private void locateViews(View itemView) {
        inflated = itemView;
        context = itemView.getContext();

        mediaName = itemView.findViewById(R.id.mediaName);
        mediaDescription = itemView.findViewById(R.id.mediaDescription);
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

        inflated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Hint: mediaItem.toJson().toString() && context.startActivity);
                Intent intent = new Intent(context, MediaDetailActivity.class);
                intent.putExtra(MyListActivity.mediaExtra, mediaItem.toJson().toString());
                context.startActivity(intent);
            }
        });
    }
}
