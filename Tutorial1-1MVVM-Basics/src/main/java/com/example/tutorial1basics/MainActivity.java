package com.example.tutorial1basics;

import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tutorial1basics.databinding.ActivityMainBinding;
import com.example.tutorial1basics.viewmodel.UserViewModel;

/*
    This tutorial includes
    Binding data to views from UserViewModel onCreate() method
    Updating user names with changeNames() method UserViewModel only updates UserViewModel. To update
    views use ObservableField or LiveData objects.

    ViewModel class survives device rotation and updated values with changeNames() are bound to views on create
    after rotation.
 */

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        UserViewModel usersViewModel =
                ViewModelProviders.of(this).get(UserViewModel.class);
        usersViewModel.getUserList();

        activityMainBinding.setViewmodel(usersViewModel);
    }
}
