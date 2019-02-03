package com.test.tutorial2_2livedata_transformations_map;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.test.tutorial2_2livedata_transformations_map.model.User;
import com.test.tutorial2_2livedata_transformations_map.viewmodel.UserViewModel;

/**
 * This example demonstrates usage of {@link android.arch.lifecycle.Transformations#map(LiveData, Function)} method
 * map() method changes value of target(String in this example) LiveData
 * when value of source LiveData(user LiveData in this example) has changed
 */
public class MainActivity extends AppCompatActivity {

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setFirstName("Unknown #" + counter);
                user.setLastName("User");

                // Sets user of ViewModel's mUserLiveData
                userViewModel.setUser(user);

                counter++;
            }
        });

        observe(userViewModel);
    }

    private void observe(UserViewModel userViewModel) {

        System.out.println("MainActivity observe() userViewModel.mUserNameLiveData: " + userViewModel.mUserNameLiveData);


        // Observe LiveData<String>
        userViewModel.mUserNameLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                TextView textView = findViewById(R.id.textView2);

                textView.setText("User: " + s);

                Toast.makeText(MainActivity.this, "User: " + s, Toast.LENGTH_SHORT).show();

            }
        });


        // Observe MediatorLiveData<String>
 /*       userViewModel.mediatorLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                TextView textView = findViewById(R.id.textView2);

                textView.setText("User: " + s);

                Toast.makeText(MainActivity.this, "User: " + s, Toast.LENGTH_SHORT).show();
            }
        });*/


    }
}
