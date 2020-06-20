
package com.test.foursquaremultiple.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.test.foursquaremultiple.model.Venue;
import com.test.foursquaremultiple.repo.Resource;
import com.test.foursquaremultiple.repo.VenueRepository;

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

