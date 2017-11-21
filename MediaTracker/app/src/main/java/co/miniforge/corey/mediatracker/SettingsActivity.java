package co.miniforge.corey.mediatracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import co.miniforge.corey.mediatracker.ui_helpers.ThemeHelper;

public class SettingsActivity extends AppCompatActivity {

    Button toggle;
    TextView textView;

    ThemeHelper themeHelper;

//    public SettingsActivity(Context context) {
//        themeHelper = new ThemeHelper(context);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toggle = (Button) findViewById(R.id.toggle);
        textView = (TextView) findViewById(R.id.textView);

        themeHelper = new ThemeHelper(getApplicationContext());
        themeHelper.themeBackground(textView.getRootView());
        themeHelper.themeTextView(textView);

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeHelper.enableDarkTheme(!themeHelper.darkThemeEnabled());

                themeHelper.themeBackground(view.getRootView());
                themeHelper.themeTextView(textView);

                Intent intent = new Intent(view.getContext(), MyListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
