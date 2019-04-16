package com.example.tigol;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;


public class SettingActivity extends AppCompatActivity {
    Switch nightMode, bigSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        theme();
        setContentView(R.layout.activity_setting);


//        setSupportActionBar(new Toolbar());
        getSupportActionBar().setTitle("Setting"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
    }

    private void theme() {
        SharedPreferences prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        if (prefs.getBoolean("nightMode", false)) {
            setTheme(R.style.dark);
        } else {
            setTheme(R.style.light);
        }
    }

    private void init() {
        SharedPreferences prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);

        nightMode = findViewById;
        bigSize = findViewById;
        nightMode.setChecked(prefs.getBoolean("nightMode", false));
        bigSize.setChecked(prefs.getBoolean("bigSize", false));
    }

    public void save(View view) {
        SharedPreferences.Editor editor = getSharedPreferences(getPackageName(), MODE_PRIVATE).edit();
        editor.putBoolean("nightMode", nightMode.isChecked());
        editor.putBoolean("bigSize", bigSize.isChecked());
        editor.apply();

        if (nightMode.isChecked()) {
            setTheme(R.style.dark);
        } else {
            setTheme(R.style.light);
        }
        if (bigSize.isChecked()){
            bigSize;
            nightMode;
        }else{
            bigSize;
            nightMode;
        }
        recreate();
        gotoHome();
    }

    private void gotoHome(){
        startActivity(new Intent(SettingActivity.this,MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        gotoHome();
    }
}