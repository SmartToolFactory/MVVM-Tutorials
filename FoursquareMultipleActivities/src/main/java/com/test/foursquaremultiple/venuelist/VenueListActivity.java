package com.test.foursquaremultiple.venuelist;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.test.foursquaremultiple.R;
import com.test.foursquaremultiple.databinding.ActivityVenueListBinding;
import com.test.foursquaremultiple.model.Venue;
import com.test.foursquaremultiple.utils.SnackbarUtils;
import com.test.foursquaremultiple.venuedetail.VenueDetailDialogFragment;
import com.test.foursquaremultiple.venuesearch.VenueSearchActivity;
import com.test.foursquaremultiple.viewmodel.VenueDetailViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class VenueListActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private VenueDetailViewModel mVenueDetailViewModel;

    private ActivityVenueListBinding mDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_venue_list);

        mVenueDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(VenueDetailViewModel.class);

        initViews();


        observeVenueDetails();
    }

    private void initViews() {

        List<Venue> venueList = getIntent().getParcelableArrayListExtra(VenueSearchActivity.VENUE_SEARCH_ARGS);


        Toolbar toolbar = mDataBinding.toolbar;

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        VenueListFragment venueListFragment = VenueListFragment.newInstance(venueList);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_venue_list, venueListFragment)
                .commit();
    }


    // Venue Details
    private void observeVenueDetails() {
        mVenueDetailViewModel.getVenueDetail().observe(this, venueResource -> {

            // TODO FIX: User SingleLiveEvent instead of ViewModel boolean

            if (venueResource == null || mVenueDetailViewModel.isEventConsumed) return;


            switch (venueResource.status) {

                case ERROR:
                    showSnackbar(venueResource.message);
                    mVenueDetailViewModel.isEventConsumed = true;
                    break;

                case SUCCESS:
                    showVenueDetails(venueResource.data);
                    mVenueDetailViewModel.isEventConsumed = true;
                    break;
            }
        });
    }

    /**
     * Show a Snackbar to user
     *
     * @param message to be displayed to user
     */
    private void showSnackbar(String message) {
        View parentLayout = findViewById(android.R.id.content);
        SnackbarUtils.showSnackbar(parentLayout, message);
    }

    /**
     * Show venue details to user with a dialog
     *
     * @param venue current venue selected by user
     */
    private void showVenueDetails(Venue venue) {

        VenueDetailDialogFragment.newInstance(venue).show(getSupportFragmentManager(), null);

    }

}
