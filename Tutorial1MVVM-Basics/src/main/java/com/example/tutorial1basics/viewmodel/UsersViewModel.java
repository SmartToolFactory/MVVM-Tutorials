package com.example.tutorial1basics.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.tutorial1basics.model.User;

import java.util.ArrayList;
import java.util.List;

public class UsersViewModel extends ViewModel {


    private List<User> mUserList;

    public List<User> getUserList() {
        if (mUserList == null) {
            mUserList = loadUsers();
        }
        return mUserList;
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
        if (mUserList != null && mUserList.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("First Names:\n");
            for (User user : mUserList) {
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
        System.out.println("UsersViewModel changeNames() mUserList: " + mUserList.size());
        if (mUserList != null && mUserList.size() > 0) {
            for (User user : mUserList) {
                user.setFirstName("Jack");
            }
        }
    }
}