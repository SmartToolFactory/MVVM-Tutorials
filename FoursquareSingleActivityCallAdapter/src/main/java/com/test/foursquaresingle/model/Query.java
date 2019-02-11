package com.test.foursquaresingle.model;

import javax.inject.Inject;

/**
 * Query class stores type of query for view model to request venues by location or name.
 * Also stores venue type, location and id
 */


public class Query {

    /**
     * Query option to get user's current location
     */
    public static int QUERY_VENUES_BY_LOCATION = 0;
    /**
     * Query option to choose a city or are
     */
    public static int QUERY_VENUES_BY_NAME = 1;


    public int type;
    public String id;
    public String venueType;
    public String venueLocation;
    public long date;


    @Inject
    public Query() {

    }


    public void setVenueListQuery(int queryType, String venueType, String venueLocation) {
        this.type = queryType;
        this.venueType = venueType;
        this.venueLocation = venueLocation;
    }
}
