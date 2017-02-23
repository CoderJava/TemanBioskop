package ysn.temanbioskop.internal.model.bioskop.kota;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 23/02/17.
 */

public class DataKota {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<DatumKota> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DatumKota> getData() {
        return data;
    }

    public void setData(List<DatumKota> data) {
        this.data = data;
    }

}
