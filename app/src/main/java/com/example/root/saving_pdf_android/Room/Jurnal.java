package com.example.root.saving_pdf_android.Room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by khoiron on 23/01/18.
 */
@Entity
public class Jurnal {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "waktu_masuk")
    String waktu_masuk;
    @ColumnInfo(name = "waktu_keluar")
    String waktu_keluar;
    @ColumnInfo(name = "tanggal")
    String tanggal;
    @ColumnInfo(name = "uraian")
    String uraian;

    public String getUraian() {
        return uraian;
    }

    public void setUraian(String uraian) {
        this.uraian = uraian;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWaktu_masuk() {
        return waktu_masuk;
    }

    public void setWaktu_masuk(String waktu_masuk) {
        this.waktu_masuk = waktu_masuk;
    }

    public String getWaktu_keluar() {
        return waktu_keluar;
    }

    public void setWaktu_keluar(String waktu_keluar) {
        this.waktu_keluar = waktu_keluar;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
