package com.example.tutorial3livedataanddatabinding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tutorial3livedataanddatabinding.databinding.ActivityMainBinding
import com.example.tutorial3livedataanddatabinding.viewmodel.CounterViewModel

class MainActivity : AppCompatActivity() {

    private val counterViewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 🔥 ‍LifeCycleOwner should be set for LiveData changes to be propagated to UI for this binding
        binding.lifecycleOwner = this

        binding.viewModel = counterViewModel
    }
}

/*
If you use Java, do the following :

ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
binding.setLifecycleOwner(this);
CounterViewModel counterViewModel = ViewModelProviders.of(this).get(CounterViewModel.class);
binding.setViewModel(counterViewModel);

* */