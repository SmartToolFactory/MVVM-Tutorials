package com.test.foursquaremultiple.view.callback;

import com.test.foursquaremultiple.model.Venue;
import com.test.foursquaremultiple.venuelist.VenueListAdapter;

/**
 * Callback for {@link VenueListAdapter} on item click to display details of Venue
 */
public interface OnVenueClickListener {
    void onClick(Venue venue);
}
