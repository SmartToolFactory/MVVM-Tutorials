package com.example.tutorial4livedata_mvvm_recyclerview2extendlivedata;

import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorial4livedata_mvvm_recyclerview2extendlivedata.databinding.ActivityMainBinding;
import com.example.tutorial4livedata_mvvm_recyclerview2extendlivedata.viewmodel.MarkerViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // Set this to update view when LiveData changes
        binding.setLifecycleOwner(this);

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MarkerViewModel markerListViewModel = ViewModelProviders.of(this).get(MarkerViewModel.class);
        binding.setViewModel(markerListViewModel);

        MarkerAdapter adapter = new MarkerAdapter(markerListViewModel.getMarkerList());
        recyclerView.setAdapter(adapter);

    }
}
