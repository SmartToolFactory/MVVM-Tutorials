package com.example.tutorial1mvvmrecyclerview.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.tutorial1mvvmrecyclerview.data.Repository;
import com.example.tutorial1mvvmrecyclerview.model.Marker;

import java.util.ArrayList;
import java.util.List;


public class MarkerViewModel extends ViewModel {

    private List<Marker> markerList = new ArrayList<>();

    private Repository repository;

    public MarkerViewModel() {
        repository = Repository.getsInstance();
    }


    public List<Marker> getMarkerList() {
        // MOCK: Make an api call or retrieve from database
        markerList = repository.getAll();
        return markerList;
    }

    public void update() {

        System.out.println("MarkerViewModel update()");


        for (int i = 0; i < markerList.size(); i++) {
            Marker marker = markerList.get(i);
            marker.getTitle().set("UPDATED TITLE " + i);
        }
    }


    public void add() {
        Marker marker = new Marker();
        marker.getTitle().set("Title4");
        marker.getLatitude().set("11.75790");
        marker.getAddress().set("Shangai");

        markerList.add(marker);

    }
}
