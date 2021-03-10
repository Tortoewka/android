package com.example.myandroidprojekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class Settings extends AppCompatActivity {
    private static final String MY_PREFERENCES ="nightModePreferences" ;
    private static final String KEY_NIGHT_MODE ="nightMode" ;
    private static final String KEY_MAIN_SCREEN ="memoryScreen" ;
    private static final String KEY_EQUATION ="equation" ;

    SharedPreferences sharedPreferences;

    TextView textView;
    Switch changeTheme;
    String equation ="";

    TextView mainScreen;
    TextView memoryScreen;




        private void saveNightModeState(boolean nightMode) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(KEY_NIGHT_MODE, nightMode).apply();
        }

        public void checkNightModeActivated() {
            if (sharedPreferences.getBoolean(KEY_NIGHT_MODE, false)) {
                changeTheme.setChecked(true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                changeTheme.setChecked(false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        changeTheme = findViewById(R.id.change_theme);

        changeTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                saveNightModeState(true);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                saveNightModeState(false);
            }
            recreate();
        });
    }
}
