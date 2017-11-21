package co.miniforge.corey.mediatracker.model;

import org.json.JSONObject;

import co.miniforge.corey.mediatracker.R;

/**
 * Created by corey on 10/20/17.
 */

public class MovieModel extends MediaItem {
    public int myRating;
    public String genre;

    public MovieModel(JSONObject jsonObject) {
        super(jsonObject);
    }

    public MovieModel(MediaItemType mediaItemType) {
        super(mediaItemType);

        this.myRating = 0;
        this.genre = "defaultGenre";

        this.type = getTypeForString("Movie");
    }
}
