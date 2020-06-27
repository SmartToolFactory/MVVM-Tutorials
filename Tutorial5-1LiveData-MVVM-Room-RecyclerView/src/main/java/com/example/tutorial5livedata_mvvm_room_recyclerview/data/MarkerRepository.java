package com.example.tutorial5livedata_mvvm_room_recyclerview.data;


import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.example.tutorial5livedata_mvvm_room_recyclerview.data.local.MarkerLocalDataSource;
import com.example.tutorial5livedata_mvvm_room_recyclerview.model.Marker;

import java.util.List;

// TODO Do User Data Caching inside this class

public class MarkerRepository implements DataSource {

    private static MarkerRepository sInstance;

    private final MarkerLocalDataSource markerLocalDataSource;

    private MarkerRepository(MarkerLocalDataSource markerLocalDataSource) {
        this.markerLocalDataSource = markerLocalDataSource;
    }

    public static MarkerRepository getInstance(@NonNull MarkerLocalDataSource markerLocalDataSource) {

        if (sInstance == null) {
            sInstance = new MarkerRepository(markerLocalDataSource);
        }

        return sInstance;
    }


    @Override
    public LiveData<List<Marker>> getAll() {
        return markerLocalDataSource.getAll();
    }

    @Override
    public long addMarker(Marker marker) {
        return markerLocalDataSource.addMarker(marker);
    }

    @Override
    public int update(Marker marker) {
        return markerLocalDataSource.update(marker);
    }

    @Override
    public int delete(Marker marker) {
        return markerLocalDataSource.delete(marker);
    }

    @Override
    public int deleteAll() {
        return markerLocalDataSource.deleteAll();
    }
}
