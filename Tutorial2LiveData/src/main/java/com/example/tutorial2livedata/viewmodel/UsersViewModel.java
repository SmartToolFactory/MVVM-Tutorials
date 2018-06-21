package com.example.tutorial2livedata.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.tutorial2livedata.model.User;

import java.util.ArrayList;
import java.util.List;

public class UsersViewModel extends ViewModel {


    private MutableLiveData<List<User>> users;

    public LiveData<List<User>> getUsers() {

        if (users == null) {
            users = new MutableLiveData<List<User>>();
            loadUsers();
        }
        return users;
    }
    /**
     * Dummy Method to fake web service
     *
     * @return list of users
     */
    private List<User> loadUsers() {

        // do something to load users
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Adams");
        userList.add(user);

        user = new User();
        user.setFirstName("Lucy");
        user.setLastName("Adams");
        userList.add(user);

        return userList;

    }


    @NonNull
    public String getUserFirstNames() {
        if (users != null && users.getValue().size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("First Names:\n");
            for (User user : users.getValue()) {
                sb.append(user.getFirstName() + "\n");
            }

            return sb.toString();
        }

        return "empty";
    }

    /**
     * This method changes user names but since mUserList is not observable it does not update UI.
     * When device rotates it gets updated values from UserViewModel with changed values
     */
    public void changeNames() {
        System.out.println("UsersViewModel changeNames() mUserList: " + users.getValue().size());
        if (users.getValue() != null && users.getValue().size() > 0) {
            for (User user : users.getValue()) {
                user.setFirstName("Jack");
            }
        }
    }
}