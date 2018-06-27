package com.example.tutorial1mvvmrecyclerview.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.tutorial1mvvmrecyclerview.data.Repository;
import com.example.tutorial1mvvmrecyclerview.model.Marker;

import java.util.ArrayList;
import java.util.List;


public class MarkerViewModel extends ViewModel {

    private List<Marker> marListObservableArrayList = new ArrayList<>();

    private Repository repository;

    public MarkerViewModel() {
        repository = Repository.getsInstance();
    }


    public List<Marker> getMarkerList() {

        // MOCK: Make an api call or retrieve from database
        marListObservableArrayList = repository.getAll();
        return repository.getAll();
    }

    public void update() {
        System.out.println("MarkerViewModel update()");
        for (int i = 0; i < marListObservableArrayList.size(); i++) {
            Marker marker = marListObservableArrayList.get(i);
            marker.getTitle().set("UPDATED TITLE " + i);
        }
    }
}
