package com.example.tutorial4livedata_mvvm_recyclerview2extendlivedata.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.tutorial4livedata_mvvm_recyclerview2extendlivedata.data.Repository;
import com.example.tutorial4livedata_mvvm_recyclerview2extendlivedata.model.Marker;
import com.example.tutorial4livedata_mvvm_recyclerview2extendlivedata.model.MarkerLiveData;

import java.util.ArrayList;
import java.util.List;


public class MarkerViewModel extends ViewModel {

    public MarkerLiveData markerLiveData = new MarkerLiveData();
    private List<MarkerLiveData> listLiveData = new ArrayList<>();

    private Repository repository;

    public MarkerViewModel() {
        repository = Repository.getsInstance();

        // Set Marker of MarkerLiveData for TextView
        Marker marker = new Marker();
        marker.setTitle("Marker Title");
        marker.setLatitude("Marker Latitude");
        marker.setAddress("Marker Address");
        markerLiveData.setValue(marker);
    }


    public List<MarkerLiveData> getMarkerList() {
        // MOCK: Make an api call or retrieve from database
        return (listLiveData = repository.getAll());
    }

    public void update() {
        System.out.println("MarkerViewModel update()");

        // Update MarkerLiveData for TextView
        markerLiveData.getValue().setTitle("Updated Marker Title");
        markerLiveData.setValue(markerLiveData.getValue());

        // Update MarkerLiveData for RecyclerView Adapter
        if (listLiveData != null) {
            for (MarkerLiveData markerLD : listLiveData) {
                markerLD.getValue().setTitle("Updated Marker Title");
                markerLD.setValue(markerLD.getValue());
            }
        }
    }
}
