package com.test.foursquaresingle.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Photo implements Parcelable {

    @SerializedName("prefix")
    String prefix;

    @SerializedName("suffix")
    String suffix;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.prefix);
        dest.writeString(this.suffix);
    }

    public Photo() {
    }

    protected Photo(Parcel in) {
        this.prefix = in.readString();
        this.suffix = in.readString();
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel source) {
            return new Photo(source);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    public String getImageBySize(String size) {
        String imageUrl = prefix + size + suffix;
        return imageUrl;
    }
}
