package com.example.walle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.walfud.walle.debug.DebugUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DebugUtils.dump();
    }
}
