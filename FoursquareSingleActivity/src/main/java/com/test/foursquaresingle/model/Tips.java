package com.test.foursquaresingle.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Tips implements Parcelable {
    int count;
    ArrayList<TipGroup> groups;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
        dest.writeList(this.groups);
    }

    public Tips() {
    }

    protected Tips(Parcel in) {
        this.count = in.readInt();
        this.groups = new ArrayList<TipGroup>();
        in.readList(this.groups, TipGroup.class.getClassLoader());
    }

    public static final Creator<Tips> CREATOR = new Creator<Tips>() {
        @Override
        public Tips createFromParcel(Parcel source) {
            return new Tips(source);
        }

        @Override
        public Tips[] newArray(int size) {
            return new Tips[size];
        }
    };

    public ArrayList<TipGroup> getGroups() {
        return groups;
    }
}