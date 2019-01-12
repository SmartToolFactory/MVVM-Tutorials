
package com.test.foursquaresingle.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.test.foursquaresingle.model.Venue;
import com.test.foursquaresingle.repo.Resource;
import com.test.foursquaresingle.repo.VenueRepository;

import javax.inject.Inject;

public class VenueDetailViewModel extends ViewModel {


    private MutableLiveData<String> venueIDLiveData = new MutableLiveData<>();


    /**
     * Wrapper class for data fetch status, data, and status messages for venue list queries
     */

    private LiveData<Resource<Venue>> venueDetail = new MutableLiveData<>();

    public boolean isEventConsumed = false;


    @Inject
    public VenueDetailViewModel(VenueRepository venueRepository) {

        venueDetail = Transformations.switchMap(venueIDLiveData, input -> venueRepository.getVenueDetail(input));

    }

    public LiveData<Resource<Venue>> getVenueDetail() {
        return venueDetail;
    }


    public void queryVenueDetail(String venueID) {
        isEventConsumed = false;
        venueIDLiveData.setValue(venueID);
    }


}

