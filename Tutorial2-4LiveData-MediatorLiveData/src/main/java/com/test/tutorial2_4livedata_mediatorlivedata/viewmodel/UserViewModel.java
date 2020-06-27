package com.test.tutorial2_4livedata_mediatorlivedata.viewmodel;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.test.tutorial2_4livedata_mediatorlivedata.model.User;

public class UserViewModel extends ViewModel {

    /**
     * Changes on this user LiveData triggers function that sets mUserNameLiveData String value
     */
    public MutableLiveData<User> mUserLiveData = new MutableLiveData<>();

    public final LiveData<String> mUserNameLiveData;


    public UserViewModel() {

        /*
         * map() method emits a value in type of destination data(String in this example) when the source LiveData is changed. In this example
         * when a new User value is set to LiveData it trigger this function that returns a String type
         *
         * !Check for conditions that User input can be null
         */
        mUserNameLiveData = Transformations.map(mUserLiveData, new Function<User, String>() {
            @Override
            public String apply(User input) {
                System.out.println("UserViewModel user: " + input);
                return input.getFirstName() + ", " + input.getLastName();
            }
        });
    }

    public void setUser(User user) {
        mUserLiveData.setValue(user);
    }


}