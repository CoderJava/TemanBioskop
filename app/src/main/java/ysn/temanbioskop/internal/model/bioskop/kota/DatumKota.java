package ysn.temanbioskop.internal.model.bioskop.kota;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 23/02/17.
 */

public class DatumKota {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("kota")
    @Expose
    private String kota;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

}
