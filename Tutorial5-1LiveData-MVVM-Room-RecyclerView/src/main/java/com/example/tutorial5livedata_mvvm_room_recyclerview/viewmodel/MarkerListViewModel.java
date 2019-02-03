package com.example.tutorial5livedata_mvvm_room_recyclerview.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.tutorial5livedata_mvvm_room_recyclerview.data.MarkerRepository;
import com.example.tutorial5livedata_mvvm_room_recyclerview.data.local.AppDatabase;
import com.example.tutorial5livedata_mvvm_room_recyclerview.data.local.MarkerLocalDataSource;
import com.example.tutorial5livedata_mvvm_room_recyclerview.model.Marker;
import com.example.tutorial5livedata_mvvm_room_recyclerview.util.AppExecutors;

import java.util.List;

public class MarkerListViewModel extends AndroidViewModel {
    private MarkerRepository mMarkerRepository;

    private LiveData<List<Marker>> mListLiveData;

    public MarkerListViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application.getApplicationContext());

        mMarkerRepository = MarkerRepository
                .getInstance(MarkerLocalDataSource.getInstance(appDatabase.markerDao(), new AppExecutors()));

        /*
         *After getting data once from Dao with LiveData class any changes on this Dao triggers an update on LiveData too.
         */
        mListLiveData = mMarkerRepository.getAll();
    }

    public LiveData<List<Marker>> getListLiveData() {

        return ( mMarkerRepository.getAll());
    }

    /**
     * Add new marker to database using Repository
     *
     * @param marker to be added to db
     * @return id of current row
     */
    public long insertMarker(Marker marker) {
        return mMarkerRepository.addMarker(marker);
    }
}
