package com.example.tutorial1mvvmrecyclerview.model;

import android.databinding.ObservableField;

public class Marker {

    private long id;


    private ObservableField<String> title = new ObservableField<>();

    private ObservableField<String> latitude= new ObservableField<>();
    private ObservableField<String> longitude= new ObservableField<>();

    private ObservableField<String> address= new ObservableField<>();
    private ObservableField<Integer> imageId= new ObservableField<>();
    private ObservableField<String> note= new ObservableField<>();

    private ObservableField<Long> date= new ObservableField<>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ObservableField<String> getTitle() {
        return title;
    }

    public void setTitle(ObservableField<String> title) {
        this.title = title;
    }

    public ObservableField<String> getLatitude() {
        return latitude;
    }

    public void setLatitude(ObservableField<String> latitude) {
        this.latitude = latitude;
    }

    public ObservableField<String> getLongitude() {
        return longitude;
    }

    public void setLongitude(ObservableField<String> longitude) {
        this.longitude = longitude;
    }

    public ObservableField<String> getAddress() {
        return address;
    }

    public void setAddress(ObservableField<String> address) {
        this.address = address;
    }

    public ObservableField<Integer> getImageId() {
        return imageId;
    }

    public void setImageId(ObservableField<Integer> imageId) {
        this.imageId = imageId;
    }

    public ObservableField<String> getNote() {
        return note;
    }

    public void setNote(ObservableField<String> note) {
        this.note = note;
    }

    public ObservableField<Long> getDate() {
        return date;
    }

    public void setDate(ObservableField<Long> date) {
        this.date = date;
    }



}
