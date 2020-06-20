package com.test.foursquaremultiple.model;

import androidx.databinding.BindingAdapter;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.test.foursquaremultiple.R;

public class Venue implements Parcelable {
    private String id;
    private String name;
    private Location location;
    private Tips tips;
    private Photo bestPhoto;

    public Venue() {
    }


    public String getName() {
        return name;
    }

    public String getAddress() {

        if (location == null)
            return null;

        return location.getAddress();
    }

    public String getCountry() {

        if (location == null)
            return null;

        return location.getCountry();
    }

    public Location getLocation() {
        return location;
    }


    public String getId() {
        return id;
    }

    public Tips getTips() {
        return tips;
    }

    /**
     * Binding adapter used with this class android:src used with binding of this onbject
     * loads image from url into specified view
     *
     * @param view image to be loaded into
     * @param url  of the image to be fetched
     */
    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_image_placeholder_darker);
        requestOptions.error(R.drawable.ic_image_placeholder_grey);

        Glide.with(view.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(url)
                .into(view);
    }

    public Photo getBestPhoto() {
        return bestPhoto;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeParcelable(this.location, flags);
        dest.writeParcelable(this.tips, flags);
        dest.writeParcelable(this.bestPhoto, flags);
    }


    protected Venue(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.tips = in.readParcelable(Tips.class.getClassLoader());
        this.bestPhoto = in.readParcelable(Photo.class.getClassLoader());
    }

    public static final Creator<Venue> CREATOR = new Creator<Venue>() {
        @Override
        public Venue createFromParcel(Parcel source) {
            return new Venue(source);
        }

        @Override
        public Venue[] newArray(int size) {
            return new Venue[size];
        }
    };
}