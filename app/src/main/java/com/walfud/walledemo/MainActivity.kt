package com.walfud.walledemo

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.walfud.walle.lang.md5
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            Toast.makeText(this@MainActivity, "a".md5(), Toast.LENGTH_SHORT).show()
        }
        btn.performClick()
    }
}
