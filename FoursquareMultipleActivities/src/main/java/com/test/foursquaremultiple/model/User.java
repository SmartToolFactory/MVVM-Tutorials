package com.test.foursquaremultiple.model;

import android.os.Parcel;
import android.os.Parcelable;


public class User implements Parcelable {
    String  id;
    String  firstName;
    String  lastName;
    String  gender;
    Photo photo;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.gender);
        dest.writeParcelable(this.photo, flags);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.id = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.gender = in.readString();
        this.photo = in.readParcelable(Photo.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public Photo getPhoto() {
        return photo;
    }
}
