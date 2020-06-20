package com.test.foursquaresingle.repo.remote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.test.foursquaresingle.api.ApiResponse;
import com.test.foursquaresingle.api.FoursquareService;
import com.test.foursquaresingle.api.VenueDetailResponse;
import com.test.foursquaresingle.api.VenueListResponse;
import com.test.foursquaresingle.model.Venue;
import com.test.foursquaresingle.repo.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * WebService uses FourSquareService interface to request make api calls and retrieve data from api
 */

@Singleton
public class WebService {

    @Inject
    FoursquareService foursquareService;

    @Inject
    public WebService() {

    }

    public LiveData<Resource<List<Venue>>> getVenues(String venueType, String near) {


        final MutableLiveData<Resource<List<Venue>>> venueListData = new MutableLiveData<>();

        venueListData.postValue(Resource.loading(null));

//        System.out.println("WebService onResponse() LOADING thread: " + Thread.currentThread().getName());


        Call<VenueListResponse> call = foursquareService.getVenues(
                FoursquareService.FOURSQUARE_VERSION,
                FoursquareService.FOURSQUARE_CLIENT_ID,
                FoursquareService.FOURSQUARE_SECRET_ID,
                venueType,
                near);

        call.enqueue(new Callback<VenueListResponse>() {
            @Override
            public void onResponse(Call<VenueListResponse> call, Response<VenueListResponse> response) {

//                System.out.println("WebService onResponse() SUCCESS thread: " + Thread.currentThread().getName());

                if (response.body() == null) {
                    venueListData.postValue(Resource.error("Error", null));
                } else {
                    venueListData.postValue(Resource.success(response.body().getResponse().getVenues()));
                }
            }

            @Override
            public void onFailure(Call<VenueListResponse> call, Throwable t) {
                venueListData.setValue(Resource.error(t.getMessage(), null));
            }
        });



        return venueListData;
    }

    public LiveData<ApiResponse<VenueListResponse>> getVenuesApiResponse(String venueType, String near) {

        LiveData<ApiResponse<VenueListResponse>> venues = foursquareService.getVenuesLive(
                FoursquareService.FOURSQUARE_VERSION,
                FoursquareService.FOURSQUARE_CLIENT_ID,
                FoursquareService.FOURSQUARE_SECRET_ID,
                venueType,
                near);


        return  venues;
    }

    public LiveData<Resource<List<Venue>>> getVenuesByLocation(String venueType, String latLng) {

        final MutableLiveData<Resource<List<Venue>>> venueListData = new MutableLiveData<>();

        venueListData.setValue(Resource.loading(null));

        Call<VenueListResponse> call = foursquareService.getVenuesByLocation(
                FoursquareService.FOURSQUARE_VERSION,
                FoursquareService.FOURSQUARE_CLIENT_ID,
                FoursquareService.FOURSQUARE_SECRET_ID,
                venueType,
                latLng);


        call.enqueue(new Callback<VenueListResponse>() {

            @Override
            public void onResponse(Call<VenueListResponse> call, Response<VenueListResponse> response) {

                if (response.body() == null) {
                    venueListData.setValue(Resource.error("Error", null));
                } else {
                    venueListData.setValue(Resource.success(response.body().getResponse().getVenues()));
                }
            }

            @Override
            public void onFailure(Call<VenueListResponse> call, Throwable t) {
                venueListData.setValue(Resource.error(t.getMessage(), null));
            }

        });


        return venueListData;
    }

    public LiveData<Resource<Venue>> getVenueDetail(String venueID) {

        MutableLiveData<Resource<Venue>> venueData = new MutableLiveData<>();

        venueData.setValue(Resource.loading(null));

        Call<VenueDetailResponse> call = foursquareService.getVenueDetail(
                venueID,
                FoursquareService.FOURSQUARE_VERSION,
                FoursquareService.FOURSQUARE_CLIENT_ID,
                FoursquareService.FOURSQUARE_SECRET_ID);


        call.enqueue(new Callback<VenueDetailResponse>() {

            @Override
            public void onResponse(Call<VenueDetailResponse> call, Response<VenueDetailResponse> response) {

                if (response.body() == null) {
                    venueData.setValue(Resource.error("Response body is NULL", null));
                } else {
                    venueData.setValue(Resource.success(response.body().getResponse().getVenue()));
                }
            }

            @Override
            public void onFailure(Call<VenueDetailResponse> call, Throwable t) {

                System.out.println("WebService onError() error: " + t.getMessage());
                venueData.setValue(Resource.error(t.getMessage(), null));
            }

        });

        return venueData;
    }
}
