package com.example.tutorial5livedata_mvvm_room_recyclerview.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.tutorial5livedata_mvvm_room_recyclerview.model.Marker;

import java.util.List;

@Dao
public interface MarkerDao extends BaseDao<Marker> {

    @Query("SELECT * FROM table_marker")
    LiveData<List<Marker>> getAll();

    @Query("DELETE FROM table_marker")
    int deleteAll();
}
