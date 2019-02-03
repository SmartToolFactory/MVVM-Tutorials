package com.test.tutorial2_4livedata_mediatorlivedata;

import android.arch.lifecycle.MediatorLiveData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediatorLiveData<String> mediatorLiveData = new MediatorLiveData<>();


    }
}
