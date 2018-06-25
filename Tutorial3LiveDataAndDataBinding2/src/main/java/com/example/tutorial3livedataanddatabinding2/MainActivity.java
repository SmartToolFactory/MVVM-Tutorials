package com.example.tutorial3livedataanddatabinding2;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tutorial3livedataanddatabinding2.databinding.ActivityMainBinding;
import com.example.tutorial3livedataanddatabinding2.viewmodel.MyViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyViewModel myViewModel = ViewModelProviders.of(MainActivity.this).get(MyViewModel.class);
        myViewModel.setUpLiveData();

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(myViewModel);
        // Required for binding with LiveData
        binding.setLifecycleOwner(this);

        /*
           myViewModel.getUsersLiveData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                binding.textView.setText(myViewModel.getUserRecords(users));
            }
        });
        */


    }
}
