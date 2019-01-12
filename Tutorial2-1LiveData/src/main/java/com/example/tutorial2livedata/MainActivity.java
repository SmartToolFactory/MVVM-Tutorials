package com.example.tutorial2livedata;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.tutorial2livedata.databinding.ActivityMainBinding;
import com.example.tutorial2livedata.model.User;
import com.example.tutorial2livedata.viewmodel.UsersViewModel;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        final UsersViewModel usersViewModel =
                ViewModelProviders.of(this).get(UsersViewModel.class);
        User user = new User();
        user.setFirstName("Johnny");
        user.setLastName("Walker");

        usersViewModel.userMutableLiveData.setValue(user);
        activityMainBinding.tvUsers.setText("user: " + user.getFirstName() + ", last name: " + user.getLastName());

        activityMainBinding.setViewModel(usersViewModel);

        usersViewModel.userMutableLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                activityMainBinding.tvUsers.setText("user: " + user.getFirstName() + ", last name: " + user.getLastName());
            }
        });





    }
}
