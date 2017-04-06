package com.walfud.walledemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.walfud.walle.WallE;
import com.walfud.walle.network.NetworkUtils;

public class MainActivity extends Activity {
    public static final String TAG = "MainActivity";

    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(v -> Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show());

        WallE.initialize(getApplicationContext());
        NetworkUtils.get("http://so.com").subscribe();
    }
}
