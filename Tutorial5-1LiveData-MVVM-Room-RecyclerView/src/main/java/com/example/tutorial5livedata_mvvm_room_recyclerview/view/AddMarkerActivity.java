package com.example.tutorial5livedata_mvvm_room_recyclerview.view;

import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

   /*     mAddMarkerViewModel.markerLiveData.observe(this, new Observer<Marker>() {
            @Override
            public void onChanged(@Nullable Marker marker) {
                Toast.makeText(AddMarkerActivity.this, "Marker title: " + marker.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });*/

        mAddMarkerViewModel.markerLiveData.observe(this, marker ->
                Toast.makeText(AddMarkerActivity.this, "Marker title: " + marker.getTitle(), Toast.LENGTH_SHORT).show());

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
                long id = mAddMarkerViewModel.addMarker(mAddMarkerViewModel.markerLiveData.getValue());
                Toast.makeText(this, "id " + id, Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return true;
    }
}
