package com.example.tutorial5livedata_mvvm_room_recyclerview.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.tutorial5livedata_mvvm_room_recyclerview.data.MarkerRepository;
import com.example.tutorial5livedata_mvvm_room_recyclerview.data.local.AppDatabase;
import com.example.tutorial5livedata_mvvm_room_recyclerview.data.local.MarkerLocalDataSource;
import com.example.tutorial5livedata_mvvm_room_recyclerview.model.Marker;
import com.example.tutorial5livedata_mvvm_room_recyclerview.util.AppExecutors;

public class AddMarkerViewModel extends AndroidViewModel {

    private MarkerRepository mMarkerRepository;
    
    public ObservableField<Marker> markerObservableField = new ObservableField<>();


    public AddMarkerViewModel(@NonNull Application application) {
        super(application);

        AppDatabase appDatabase = AppDatabase.getInstance(application.getApplicationContext());

        mMarkerRepository = MarkerRepository
                .getsInstance(MarkerLocalDataSource.getInstance(appDatabase.markerDao(), new AppExecutors()));
        if (markerObservableField.get() == null) {
            Marker marker = new Marker();
            marker.setTitle("New Title");
            markerObservableField.set(marker);
        }
    }


    public long addMarker(Marker marker) {
        return mMarkerRepository.addMarker(marker);
    }
}
