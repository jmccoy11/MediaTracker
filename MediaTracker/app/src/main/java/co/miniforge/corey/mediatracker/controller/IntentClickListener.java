package co.miniforge.corey.mediatracker.controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

/*
 * Jonnathon McCoy
 * 10/26/2017
 *
 * package: co.miniforge.corey.mediatracker.controller
 * class: IntentClickListener.java
 *
 * Generic Intent ClickListener.
 *
 * This class wasn't used in this project but I made it so I'm storing it here for now.
 * This class is untested.
 */

/**
 * Generic Intent ClickListener.
 *
 * This class wasn't used in this project but I made it so I'm storing it here for now.
 * This class is untested.
 */
public class IntentClickListener implements View.OnClickListener {
    private Context activity;
    private Context intentActivity;

    /**
     * Constructor for the IntentClickListener
     *
     * @param activity - Context - Receives a context so it can pass it to the Intent
     *                 creation.
     */
    public IntentClickListener(Context activity) {
        this.activity = activity;
    }

    /**
     * On click, initiate the the next activity.
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

    /**
     * Get the activity that this ClickListener belongs to.
     *
     * @return - Context - the context this ClickListener belongs to.
     */
    public Context getActivity() {
        return activity;
    }

    /**
     * Get the intentActivity that this ClickListener is set to go to.
     *
     * @return - Context - The Activity this is intended to go to.
     */
    public Context getIntentActivity() {
        return intentActivity;
    }

    /**
     * Set the activity that this clickListener is supposed to go to.
     *
     * @param intentActivity - Context - The activity this intent is supposed to go to.
     */
    public void setIntentActivity(Context intentActivity) {
        this.intentActivity = intentActivity;
    }
}

