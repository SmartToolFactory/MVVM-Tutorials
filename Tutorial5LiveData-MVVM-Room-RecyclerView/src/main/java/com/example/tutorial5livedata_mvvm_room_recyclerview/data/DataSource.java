package com.example.tutorial5livedata_mvvm_room_recyclerview.data;


import android.arch.lifecycle.LiveData;

import com.example.tutorial5livedata_mvvm_room_recyclerview.model.Marker;

import java.util.List;

public interface DataSource {

    LiveData<List<Marker>> getAll();

    long addMarker(Marker marker);

    int update(Marker marker);

    int delete(Marker marker);

    int deleteAll();
}
