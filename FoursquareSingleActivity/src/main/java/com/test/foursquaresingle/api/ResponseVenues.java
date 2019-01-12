package com.test.foursquaresingle.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.test.foursquaresingle.model.Venue;

import java.util.ArrayList;


public class ResponseVenues implements Parcelable {

    private ArrayList<Venue> venues;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.venues);
    }

    public ResponseVenues() {
    }

    protected ResponseVenues(Parcel in) {
        this.venues = new ArrayList<>();
        in.readList(this.venues, Venue.class.getClassLoader());
    }

    public static final Creator<ResponseVenues> CREATOR = new Creator<ResponseVenues>() {
        @Override
        public ResponseVenues createFromParcel(Parcel source) {
            return new ResponseVenues(source);
        }

        @Override
        public ResponseVenues[] newArray(int size) {
            return new ResponseVenues[size];
        }
    };

    public ArrayList<Venue> getVenues() {
        return venues;
    }

    public Venue getVenue(int pos) {

        if (venues == null)
            return null;

        return venues.get(pos);
    }
}
