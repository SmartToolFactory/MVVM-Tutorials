package com.example.tutorial4livedatamvvmrecyclerview.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.tutorial4livedatamvvmrecyclerview.data.Repository;
import com.example.tutorial4livedatamvvmrecyclerview.model.Marker;

import java.util.List;


public class MarkerListViewModel extends ViewModel {

    private Repository repository;

    public void init() {
        repository = Repository.getsInstance();

    }

    public List<Marker> getMarkerLis() {
        return repository.getAll();
    }

}
