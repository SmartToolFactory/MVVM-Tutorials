package com.example.tutorial5livedata_mvvm_room_recyclerview.data.local;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.tutorial5livedata_mvvm_room_recyclerview.model.Marker;

import static com.example.tutorial5livedata_mvvm_room_recyclerview.data.local.AppDatabase.DATABASE_VERSION;

@Database(entities = {Marker.class}, version = DATABASE_VERSION)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "marker.db";
    public static final int DATABASE_VERSION = 1;

    private static AppDatabase INSTANCE;

    private static final Object sLock = new Object();

    public abstract MarkerDao markerDao();

    public static AppDatabase getInstance(Context context) {

        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, DATABASE_NAME)
                    //    .allowMainThreadQueries()
                        .build();
            }
            return INSTANCE;
        }
    }

}
