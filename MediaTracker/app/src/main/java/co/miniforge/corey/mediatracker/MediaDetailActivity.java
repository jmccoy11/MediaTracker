package co.miniforge.corey.mediatracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import co.miniforge.corey.mediatracker.model.MediaItem;

/**
 * This activity will display the contents of a media item and allow the user to update the contents
 * of the item. When the user clicks the save button, the activity should create an intent that goes
 * back to MyListActivity and puts the MediaItem into the intent (If you are stuck on that, read through
 * the code in MyListActivity)
 */
public class MediaDetailActivity extends AppCompatActivity {
    EditText title;
    EditText description;
    EditText url;

    Button save;
    Button cancel;

    MediaItem mediaItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_detail);

        locateViews();

        bindFunctionality();
    }

    private void locateViews() {
        this.title = (EditText) findViewById(R.id.et_title);
        this.description = (EditText) findViewById(R.id.et_description);
        this.url = (EditText) findViewById(R.id.et_url);

        this.save = (Button) findViewById(R.id.btn_save);
        this.cancel = (Button) findViewById(R.id.btn_cancel);
    }

    private void bindFunctionality() {
        if(getIntent().hasExtra(MyListActivity.mediaExtra)) {
            String mediaItemJSONString = getIntent().getStringExtra(MyListActivity.mediaExtra);

            JSONObject json = null;
            try {
                json = new JSONObject(mediaItemJSONString);
            } catch (JSONException e){
                Log.e("toJSONError", "There was an error creating the JSON object"
                        + e.getStackTrace());
            }

            if(json!=null){
                mediaItem = new MediaItem(json);

                title.setText(mediaItem.title);
                description.setText(mediaItem.description);
                url.setText(mediaItem.url);
            } else {
                Log.e("JSONError", "There was an error creating a media item from the JSONObject");
            }
        }

        save.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                mediaItem.title = title.getText().toString();
                mediaItem.description = description.getText().toString();
                mediaItem.url = url.getText().toString();

                Intent intent = new Intent(getApplicationContext(), MyListActivity.class);
                intent.putExtra(MyListActivity.mediaExtra, mediaItem.toJson().toString());
                startActivity(intent);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
