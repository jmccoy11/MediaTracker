package co.miniforge.corey.mediatracker.ui_helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import co.miniforge.corey.mediatracker.R;

/**
 * Created by Jonn on 11/19/2017.
 */

public class ThemeHelper {
    private SharedPreferences sharedPref;
    private int themeDarkText;
    private int themeDarkContainer;
    private int themeDarkBg;
    private int themeLightText;
    private int themeLightContainer;
    private int themeLightBg;

    public ThemeHelper(Context context) {
        try {
            themeDarkText = ContextCompat.getColor(context, R.color.themeDarkText);
            themeDarkContainer = ContextCompat.getColor(context, R.color.themeDarkContainer);
            themeDarkBg = ContextCompat.getColor(context, R.color.themeDarkBg);
            themeLightText = ContextCompat.getColor(context, R.color.themeLightText);
            themeLightContainer = ContextCompat.getColor(context, R.color.themeLightContainer);
            themeLightBg = ContextCompat.getColor(context, R.color.themeLightBg);

            sharedPref = context.getSharedPreferences("theme", 0);
        } catch (Exception exc) {

        }
    }

    public boolean darkThemeEnabled(){
        return sharedPref.getBoolean("darkTheme", false);
    }

    public void enableDarkTheme(boolean enabled){
        sharedPref.edit().putBoolean("darkTheme", enabled).apply();
    }

    public void themeTextView(TextView... textViews) {
        boolean dark = darkThemeEnabled();
        for(TextView textView : textViews) {
            textView.setTextColor(dark ? themeDarkText : themeLightText);
        }
    }

    public void themeImageContainer(View... containers) {
        boolean dark = darkThemeEnabled();
        for(View view : containers) {
            view.setBackgroundColor(dark ? themeDarkContainer : themeLightContainer);
        }
    }

    public void themeBackground(View rootview) {
        boolean dark = darkThemeEnabled();
        rootview.setBackgroundColor(dark ? themeDarkBg : themeLightBg);
    }
}
