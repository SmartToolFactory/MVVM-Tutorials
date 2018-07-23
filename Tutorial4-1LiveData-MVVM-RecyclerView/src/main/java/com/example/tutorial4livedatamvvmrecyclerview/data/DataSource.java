package com.example.tutorial4livedatamvvmrecyclerview.data;

import android.arch.lifecycle.LiveData;

import com.example.tutorial4livedatamvvmrecyclerview.model.Marker;

import java.util.List;

public interface DataSource {
     LiveData<List<Marker>> getAll();
     long addMarker(Marker marker);
     int update(Marker marker);
}
