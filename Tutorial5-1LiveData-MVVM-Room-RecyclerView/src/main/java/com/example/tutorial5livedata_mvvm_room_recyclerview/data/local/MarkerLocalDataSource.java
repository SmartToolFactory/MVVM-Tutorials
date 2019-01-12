package com.example.tutorial5livedata_mvvm_room_recyclerview.data.local;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.tutorial5livedata_mvvm_room_recyclerview.data.DataSource;
import com.example.tutorial5livedata_mvvm_room_recyclerview.model.Marker;
import com.example.tutorial5livedata_mvvm_room_recyclerview.util.AppExecutors;

import java.util.List;

// TODO Add AppExecutor and Executors

public class MarkerLocalDataSource implements DataSource {

    private MarkerDao mMarkerDao;

    private AppExecutors mAppExecutors;

    private static volatile MarkerLocalDataSource INSTANCE;

    private MarkerLocalDataSource(@NonNull MarkerDao markerDao, @NonNull AppExecutors appExecutors) {
        mMarkerDao = markerDao;
        mAppExecutors = appExecutors;
    }

    public static MarkerLocalDataSource getInstance(
            @NonNull MarkerDao markerDao, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (MarkerLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MarkerLocalDataSource(markerDao, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<List<Marker>> getAll() {

        return mMarkerDao.getAll();
    }

    @Override
    public long addMarker(Marker marker) {

        final long[] id = new long[1];

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                id[0] = mMarkerDao.insert(marker);
            }
        };
        mAppExecutors.diskIO().execute(runnable);

        return id[0];
    }

    @Override
    public int update(Marker marker) {
        return mMarkerDao.update(marker);
    }

    @Override
    public int delete(Marker marker) {
        return mMarkerDao.delete(marker);
    }

    @Override
    public int deleteAll() {
        return mMarkerDao.deleteAll();
    }
}
