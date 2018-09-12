package com.example.tutorial1_2lifecycleowner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyLiveObject myLiveObject = new MyLiveObject(this);

        AnotherLiveObject anotherLiveObject = new AnotherLiveObject(this);

        /*
         Set LifeCycle Observer for this Object
         All life cycle methods of this object with annotations will be called by this Activity
          */

        getLifecycle().addObserver(myLiveObject);
        getLifecycle().addObserver(anotherLiveObject);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "MainActivity onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "MainActivity onPause()", Toast.LENGTH_SHORT).show();

    }
}
