package com.test.foursquaresingle;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.test.foursquaresingle.api.ApiResponse;
import com.test.foursquaresingle.api.FoursquareService;
import com.test.foursquaresingle.api.VenueListResponse;
import com.test.foursquaresingle.databinding.ActivityVenueSearchBinding;
import com.test.foursquaresingle.model.Venue;
import com.test.foursquaresingle.utils.SnackbarUtils;
import com.test.foursquaresingle.view.venuedetail.VenueDetailDialogFragment;
import com.test.foursquaresingle.view.venuelist.VenueListFragment;
import com.test.foursquaresingle.view.venuesearch.VenueSearchFragment;
import com.test.foursquaresingle.viewmodel.VenueDetailViewModel;
import com.test.foursquaresingle.viewmodel.VenueSearchViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * This activity is host to {@link VenueSearchFragment} to search Venues
 * and {@link VenueListFragment} to list results inside a RecyclerView
 */
public class MainActivity extends DaggerAppCompatActivity {

    private ActivityVenueSearchBinding mDataBinding;

    private ActionBarDrawerToggle mDrawerToggle;

    private boolean mToolBarNavigationListenerIsRegistered = false;


    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private VenueSearchViewModel mVenueListViewModel;

    private VenueDetailViewModel mVenueDetailViewModel;


    @Inject
    FoursquareService foursquareService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_venue_search);

        // Create ViewModel to share data between fragments and this activity
        // Search fragment shares venue list with list fragment and venue type with activity to change toolbar title
        mVenueListViewModel = ViewModelProviders.of(this, viewModelFactory).get(VenueSearchViewModel.class);

        mVenueDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(VenueDetailViewModel.class);


        // Set view properties
        initViews();

        // Observe venue search result to change current fragment
        observeVenueSearchResult();

        // Observe venue detail clicks to show details
        observeVenueDetails();

        // Add search fragment only the first time app is created
        // After device rotation savedInstanceState is not null
        // fragment manager and viewModel survives through Activity life cycle
        if (savedInstanceState == null) {
            navigateToVenueSearch();
        }


//       LiveData<ApiResponse<VenueListResponse>> responseLiveData = foursquareService.getVenuesLive(FoursquareService.FOURSQUARE_VERSION,
//                FoursquareService.FOURSQUARE_CLIENT_ID,
//                FoursquareService.FOURSQUARE_SECRET_ID
//        ,"bar", "istanbul");
//
//
//        responseLiveData.observe(this, new Observer<ApiResponse<VenueListResponse>>() {
//            @Override
//            public void onChanged(@Nullable ApiResponse<VenueListResponse> venueListResponseApiResponse) {
//                System.out.println("MainActivity API RESPONSE: " + venueListResponseApiResponse.code);
//            }
//        });

        mVenueListViewModel.apiResponseMediatorLiveData.observe(this, new Observer<ApiResponse<VenueListResponse>>() {
            @Override
            public void onChanged(@Nullable ApiResponse<VenueListResponse> venueListResponseApiResponse) {
                System.out.println("MainActivity API RESPONSE: " + venueListResponseApiResponse.code);
            }
        });
    }

    private void initViews() {

        // Set Toolbar
        Toolbar toolbar = mDataBinding.toolbar;
        setSupportActionBar(toolbar);

        DrawerLayout mDrawerLayout = mDataBinding.drawerLayout;

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.syncState();

    }


    private void navigateToVenueSearch() {

        VenueSearchFragment fragment = VenueSearchFragment.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_venue_search, fragment)
                .commit();
    }


    private void navigateToVenueList() {

        VenueListFragment venueListFragment = VenueListFragment.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_venue_search, venueListFragment)
                .addToBackStack(null)
                .commit();

    }

    /**
     * To be semantically or contextually correct, maybe change the name
     * and signature of this function to something like:
     * <p>
     * private void showBackButton(boolean show)
     * Just a suggestion.
     */
    public void enableToolbarBackArrow(boolean enable) {

        DrawerLayout drawerLayout = mDataBinding.drawerLayout;

        // To keep states of ActionBar and ActionBarDrawerToggle synchronized,
        // when you enable on one, you disable on the other.
        // And as you may notice, the order for this operation is disable first, then enable - VERY VERY IMPORTANT.
        if (enable) {
            //You may not want to open the drawer on swipe from the left in this case
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            // Remove hamburger
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            // Show back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // when DrawerToggle is disabled i.e. setDrawerIndicatorEnabled(false), navigation icon
            // clicks are disabled i.e. the UP button will not work.
            // We need to add a listener, as in below, so DrawerToggle will forward
            // click events to this listener.
            if (!mToolBarNavigationListenerIsRegistered) {
                mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Doesn't have to be onBackPressed
                        onBackPressed();
                    }
                });

                mToolBarNavigationListenerIsRegistered = true;
            }

            if (mVenueListViewModel.queryLiveData.getValue() != null
                    && mVenueListViewModel.queryLiveData.getValue().venueType != null) {
                String toolbarTitle = mVenueListViewModel.queryLiveData.getValue().venueType;
                setTitle(toolbarTitle);
            }

        } else {
            //You must regain the power of swipe for the drawer.
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

            // Remove back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            // Show hamburger
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            // Remove the/any drawer toggle listener
            mDrawerToggle.setToolbarNavigationClickListener(null);
            mToolBarNavigationListenerIsRegistered = false;

            setTitle(R.string.toolbar_title);
        }

        // So, one may think "Hmm why not simplify to:
        // .....
        // getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
        // mDrawer.setDrawerIndicatorEnabled(!enable);
        // ......
        // To re-iterate, the order in which you enable and disable views IS important #dontSimplify.
    }


    /**
     * Activity only observes reult of query to change fragment to list data if data is retrieved successfully
     */
    private void observeVenueSearchResult() {
        // List Resource is a wrapper class that contains web request status and response data of venues if successful
        mVenueListViewModel.getVenueListResource().observe(this, listResource -> {

            if (listResource == null) return;

            switch (listResource.status) {

                case SUCCESS:
//                    System.out.println("MainActivity observeVenueSearchResult mVenueListViewModel.getVenueListResource(): "
//                    + mVenueListViewModel.getVenueListResource());

//                    System.out.println("Activity SUCCESS mVenueListViewModel.isEventConsumed: "
//                            + mVenueListViewModel.isEventConsumed);

                    if (!mVenueListViewModel.isEventConsumed) {
                        navigateToVenueList();
                    }

                    break;
            }
        });
    }

    // Venue Details

    private void observeVenueDetails() {
        mVenueDetailViewModel.getVenueDetail().observe(this, venueResource -> {

            // TODO FIX: Use SingleLiveEvent instead of ViewModel boolean

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

        // Create new DialogFragment if it's not used yet
        VenueDetailDialogFragment.newInstance(venue).show(getSupportFragmentManager(), null);

    }

    // Required to call onActivityResult inside search fragment to get location settings result

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
