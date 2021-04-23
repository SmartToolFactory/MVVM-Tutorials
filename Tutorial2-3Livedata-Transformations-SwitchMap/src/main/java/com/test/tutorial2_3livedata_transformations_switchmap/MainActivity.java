package com.test.tutorial2_3livedata_transformations_switchmap;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.test.tutorial2_3livedata_transformations_switchmap.model.User;
import com.test.tutorial2_3livedata_transformations_switchmap.viewmodel.UserViewModel;

/*
* 🔥 Alternatively, you can use LiveData.switchMap() from livedata-ktx extensions which is equivalent of Transformations.switchMap().
* */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        EditText editText = findViewById(R.id.editText);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            String text = editText.getText().toString();
            if (editText != null && text != null) {
                userViewModel.setUserName(text);
            }
        });


        observe(userViewModel);

    }

    private void observe(UserViewModel userViewModel) {

        // Observe LiveData<User>
    /*    userViewModel.mUserLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                TextView textView = findViewById(R.id.textView);

                textView.setText("Name: " + user.getFirstName() + ", Last Name: " + user.getLastName());
            }
        });*/


        // Observe MediatorLiveData<User>

        userViewModel.userMediatorLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                TextView textView = findViewById(R.id.textView);

                textView.setText("Name: " + user.getFirstName() + ", Last Name: " + user.getLastName());
            }

        });


    }
}
