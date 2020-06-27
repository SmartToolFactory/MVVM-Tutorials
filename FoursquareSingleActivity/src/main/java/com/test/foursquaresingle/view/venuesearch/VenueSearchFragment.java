package com.test.foursquaresingle.view.venuesearch;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import androidx.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.test.foursquaresingle.MainActivity;
import com.test.foursquaresingle.R;
import com.test.foursquaresingle.databinding.FragmentVenueSearchBinding;
import com.test.foursquaresingle.utils.NetworkUtils;
import com.test.foursquaresingle.utils.QueryValidator;
import com.test.foursquaresingle.utils.SnackbarUtils;
import com.test.foursquaresingle.view.callback.IQuery;
import com.test.foursquaresingle.viewmodel.VenueSearchViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class VenueSearchFragment extends DaggerFragment implements IQuery {

    /**
     *
     */
    private String mVenueLocation;
    private String mVenueType;

    private static final int REQUEST_LOCATION = 100;
    private static final int REQUEST_CHECK_SETTINGS = 101;


    @Inject
    ViewModelProvider.Factory viewModelFactory;


    private FragmentVenueSearchBinding fragmentBinding;

    private VenueSearchViewModel mVenueListViewModel;

    private MaterialDialog mProgressbar;

    // Location
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationCallback mLocationCallback;


    public static VenueSearchFragment newInstance() {
        return new VenueSearchFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_venue_search, container, false);

        return fragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // getActivity() returns same ViewModel with Activity
        mVenueListViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(VenueSearchViewModel.class);

        fragmentBinding.setViewModel(mVenueListViewModel);
        fragmentBinding.setIQuery(this);

        ((MainActivity) getActivity()).enableToolbarBackArrow(false);

        observeVenueSearch();

    }

    /**
     * This method observes query progress and shows loading progress bar while data retrieval progress is going on.
     * and hides progress bar when query has finished.
     * It's Activities responsibility to change fragments
     */
    private void observeVenueSearch() {
        // List Resource is a wrapper class that contains web request status and response data of venues if successful
        mVenueListViewModel.getVenueListResource().observe(this, listResource -> {

            if (listResource == null) return;

            switch (listResource.status) {

                case LOADING:
                    showProgressBar(getString(R.string.searching));
                    break;

                case ERROR:
                    if (!mVenueListViewModel.isEventConsumed) {
                        // We got a result hide progress bar
                        hideProgressBar();
                        // show error to user
                        showApiFailError();
                        mVenueListViewModel.isEventConsumed = true;
                    }

                    break;

                case SUCCESS:

                    if (!mVenueListViewModel.isEventConsumed) {
                        // We got a result hide progress bar
                        hideProgressBar();
                        mVenueListViewModel.isEventConsumed = true;
                    }

                    break;
            }
        });
    }


    private void showProgressBar(String message) {

        if (mProgressbar == null) {
            mProgressbar = new MaterialDialog.Builder(getActivity())
                    .progress(true, 0).content(message).cancelable(false)
                    .build();
        }

        mProgressbar.show();

    }

    private void hideProgressBar() {

        if (mProgressbar != null) {
            mProgressbar.hide();
        }
    }

    private void showApiFailError() {
        new MaterialDialog.Builder(getActivity())
                .title(R.string.popup_search_fail_title)
                .content(R.string.popup_search_fail_content)
                .positiveText(R.string.ok).show();
    }

    private void showWarnUserInput() {
        new MaterialDialog.Builder(getActivity())
                .title(R.string.popup_invalid_input_title)
                .content(R.string.popup_invalid_input_content)
                .positiveText(R.string.ok).show();
    }


    /**
     * Runs string validation and a query to get venues list if query String is validated
     */
    private void query() {
        mVenueType = fragmentBinding.venueType.getEditableText().toString();
        mVenueLocation = fragmentBinding.venueLocation.getEditableText().toString();

        // Check if this is a valid queryVenues
        if (!QueryValidator.isValidQuery(mVenueType)) {
            // Display error message
            showWarnUserInput();
        } else {

            // Run a query via ViewModel
            if (!mVenueLocation.isEmpty()) {
                // Venue location is not empty so use it
                mVenueListViewModel.queryVenues(mVenueType, mVenueLocation);
                mVenueListViewModel.isEventConsumed = false;

            } else {
                // Venue location is empty use current location

                // User hasn't granted location permission yet
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermission(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                } else {
                    // Permission for location is granted, request location and query depending on result
                    // User's location settings might not be on
                    queryVenueOnCurrentLocation();
                }

            }
        }

    }

    private void queryVenueOnCurrentLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        getLocation();
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        createLocationCallback();
        startLocationUpdates();
    }

    /**
     * Location callback is used for getting location
     *
     * @return LocationCallback to create a location request
     */
    private void createLocationCallback() {

        mLocationCallback = new LocationCallback() {

            @Override
            public void onLocationResult(LocationResult locationResult) {


                if (locationResult != null) {

                    Location location = locationResult.getLastLocation();
                    mVenueLocation = location.getLatitude() + "," + location.getLongitude();
                    mVenueListViewModel.queryVenuesByLocation(mVenueType, mVenueLocation);

                    // Stop location updates so app does not query over and over again
                    stopLocationUpdates();

                }
            }
        };

    }

    private void stopLocationUpdates() {
        // It is a good practice to remove location requests when the activity is in a paused or
        // stopped state. Doing so helps battery performance and is especially
        // recommended in applications that request frequent location updates.
        mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback)
                .addOnCompleteListener(getActivity(), task -> {
                });
    }


    private void startLocationUpdates() {

        // Set location request to set location request interval
        LocationRequest locationRequest = getLocationRequest();

        // Location setting request needed to open location settings if location providers are not active
        LocationSettingsRequest locationSettingsRequest = getLocationSettingsRequest(locationRequest);


        SettingsClient settingsClient = LocationServices.getSettingsClient(getActivity());

        // Begin by checking if the device has the necessary location settings.
        settingsClient.checkLocationSettings(locationSettingsRequest)
                .addOnSuccessListener(getActivity(), new OnSuccessListener<LocationSettingsResponse>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

                        mFusedLocationProviderClient.requestLocationUpdates(locationRequest,
                                mLocationCallback, Looper.myLooper());

                    }
                })
                .addOnFailureListener(getActivity(), e -> {

                    int statusCode = ((ApiException) e).getStatusCode();

                    switch (statusCode) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                // Show the dialog by calling startResolutionForResult(), and check the
                                // result in onActivityResult().
                                ResolvableApiException rae = (ResolvableApiException) e;

                                // Used with Activity, does not call fragments onActivityResult method
                                //   rae.startResolutionForResult(getActivity(), REQUEST_CHECK_SETTINGS);

                                // TODO Warning: calls fragments onActivityResult method which required to run query if user activated location settings
                                startIntentSenderForResult(rae.getResolution().getIntentSender(),
                                        REQUEST_CHECK_SETTINGS, null, 0, 0, 0, null);

                            } catch (IntentSender.SendIntentException sie) {
                                sie.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            String errorMessage = "Location settings are inadequate, and cannot be " +
                                    "fixed here. Fix in Settings.";

                            SnackbarUtils.showSnackbar(getView(), errorMessage);
                    }

                });
    }

    @NonNull
    private LocationSettingsRequest getLocationSettingsRequest(LocationRequest locationRequest) {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);
        return builder.build();
    }

    @NonNull
    private LocationRequest getLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(2000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    @Override
    public void onQuery() {

        // Check if user is online
        if (NetworkUtils.isOnline(getActivity().getApplicationContext())) {
            query();
        } else {
            SnackbarUtils.showSnackbar(getView(), "Internet Connection is not available!");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // TODO Warning: query after location permission is granted
        queryVenueOnCurrentLocation();
    }


    private void requestPermission(final String[] permissions, final int requestCode) {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                        Manifest.permission.CAMERA);

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {
            requestPermissions(permissions, requestCode);
        } else {
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            requestPermissions(permissions, requestCode);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                if (resultCode == Activity.RESULT_OK) queryVenueOnCurrentLocation();
                break;
        }
    }
}

