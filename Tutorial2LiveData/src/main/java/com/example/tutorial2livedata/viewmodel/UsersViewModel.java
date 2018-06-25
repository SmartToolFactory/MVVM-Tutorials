package com.example.tutorial2livedata.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.tutorial2livedata.model.User;

public class UsersViewModel extends ViewModel {


    public MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();


    public void changeUserName() {
        User user = new User();
        user.setFirstName("Jack");
        user.setLastName("Daniels");
        userMutableLiveData.setValue(user);
    }

}