package com.example.tutorial1mvvmrecyclerview.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.tutorial1mvvmrecyclerview.data.Repository;
import com.example.tutorial1mvvmrecyclerview.model.Marker;

import java.util.ArrayList;
import java.util.List;


public class MarkerViewModel extends ViewModel {

    private MutableLiveData<List<Marker>> listMutableLiveData = new MutableLiveData<>();

    private Repository repository;

    public MarkerViewModel() {
        repository = Repository.getsInstance();
    }


    public LiveData<List<Marker>> getMarkerList() {
        // MOCK: Make an api call or retrieve from database
        listMutableLiveData = repository.getAll();
        return listMutableLiveData;
    }

    public void update() {

        System.out.println("MarkerViewModel update()");

        List<Marker> markerList =

        for (int i = 0; i < marListObservableArrayList.size(); i++) {
            Marker marker = marListObservableArrayList.get(i);
            marker.getTitle().set("UPDATED TITLE " + i);
        }
    }
}
