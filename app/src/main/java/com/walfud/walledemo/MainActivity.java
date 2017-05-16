package com.walfud.walledemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.walfud.walle.WallE;
import com.walfud.walle.collection.CollectionUtils;
import com.walfud.walle.lang.ObjectUtils;

import java.util.List;

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

        List<Foo> fooList = CollectionUtils.newArrayList(
                new Foo(1, "1"),
                new Foo(2, "2"),
                new Foo(3, "3"),
                new Foo(4, "4")
        );

        List<Bar> barList = CollectionUtils.newArrayList(
                new Bar(1, "1", "other 1"),
                new Bar(2, "2", "other 2"),
                new Bar(3, "3", "other 3")
//                new Bar(4, "4", null)
        );

        boolean z = ObjectUtils.isEqual(fooList, barList, (a, b) -> a.i - b.ii);
    }

    public static class Foo {
        public int i;
        public String s;

        public Foo(int i, String s) {
            this.i = i;
            this.s = s;
        }
    }

    public static class Bar {
        public int ii;
        public String ss;
        public String others;

        public Bar(int ii, String ss, String others) {
            this.ii = ii;
            this.ss = ss;
            this.others = others;
        }
    }
}
