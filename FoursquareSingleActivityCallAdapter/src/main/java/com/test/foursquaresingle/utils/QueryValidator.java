package com.test.foursquaresingle.utils;

import java.util.regex.Pattern;

/**
 * Check and validate Venue queries meet the specified conditions
 */
public class QueryValidator {

    public static boolean isValidQuery(String venueType) {

        if (venueType == null)
            return false;

        if (venueType.length() < 3)
            return false;

        String trimmedVenueType = venueType.replaceAll(" ", "");

        //area has to be only alphabetic character
        if (!Pattern.matches("[a-zA-Z]+", trimmedVenueType))
            return false;

        return true;
    }


}