package com.test.foursquaresingle.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface FoursquareService {

    String FOURSQUARE_VERSION = "20130815";
    String FOURSQUARE_CLIENT_ID = "AHGMLIAOHLNG1NU2IT525G0RJ2I5PXOCUBVCL5N5EJX4L321";
    String FOURSQUARE_SECRET_ID = "YLULMZUWQ0XIKC2O2HJQFW0NCNCDIRF22C3BSLSJUT24G4Z1";

    String BASE_URL = "https://api.foursquare.com";

    /**
     * Get venues near specified city or area with specified type.
     * <p></p>
     * <p>
     * For example, query for getting bars list near Rome:
     * </p>
     * <p>
     * https://api.foursquare.com/v2/venues/search?
     * v={@param version}
     * &client_id={@param clientID}
     * &client_secret={@param clientSecret}
     * &query={@param venueType}
     * &near={@param near}
     * </p>
     *
     * @param version      determines which version of Foursquare api to be used. Format is YYYYMMDD
     * @param clientID     id of client
     * @param clientSecret secret id of client
     * @param venueType    select type of venue to be returned. Bar, mall, etc.
     * @param near         is Geo-coded geographical city or are such as Rome, Istanbul, etc.
     * @return venues with specified type near selected city or arer
     */

    @GET("/v2/venues/search")
    Call<VenueListResponse> getVenues(@Query("v") String version,
                                      @Query("client_id") String clientID,
                                      @Query("client_secret") String clientSecret,
                                      @Query("query") String venueType,
                                      @Query("near") String near);

    /**
     * @param version      determines which version of Foursquare api to be used. Format is YYYYMMDD
     * @param clientID     id of client
     * @param clientSecret secret id of client
     * @param venueType    select type of venue to be returned. Bar, mall, etc.
     * @param latLng       latitude and longitude of zero search point. Venues with default distance to this point are returned
     * @return venues with given type near specified latitude and longitude
     */
    @GET("/v2/venues/search")
    Call<VenueListResponse> getVenuesByLocation(@Query("v") String version,
                                                @Query("client_id") String clientID,
                                                @Query("client_secret") String clientSecret,
                                                @Query("query") String venueType,
                                                @Query("ll") String latLng);

    /**
     * Get details of venue with given id
     *
     * @param venueID      Id of the venue to get details of
     * @param version      determines the which version of Foursquare api to be used. Format is YYYYMMDD
     * @param clientID     id of client
     * @param clientSecret secret id of client
     * @return details of the selected venue
     */
    @GET("/v2/venues/{venue_id}")
    Call<VenueDetailResponse> getVenueDetail(@Path("venue_id") String venueID,
                                             @Query("v") String version,
                                             @Query("client_id") String clientID,
                                             @Query("client_secret") String clientSecret);

}
