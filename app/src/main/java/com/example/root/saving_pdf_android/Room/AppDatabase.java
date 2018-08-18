package com.example.root.saving_pdf_android.Room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by root_iav on 23/01/18.
 */

@Database(entities = {Jurnal.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract JurnalDao userDao();
}
