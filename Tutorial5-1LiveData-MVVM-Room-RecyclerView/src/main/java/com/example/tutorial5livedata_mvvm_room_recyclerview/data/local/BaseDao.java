package com.example.tutorial5livedata_mvvm_room_recyclerview.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface BaseDao<T> {

    @Insert(onConflict = REPLACE)
    long insert(T entity);

    @Update
    int update(T entity);

    @Delete
    int delete(T entity);

}
