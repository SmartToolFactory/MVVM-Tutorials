package com.test.foursquaresingle.view.callback;

import com.test.foursquaresingle.model.Venue;
import com.test.foursquaresingle.view.venuelist.VenueListAdapter;

/**
 * Callback for {@link VenueListAdapter} on item click to display details of Venue
 */
public interface OnVenueClickListener {
    void onClick(Venue venue);
}
