package com.test.foursquaresingle.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserComment implements Parcelable {

    private String id;
    private String text;
    private int agreeCount;
    private int disagreeCount;
    private User user;


    public String getText() {
        return text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.text);
        dest.writeInt(this.agreeCount);
        dest.writeInt(this.disagreeCount);
        dest.writeParcelable(this.user, flags);
    }

    public UserComment() {
    }

    protected UserComment(Parcel in) {
        this.id = in.readString();
        this.text = in.readString();
        this.agreeCount = in.readInt();
        this.disagreeCount = in.readInt();
        this.user = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<UserComment> CREATOR = new Creator<UserComment>() {
        @Override
        public UserComment createFromParcel(Parcel source) {
            return new UserComment(source);
        }

        @Override
        public UserComment[] newArray(int size) {
            return new UserComment[size];
        }
    };

    public User getUser() {
        return user;
    }


}
