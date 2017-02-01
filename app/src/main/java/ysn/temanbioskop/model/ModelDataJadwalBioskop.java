package ysn.temanbioskop.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by root on 11/25/16.
 */

public class ModelDataJadwalBioskop implements Serializable{

    private String namaBioskop;
    private String[] jam;
    private String harga;

    public String getNamaBioskop() {
        return namaBioskop;
    }

    public void setNamaBioskop(String namaBioskop) {
        this.namaBioskop = namaBioskop;
    }

    public String[] getJam() {
        return jam;
    }

    public void setJam(String[] jam) {
        this.jam = jam;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

}
