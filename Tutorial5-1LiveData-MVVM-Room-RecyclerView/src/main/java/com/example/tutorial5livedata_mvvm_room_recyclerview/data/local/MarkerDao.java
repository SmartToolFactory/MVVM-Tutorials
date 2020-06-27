package com.example.tutorial5livedata_mvvm_room_recyclerview.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.tutorial5livedata_mvvm_room_recyclerview.model.Marker;

import java.util.List;

@Dao
public interface MarkerDao extends BaseDao<Marker> {

    @Query("SELECT * FROM table_marker")
    LiveData<List<Marker>> getAll();

    @Query("DELETE FROM table_marker")
    int deleteAll();
}
