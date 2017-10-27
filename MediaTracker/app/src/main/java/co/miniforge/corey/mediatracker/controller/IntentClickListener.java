package co.miniforge.corey.mediatracker.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Jonn on 10/26/2017.
 */

public class IntentClickListener implements View.OnClickListener {
    private Context activity;
    private Context intentActivity;

    /**
     * Constructor for the StartClickListener
     *
     * @param activity - LandingActivity - Receives an activity so that it can find the Views in
     *                 activity to perform methods and operations on them.
     */
    public IntentClickListener(Context activity) {
        this.activity = activity;
    }

    /**
     * On click, initiate the GuessingActivity.
     *
     * @param view - View - The View object that was clicked on.
     */
    @Override
    public void onClick(View view) {
        if(intentActivity != null) {
            Intent intent = new Intent(activity.getApplicationContext(), intentActivity.getClass());
            activity.startActivity(intent);
        } else {
            Toast.makeText(activity.getApplicationContext(), "There was an exception when " +
                            "attempting to get the Intent Activity.", Toast.LENGTH_SHORT).show();
        }
    }

    public Context getActivity() {
        return activity;
    }

    public void setActivity(Context activity) {
        this.activity = activity;
    }

    public Context getIntentActivity() {
        return intentActivity;
    }

    public void setIntentActivity(Context intentActivity) {
        this.intentActivity = intentActivity;
    }
}

