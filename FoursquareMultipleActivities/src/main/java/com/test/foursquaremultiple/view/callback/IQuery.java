package com.test.foursquaremultiple.view.callback;

import com.test.foursquaremultiple.venuesearch.VenueSearchFragment;

/**
 * Interface for changing query type of VenueSearchViewModel and query venue data of repository
 * in {@link VenueSearchFragment}
 */
public interface IQuery {
    void onQuery();
}