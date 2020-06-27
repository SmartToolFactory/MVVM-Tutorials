package com.example.tutorial3livedata_databinding2objects;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {

    public MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
    private User mUser;

    public UserViewModel() {
        mUser = new User("User", "asd@abc.com");
        userMutableLiveData.setValue(mUser);

    }

    public void changeUserName() {

        // Both updates LiveData but does not update UI
        mUser.setName("Name is Changed");
        // userMutableLiveData.getValue().setName("Updated Name");

        // Updates UI
        //  userMutableLiveData.setValue(userMutableLiveData.getValue());
    }

    public void changeUser() {
        mUser = new User("New User Name", "myemail@mail.com");
        // Without setting new value UI is not updated and observe is not called
        userMutableLiveData.setValue(mUser);
    }
}
