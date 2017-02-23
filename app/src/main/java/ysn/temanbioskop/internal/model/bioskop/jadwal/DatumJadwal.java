package ysn.temanbioskop.internal.model.bioskop.jadwal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 23/02/17.
 */

public class DatumJadwal {

    @SerializedName("movie")
    @Expose
    private String movie;
    @SerializedName("poster")
    @Expose
    private String poster;
    @SerializedName("genre")
    @Expose
    private String genre;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("jadwal")
    @Expose
    private List<Jadwal> jadwal = null;

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Jadwal> getJadwal() {
        return jadwal;
    }

    public void setJadwal(List<Jadwal> jadwal) {
        this.jadwal = jadwal;
    }

}
