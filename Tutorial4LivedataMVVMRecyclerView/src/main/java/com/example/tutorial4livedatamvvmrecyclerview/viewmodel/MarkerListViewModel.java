package com.example.tutorial4livedatamvvmrecyclerview.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.tutorial4livedatamvvmrecyclerview.data.Repository;
import com.example.tutorial4livedatamvvmrecyclerview.model.Marker;


public class MarkerListViewModel extends ViewModel {


    public MutableLiveData<Marker> markerMutableLiveData = new MutableLiveData<>();

    public Repository getRepository() {
        return repository;
    }

    private Repository repository;

    public void init() {
        repository = Repository.getsInstance();

    }

}
