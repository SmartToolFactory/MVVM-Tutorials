package com.example.tutorial3livedata_databinding2objects;

import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.tutorial3livedata_databinding2objects.databinding.ActivityMainBinding;

/*
    Without binding.setLifecycleOwner(this), liveData.setValue() does not work
    liveData.setValue() updates ui
    EditText changes LiveData but does not update UI
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        // LiveData should call setValue(), it does not matter value to change, to update UI via binding
        binding.setViewModel(userViewModel);

        // Required to update UI with LiveData
        binding.setLifecycleOwner(this);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.getViewModel().userMutableLiveData.getValue().getName();
                String email = binding.getViewModel().userMutableLiveData.getValue().getEmail();

                Toast.makeText(MainActivity.this, "name: " + name + ", email: " + email, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
