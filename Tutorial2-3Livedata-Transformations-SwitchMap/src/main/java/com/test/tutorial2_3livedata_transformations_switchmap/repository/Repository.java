package com.test.tutorial2_3livedata_transformations_switchmap.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.test.tutorial2_3livedata_transformations_switchmap.model.User;

public class Repository {


    public LiveData<User> getUserLiveData(String name) {


        User user = new User();
        user.setFirstName(name);
        user.setLastName("Unknown");

        final MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
        userMutableLiveData.setValue(user);

        return userMutableLiveData;
    }
}
