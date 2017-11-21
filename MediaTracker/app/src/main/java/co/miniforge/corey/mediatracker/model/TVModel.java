package co.miniforge.corey.mediatracker.model;

import org.json.JSONObject;

import co.miniforge.corey.mediatracker.R;

/**
 * Created by corey on 10/20/17.
 */

public class TVModel extends MediaItem {
    public TVModel(JSONObject jsonObject) {
        super(jsonObject);
    }

    public TVModel(MediaItemType mediaItemType) {
        super(mediaItemType);


        this.type = getTypeForString("TV");
    }
}
