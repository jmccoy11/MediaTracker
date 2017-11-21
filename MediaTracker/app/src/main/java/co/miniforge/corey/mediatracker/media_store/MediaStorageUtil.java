package co.miniforge.corey.mediatracker.media_store;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import co.miniforge.corey.mediatracker.model.MediaItem;
import co.miniforge.corey.mediatracker.model.MediaItemType;
import co.miniforge.corey.mediatracker.model.MovieModel;
import co.miniforge.corey.mediatracker.model.TVModel;

/**
 * Created by corey on 10/15/17.
 */

public class MediaStorageUtil {
    private Context context;

    private final String cacheName = "mediaCache.dat";

    public MediaStorageUtil(Context context){
        this.context = context;
    }

    public void saveMediaData(List<MediaItem> mediaItems){
        try {
            FileOutputStream outputStream = context.openFileOutput(cacheName, Context.MODE_PRIVATE);

            JSONArray jsonArray = new JSONArray();

            for(MediaItem mediaItem : mediaItems){
                jsonArray.put(mediaItem.toJson());
            }

            JSONObject object = new JSONObject();
            object.put("array", jsonArray);

            outputStream.write(object.toString().getBytes());

            outputStream.close();
        } catch (Exception e){
            Log.e("saveDataError", String.format("There was an error: %s", e.getMessage()));
        }
    }

    public List<MediaItem> getMediaDataList(){
        List<MediaItem> mediaList = new LinkedList<>();

        try {
            FileInputStream inputStream = context.openFileInput(cacheName);

            if(inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                // Loop through lines
                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }
                // close and set result
                inputStream.close();
                JSONObject result = new JSONObject(stringBuilder.toString());

                JSONArray jsonArray = result.getJSONArray("array");

                for(int i = 0; i < jsonArray.length(); i++){
                    try {
                        if(jsonArray.getJSONObject(i).get("type").equals("TV")) {
                            mediaList.add(new TVModel(jsonArray.getJSONObject(i)));
                        }
                        else if (jsonArray.getJSONObject(i).get("type").equals("Movie")) {
                            mediaList.add(new MovieModel(jsonArray.getJSONObject(i)));
                        }
                        else {
                            mediaList.add(new MediaItem(jsonArray.getJSONObject(i)));
                        }
                    }
                    catch (Exception exc) {
                        Log.d("Debug", exc.getMessage());
                    }

                    }
            }
        } catch (Exception e) {
            Log.e("readDataError", String.format("There was an error: %s", e.getMessage()));
        }

        return mediaList;
    }
}
