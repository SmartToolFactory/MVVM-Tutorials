package com.example.tutorial4livedatamvvmrecyclerview;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tutorial4livedatamvvmrecyclerview.databinding.ActivityMainBinding;
import com.example.tutorial4livedatamvvmrecyclerview.viewmodel.MarkerViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MarkerViewModel markerListViewModel = ViewModelProviders.of(this).get(MarkerViewModel.class);
        binding.setViewModel(markerListViewModel);

        MarkerAdapter adapter = new MarkerAdapter(markerListViewModel.getMarkerList().getValue());

        markerListViewModel.getMarkerList().observe(this, markerList -> adapter.setMarkerList(markerList));
        recyclerView.setAdapter(adapter);
    }
}
