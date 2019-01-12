package com.test.foursquaremultiple.location;

import android.arch.lifecycle.LifecycleObserver;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;

public interface ILocationProvider extends LifecycleObserver {

    void setInterval(long milis);

    Location getLocation();


    /**
     * Method for checking device location settings result
     *
     * @param requestCode for changing device location settings
     * @param resultCode  result of location settings change request
     * @param data        contains details
     */
    void onActivityResult(int requestCode, int resultCode, Intent data);

    /**
     * Method for checking ACCESS_FINE_LOCATION permission request result
     *
     * @param requestCode  is for checking location permission
     * @param permissions  permissions should contain ACCESS_FINE_LOCATION for location to be enabled
     * @param grantResults permission results
     */
    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);

}
