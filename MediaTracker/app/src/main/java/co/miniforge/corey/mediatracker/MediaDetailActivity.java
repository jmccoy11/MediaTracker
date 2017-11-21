package co.miniforge.corey.mediatracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import co.miniforge.corey.mediatracker.model.MediaItem;
import co.miniforge.corey.mediatracker.model.MediaItemType;
import co.miniforge.corey.mediatracker.ui_helpers.ThemeHelper;

import static co.miniforge.corey.mediatracker.MyListActivity.*;

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

    View mediaDetailRoot;
    ThemeHelper themeHelper;

    Button save;
    //Button cancel;

    MediaItem mediaItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_media_detail);

        locateViews();

        bindFunctionality();

        if(mediaItem.type.equals(MediaItemType.TV)) {
            setContentView(R.layout.activity_media_detail_tv);
        }
        else if(mediaItem.type.equals(MediaItemType.Movie)) {
            setContentView(R.layout.activity_media_detail_movie);
        }
    }

    /**
     * Locate the Views in the activity and assign them to class variables.
     */
    private void locateViews() {
        this.title = (EditText) findViewById(R.id.et_title);
        this.description = (EditText) findViewById(R.id.et_description);
        this.url = (EditText) findViewById(R.id.et_url);

        this.save = (Button) findViewById(R.id.btn_save);
        //this.cancel = (Button) findViewById(R.id.btn_cancel);

        this.mediaDetailRoot = (View) findViewById(R.id.mediaDetailRoot);
    }

    /**
     * Bind functionality to for this Activity
     */
    private void bindFunctionality() {
        themeHelper = new ThemeHelper(mediaDetailRoot.getContext());
        themeHelper.themeBackground(mediaDetailRoot);
        themeHelper.themeTextView((TextView) title, (TextView) description, (TextView) url);

        // Check if the Intent has the extra mediaExtra.
        if(getIntent().hasExtra(mediaExtra)) {
            // If it does, grab the JSON from the Intent and create a new JSONObject from
            // the stringExtra.
            String mediaItemJSONString = getIntent().getStringExtra(mediaExtra);

            JSONObject json = null;
            try {
                json = new JSONObject(mediaItemJSONString);
            } catch (JSONException e){
                Log.e("toJSONError", "There was an error creating the JSON object"
                        + e.getStackTrace());
            }

            if(json!=null){
                // Use the JSONObject to instantiate the mediaItem in this class
                mediaItem = new MediaItem(json);

                // Set the text of the EditTexts to the data in the mediaItem
                title.setText(mediaItem.title);
                description.setText(mediaItem.description);
                url.setText(mediaItem.url);
            } else {
                Log.e("JSONError", "There was an error creating a media item from the JSONObject");
            }
        }

        // Set the OnClickListener for the Save button
        save.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
            promptConfirmation();
            }
        });

        // Set the OnClickListener for the Cancel Button
//        cancel.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    void promptConfirmation() {
        //https://developer.android.com/guide/topics/ui/dialogs.html#AlertDialog
//Make sure to put the code in the activity, the builder requires an activity to be passed in
//import android.support.v7.app for the alert dialog

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Save Changes").setMessage("Are you sure you want to save these changes?");

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                // grab the text from the EditTexts and set them to the variables in the
                // mediaItem
                mediaItem.title = title.getText().toString();
                mediaItem.description = description.getText().toString();
                mediaItem.url = url.getText().toString();

                // Create an intent and pass this class's mediaItem as a JSON String
                Intent intent = new Intent(getApplicationContext(), MyListActivity.class);
                intent.putExtra(mediaExtra, mediaItem.toJson().toString());
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Create an intent to go back to MyListActivity
                Intent intent = new Intent(getApplicationContext(), MyListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
