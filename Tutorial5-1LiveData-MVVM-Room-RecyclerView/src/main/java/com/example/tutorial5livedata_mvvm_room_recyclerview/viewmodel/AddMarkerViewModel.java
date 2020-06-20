package com.example.tutorial5livedata_mvvm_room_recyclerview.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import com.example.tutorial5livedata_mvvm_room_recyclerview.data.MarkerRepository;
import com.example.tutorial5livedata_mvvm_room_recyclerview.data.local.AppDatabase;
import com.example.tutorial5livedata_mvvm_room_recyclerview.data.local.MarkerLocalDataSource;
import com.example.tutorial5livedata_mvvm_room_recyclerview.model.Marker;
import com.example.tutorial5livedata_mvvm_room_recyclerview.util.AppExecutors;

public class AddMarkerViewModel extends AndroidViewModel {

    private MarkerRepository mMarkerRepository;
    public MutableLiveData<Marker> markerLiveData = new MutableLiveData<>();


    public AddMarkerViewModel(@NonNull Application application) {
        super(application);

        AppDatabase appDatabase = AppDatabase.getInstance(application.getApplicationContext());
        // Get marker repo
        mMarkerRepository = MarkerRepository
                .getInstance(MarkerLocalDataSource.getInstance(appDatabase.markerDao(), new AppExecutors()));

        // Set initial LiveData value to prevent  NPE
        if (markerLiveData.getValue() == null) {
            Marker marker = new Marker();
            marker.setTitle("No Title");
            marker.setLatitude("0.0");
            marker.setTitle("No Location");
            markerLiveData.setValue(marker);
        }
    }


    public long addMarker(Marker marker) {
        return mMarkerRepository.addMarker(marker);
    }
}
