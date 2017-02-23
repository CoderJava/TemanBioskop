package ysn.temanbioskop.internal.model.bioskop.jadwal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 23/02/17.
 */

public class Jadwal {

    @SerializedName("bioskop")
    @Expose
    private String bioskop;
    @SerializedName("jam")
    @Expose
    private List<String> jam = null;
    @SerializedName("harga")
    @Expose
    private String harga;

    public String getBioskop() {
        return bioskop;
    }

    public void setBioskop(String bioskop) {
        this.bioskop = bioskop;
    }

    public List<String> getJam() {
        return jam;
    }

    public void setJam(List<String> jam) {
        this.jam = jam;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

}
