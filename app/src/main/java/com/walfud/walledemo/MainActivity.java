package com.walfud.walledemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.walfud.walle.WallE;

public class MainActivity extends Activity {
    public static final String TAG = "MainActivity";

    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
            }
        });

        WallE.initialize(getApplicationContext());
    }
}
