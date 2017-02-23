package ysn.temanbioskop.internal.model.bioskop.jadwal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 23/02/17.
 */

public class DataJadwal {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("kota")
    @Expose
    private String kota;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("data")
    @Expose
    private List<DatumJadwal> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<DatumJadwal> getData() {
        return data;
    }

    public void setData(List<DatumJadwal> data) {
        this.data = data;
    }

}
