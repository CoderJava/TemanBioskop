package ysn.temanbioskop.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ysn.temanbioskop.internal.model.bioskop.jadwal.DataJadwal;
import ysn.temanbioskop.internal.model.bioskop.kota.DataKota;

/**
 * Created by root on 23/02/17.
 */

public interface JadwalBioskopApiService {

    public final String baseApiUrl = "http://ibacor.com/";
    public final String apiKey = "39ebb7dbb8461e0dd0f5bf48e5df7a59";

    @GET("api/jadwal-bioskop")
    Call<DataKota> getDataKota(@Query("k") String apiKey);

    @GET("api/jadwal-bioskop")
    Call<DataJadwal> getDataJadwal(
            @Query("id") String id,
            @Query("k") String apiKey
    );
}
