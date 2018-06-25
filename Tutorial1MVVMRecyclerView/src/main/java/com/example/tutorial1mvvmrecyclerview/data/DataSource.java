package com.example.tutorial1mvvmrecyclerview.data;


import com.example.tutorial1mvvmrecyclerview.model.Marker;

import java.util.List;

public interface DataSource {
     List<Marker> getAll();
     long addMarker(Marker marker);
     int update(Marker marker);
     int deleteMarker(Marker marker);
     void deleteAll();
}
