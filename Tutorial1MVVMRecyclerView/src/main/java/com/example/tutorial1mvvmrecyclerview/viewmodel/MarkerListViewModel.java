package com.example.tutorial1mvvmrecyclerview.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.example.tutorial1mvvmrecyclerview.data.Repository;
import com.example.tutorial1mvvmrecyclerview.model.Marker;


public class MarkerListViewModel extends ViewModel {


    public ObservableField<Marker> markerObservableField = new ObservableField<>();

    public Repository getRepository() {
        return repository;
    }

    private Repository repository;

    public MarkerListViewModel() {
        repository = Repository.getsInstance();
    }


}
