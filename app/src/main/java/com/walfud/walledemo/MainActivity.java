package com.walfud.walledemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.walfud.walle.WallE;
import com.walfud.walle.android.Etc;
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

        new Thread(() -> Etc.<Void>runOnUiThread(args -> {
            for (Object arg : args) {
                Log.d(TAG, Thread.currentThread().getName() + ":" + arg.toString());
            }

            return null;
        }, null, 1, 2, 3, 4))
                .run();
    }
}
