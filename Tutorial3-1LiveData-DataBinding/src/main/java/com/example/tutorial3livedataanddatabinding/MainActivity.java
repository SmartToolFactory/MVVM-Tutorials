package com.example.tutorial3livedataanddatabinding;

import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tutorial3livedataanddatabinding.databinding.ActivityMainBinding;
import com.example.tutorial3livedataanddatabinding.viewmodel.CounterViewModel;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        /*
            LifeCycleOwner should be set for LiveData changes to be propagated to UI for this binding
         */
        binding.setLifecycleOwner(this);

        CounterViewModel counterViewModel = ViewModelProviders.of(this).get(CounterViewModel.class);

        binding.setViewModel(counterViewModel);

    }

}
