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
                .getsInstance(MarkerLocalDataSource.getInstance(appDatabase.markerDao(), new AppExecutors()));

    }

    public LiveData<List<Marker>> getListLiveData() {

        return (mListLiveData = mMarkerRepository.getAll());
    }
}
