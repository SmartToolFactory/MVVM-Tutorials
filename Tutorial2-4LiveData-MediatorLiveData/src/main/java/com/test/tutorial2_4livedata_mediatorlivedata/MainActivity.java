package com.test.tutorial2_4livedata_mediatorlivedata;

import androidx.lifecycle.MediatorLiveData;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediatorLiveData<String> mediatorLiveData = new MediatorLiveData<>();


    }
}
