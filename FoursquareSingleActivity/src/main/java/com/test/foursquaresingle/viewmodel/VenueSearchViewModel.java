package com.test.foursquaresingle.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.test.foursquaresingle.model.Query;
import com.test.foursquaresingle.model.Venue;
import com.test.foursquaresingle.repo.Resource;
import com.test.foursquaresingle.repo.VenueRepository;
import com.test.foursquaresingle.view.venuesearch.VenueSearchFragment;

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

  /*
        mVenueListData = Transformations.switchMap(queryLiveData, input -> {

            if (input == null) {
                return new MutableLiveData<>();
            } else {
                if (input.type == Query.QUERY_VENUES_BY_NAME) {
                    return venueRepository.getVenues(input.venueType, input.venueLocation);
                } else {
                    return venueRepository.getVenuesByLocation(input.venueType, input.venueLocation);
                }
            }
        });
        */

/*

        // Without lambda
        mVenueListData = Transformations.switchMap(queryLiveData, new Function<Query, LiveData<Resource<List<Venue>>>>() {
            @Override
            public LiveData<Resource<List<Venue>>> apply(Query input) {
                if (input == null) {
                    return new MutableLiveData<>();
                } else {
                    if (input.type == Query.QUERY_VENUES_BY_NAME) {
                        return venueRepository.getVenues(input.venueType, input.venueLocation);
                    } else {
                        return venueRepository.getVenuesByLocation(input.venueType, input.venueLocation);
                    }
                }
            }
        });
*/

        // With built-in function
        mVenueListData = switchMap(queryLiveData, new Function<Query, LiveData<Resource<List<Venue>>>>() {
            @Override
            public LiveData<Resource<List<Venue>>> apply(Query input) {
                if (input == null) {
                    return new MutableLiveData<>();
                } else {
                    if (input.type == Query.QUERY_VENUES_BY_NAME) {
                        return venueRepository.getVenues(input.venueType, input.venueLocation);
                    } else {
                        return venueRepository.getVenuesByLocation(input.venueType, input.venueLocation);
                    }
                }
            }
        });

        System.out.println("VenueSearchViewModel mVenueListData: " + mVenueListData);
    }


    @MainThread
    public static <X, Y> LiveData<Y> switchMap(@NonNull LiveData<X> trigger,
                                               @NonNull final Function<X, LiveData<Y>> func) {

        final MediatorLiveData<Y> result = new MediatorLiveData<>();

//        System.out.println("VenueSearchViewModel switchMap() TRIGGER: " + trigger);
//        System.out.println("VenueSearchViewModel switchMap() RESULT: " + result);

        result.addSource(trigger, new Observer<X>() {

            LiveData<Y> mSource;

            @Override
            public void onChanged(@Nullable X x) {

                LiveData<Y> newLiveData = func.apply(x);


                if (mSource == newLiveData) {
                    return;
                }

                if (mSource != null) {
                    result.removeSource(mSource);
                }

                mSource = newLiveData;

                System.out.println("VenueSearchViewModel switchMap()->onChanged() Thread: " + Thread.currentThread().getName() + ", mSource: " + mSource);


                if (mSource != null) {
                    result.addSource(mSource, new Observer<Y>() {
                        @Override
                        public void onChanged(@Nullable Y y) {
                            result.setValue(y);

                            System.out.println("VenueSearchViewModel switchMap()->onChanged()2 Thread: " + Thread.currentThread().getName() + ", mSource: " + mSource);

                        }
                    });
                }


            }
        });

        return result;
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

        venueListQuery.setVenueListQuery(Query.QUERY_VENUES_BY_NAME, "bar", "Berlin");
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