package com.jurnal.root.saving_pdf_android.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root_iav on 16/01/18.
 */
@Dao
public interface JurnalDao {

    @Query("SELECT * FROM Jurnal")
    List<Jurnal> getAll();

    @Query("SELECT * FROM Jurnal WHERE tanggal LIKE :tanggal ")
    List<Jurnal> findByName(String tanggal);

    @Insert
    void insertAll(Jurnal jurnal);

    @Delete
    public void deleteUsers(Jurnal users);

    @Update
    public void update(Jurnal jurnal);

    @Delete
    public void deleteAll(Jurnal user1, Jurnal user2);

}
