package com.example.tutorial3livedataanddatabinding2.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.tutorial3livedataanddatabinding2.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MyViewModel extends ViewModel {


    private MutableLiveData<List<User>> usersLiveData;

    public LiveData<String> userStringLiveData;

    /**
     * Call after usersLiveData is not null and it contains list of users
     */
    private void prepareListData() {


        userStringLiveData = Transformations.map(usersLiveData, new Function<List<User>, String>() {
            @Override
            public String apply(List<User> userList) {
                return getUserRecords(userList);
            }
        });


        userStringLiveData = Transformations.map(usersLiveData, userList -> {
            return getUserRecords(userList);
        });

    }

    public MutableLiveData<List<User>> getUsersLiveData() {
        return usersLiveData;
    }


    public LiveData<List<User>> setUpLiveData() {
        if (usersLiveData == null) {
            usersLiveData = new MutableLiveData<>();
            loadUsers();
            prepareListData();
        }
        return usersLiveData;
    }

    private void loadUsers() {
        // do async operation to fetch users
        List<User> userList = new ArrayList<>();

        User user = new User();
        user.setFirstName("Jack");
        user.setLastName("Daniels");
        userList.add(user);

        usersLiveData.setValue(userList);

    }

    public void shuffleUsers() {
        if (usersLiveData != null && usersLiveData.getValue() != null && usersLiveData.getValue().size() > 0) {
            Collections.shuffle(usersLiveData.getValue());
            // Needed to update Live Data observers
            usersLiveData.setValue(usersLiveData.getValue());
        }
    }

    public void addUser() {
        if (usersLiveData != null && usersLiveData.getValue() != null) {

            User user = new User();
            user.setFirstName("Winston");
            user.setLastName("Whiskey");

            usersLiveData.getValue().add(user);
            // Needed to update Live Data observers
            usersLiveData.setValue(usersLiveData.getValue());

        }
    }

    /**
     * Binds String to layout
     * @param userList list of users
     * @return String to be bound to layout via data binding
     */
    public String getUserRecords(@NonNull List<User> userList) {

        if (userList.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (User user : userList) {
                sb.append(user.getFirstName() + " " + user.getLastName() + "\n");
            }

            return sb.toString();
        }

        return "empty list";
    }
}