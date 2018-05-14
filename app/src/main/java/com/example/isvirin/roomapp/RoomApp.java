package com.example.isvirin.roomapp;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.isvirin.roomapp.db.AppDatabase;

public class RoomApp extends Application {
    public static AppDatabase db;
    @Override
    public void onCreate() {
        super.onCreate();

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();
    }
}
