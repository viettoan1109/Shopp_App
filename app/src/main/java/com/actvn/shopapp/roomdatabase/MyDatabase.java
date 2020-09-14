package com.actvn.shopapp.roomdatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities={Cart.class},version = 1,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public abstract CartDao cartDao();
}
