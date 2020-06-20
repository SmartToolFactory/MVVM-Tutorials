package com.example.tutorial5livedata_mvvm_room_recyclerview.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface BaseDao<T> {

    @Insert(onConflict = REPLACE)
    long insert(T entity);

    @Update
    int update(T entity);

    @Delete
    int delete(T entity);

}
