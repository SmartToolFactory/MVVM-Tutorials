package com.test.foursquaremultiple.venuesearch;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.test.foursquaremultiple.R;
import com.test.foursquaremultiple.databinding.ActivityVenueSearchBinding;
import com.test.foursquaremultiple.model.Venue;
import com.test.foursquaremultiple.venuelist.VenueListActivity;
import com.test.foursquaremultiple.venuelist.VenueListFragment;
import com.test.foursquaremultiple.viewmodel.VenueSearchViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * This activity is host to {@link VenueSearchFragment} to search Venues
 * and {@link VenueListFragment} to list results inside a RecyclerView
 */
public class VenueSearchActivity extends DaggerAppCompatActivity {

    public static final String VENUE_SEARCH_ARGS = "venueList";


    private ActivityVenueSearchBinding mDataBinding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private VenueSearchViewModel mVenueListViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_venue_search);

        // Create ViewModel to share data between fragments and this activity
        // Search fragment shares venue list with list fragment and venue type with activity to change toolbar title
        mVenueListViewModel = ViewModelProviders.of(this, viewModelFactory).get(VenueSearchViewModel.class);

        // Set view properties
        initViews();

        // Observe venue search result to change current fragment
        observeVenueSearchResult();

        navigateToVenueSearch();

    }


    private void initViews() {
        // Set Toolbar
        Toolbar toolbar = mDataBinding.toolbar;
        setSupportActionBar(toolbar);
        setTitle(R.string.toolbar_title);
    }

    /**
     * Create search fragment
     */
    private void navigateToVenueSearch() {

        VenueSearchFragment fragment = VenueSearchFragment.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_venue_search, fragment)
                .commit();
    }

    /**
     * Start Activity to list venues and pass venue list to {@link VenueListActivity}
     */
    private void navigateToVenueList() {

        Intent intentVenueList = new Intent(VenueSearchActivity.this, VenueListActivity.class);

        List<Venue> venueList = mVenueListViewModel.getVenueListResource().getValue().data;

        intentVenueList.putParcelableArrayListExtra(VENUE_SEARCH_ARGS, (ArrayList<? extends Parcelable>) venueList);

        startActivity(intentVenueList);
    }


    /**
     * Activity only observes result of query to change fragment to list data if data is retrieved successfully
     */
    private void observeVenueSearchResult() {

        // List Resource is a wrapper class that contains web request status and response data of venues if successful
        mVenueListViewModel.getVenueListResource().observe(this, listResource -> {

            if (listResource == null) return;

            switch (listResource.status) {

                case SUCCESS:
                    if (!mVenueListViewModel.isEventConsumed) {
                        navigateToVenueList();
                    }
                    break;
            }
        });
    }


    // Required to call onActivityResult inside search fragment to get location settings result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
