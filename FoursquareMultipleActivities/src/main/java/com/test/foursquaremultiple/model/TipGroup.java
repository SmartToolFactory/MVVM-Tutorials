package com.test.foursquaremultiple.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class TipGroup implements Parcelable {
    String type;
    String name;
    int count;
    ArrayList<UserComment> items;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.name);
        dest.writeInt(this.count);
        dest.writeList(this.items);
    }

    public TipGroup() {
    }

    protected TipGroup(Parcel in) {
        this.type = in.readString();
        this.name = in.readString();
        this.count = in.readInt();
        this.items = new ArrayList<UserComment>();
        in.readList(this.items, UserComment.class.getClassLoader());
    }

    public static final Creator<TipGroup> CREATOR = new Creator<TipGroup>() {
        @Override
        public TipGroup createFromParcel(Parcel source) {
            return new TipGroup(source);
        }

        @Override
        public TipGroup[] newArray(int size) {
            return new TipGroup[size];
        }
    };

    public ArrayList<UserComment> getItems() {
        return items;
    }
}
