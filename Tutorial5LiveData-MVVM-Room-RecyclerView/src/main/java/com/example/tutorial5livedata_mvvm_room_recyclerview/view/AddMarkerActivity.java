package com.example.tutorial5livedata_mvvm_room_recyclerview.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tutorial5livedata_mvvm_room_recyclerview.R;
import com.example.tutorial5livedata_mvvm_room_recyclerview.databinding.ActivityAddMarkerBinding;
import com.example.tutorial5livedata_mvvm_room_recyclerview.viewmodel.AddMarkerViewModel;

public class AddMarkerActivity extends AppCompatActivity {

    private AddMarkerViewModel mAddMarkerViewModel;
    private ActivityAddMarkerBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_marker);
        mAddMarkerViewModel = ViewModelProviders.of(this).get(AddMarkerViewModel.class);
        mBinding.setViewModel(mAddMarkerViewModel);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_add_location:
                long id = mAddMarkerViewModel.addMarker(mAddMarkerViewModel.markerObservableField.get());
                Toast.makeText(this, "id " + id, Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return true;
    }
}
