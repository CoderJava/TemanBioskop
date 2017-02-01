package ysn.temanbioskop.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by root on 11/25/16.
 */

public class ModelDataDetailJadwalBioskop implements Serializable {

    private String movie;
    private String poster;
    private String genre;
    private String duration;
    private ArrayList<ModelDataJadwalBioskop> jadwal;

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

    public ArrayList<ModelDataJadwalBioskop> getJadwal() {
        return jadwal;
    }

    public void setJadwal(ArrayList<ModelDataJadwalBioskop> jadwal) {
        this.jadwal = jadwal;
    }
}
