package io.github.sanjit1.deepspacescount;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.app.Activity;
import android.content.Intent;
//import android.view.Menu;
import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            int STORAGE_PERMISSION_CODE = 1;
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.INTERNET}, STORAGE_PERMISSION_CODE);
        ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_NETWORK_STATE}, STORAGE_PERMISSION_CODE);
    }
    public void onClick(View view) {

        Intent myIntent = new Intent(MainActivity.this,
                TeamName.class);
        startActivity(myIntent);

    }


}
