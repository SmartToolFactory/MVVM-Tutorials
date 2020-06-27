package com.test.foursquaremultiple.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.test.foursquaremultiple.model.Query;
import com.test.foursquaremultiple.model.Venue;
import com.test.foursquaremultiple.repo.Resource;
import com.test.foursquaremultiple.repo.VenueRepository;
import com.test.foursquaremultiple.venuesearch.VenueSearchFragment;

import java.util.List;

import javax.inject.Inject;


public class VenueSearchViewModel extends ViewModel {


    /**
     * Wrapper class for data fetch status, data, and status messages for venue list queries
     */
    private LiveData<Resource<List<Venue>>> mVenueListData;


    /**
     * Query LiveData object to change queryLiveData changes and ask for new data from repository on changes.
     */
    public MutableLiveData<Query> queryLiveData = new MutableLiveData<>();

    public boolean isEventConsumed = false;

    private Query venueListQuery;


    @Inject
    public VenueSearchViewModel(VenueRepository venueRepository) {

        venueListQuery = new Query();

        /*
        Creates a LiveData, let's name it swLiveData, which follows next flow:
        it reacts on changes of trigger LiveData, applies the given function to new value of trigger LiveData
        and sets resulting LiveData as a "backing" LiveData to swLiveData.
         */

        // Gets LiveData that contains venues via VenueRepository. Repository methods are triggered by changes on queryLiveData object.

        // Note: This is required to bind same LiveData instance to fragment or activity
        mVenueListData = Transformations.switchMap(queryLiveData, input -> {

            if (queryLiveData.getValue() == null) {
                return new MutableLiveData<>();
            } else {
                if (venueListQuery.type == Query.QUERY_VENUES_BY_NAME) {
                    return venueRepository.getVenues(input.venueType, input.venueLocation);
                } else {
                    return venueRepository.getVenuesByLocation(input.venueType, input.venueLocation);
                }
            }
        });
    }

    /**
     * Set Query Object type to get venues by city or area name, and change venueListQuery status in {@link VenueSearchFragment}'s onQuery() method
     * onQuery method is bound to layout via data binding.
     * venueType, and venueLocation via data binding of viewModel in layout.
     * Changes on queryVenues object trigger Transformation.switch to retrieve data via venueRepository
     */
    public void queryVenues(String venueType, String venueLocation) {
        venueListQuery.setVenueListQuery(Query.QUERY_VENUES_BY_NAME, venueType, venueLocation);
        queryLiveData.setValue(venueListQuery);
    }

    /**
     * Set Query Object type to get venues by latitude and longitude, and change venueListQuery status in {@link VenueSearchFragment}'s onQuery() method
     * onQuery method is bound to layout via data binding.
     * venueType, and venueLocation via data binding of viewModel in layout.
     * Changes on queryVenues object trigger Transformation.switch to retrieve data via venueRepository
     */
    public void queryVenuesByLocation(String venueType, String near) {
        venueListQuery.setVenueListQuery(Query.QUERY_VENUES_BY_LOCATION, venueType, near);
        queryLiveData.setValue(venueListQuery);
    }

    /**
     * Get data and status wrapped inside Resource class
     *
     * @return data and status wrapped inside resource
     */
    public LiveData<Resource<List<Venue>>> getVenueListResource() {
        return mVenueListData;
    }

}
