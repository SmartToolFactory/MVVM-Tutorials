package com.test.foursquaremultiple.repo;


import android.arch.lifecycle.LiveData;

import com.test.foursquaremultiple.model.Venue;
import com.test.foursquaremultiple.utils.AppExecutors;
import com.test.foursquaremultiple.repo.remote.WebService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * This class is repository for retrieving data from web service or room data base if required
 */

@Singleton
public class VenueRepository {

    private final WebService webService;
    /**
     * App executor provides three threads to execute operation on main or worker threads
     */
    private final AppExecutors appExecutors;

    /**
     * Venue Repository retrieves venue data from web or offline source if specified
     *
     * @param appExecutors contains web, database and main threads for different type of operations
     * @param webService   request data via Retrofit2 from web
     */

    @Inject
    public VenueRepository(AppExecutors appExecutors, WebService webService) {
        this.webService = webService;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<List<Venue>>> getVenues(String venueType, String latLng) {
        return webService.getVenues(venueType, latLng);
    }

    public LiveData<Resource<List<Venue>>> getVenuesByLocation(String venueType, String latLng) {
        return webService.getVenuesByLocation(venueType, latLng);
    }

    public LiveData<Resource<Venue>> getVenueDetail(String venueID) {
        return webService.getVenueDetail(venueID);
    }

}
