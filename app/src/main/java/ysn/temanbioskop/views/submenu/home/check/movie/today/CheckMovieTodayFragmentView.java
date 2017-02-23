package ysn.temanbioskop.views.submenu.home.check.movie.today;

import ysn.temanbioskop.views.base.View;

/**
 * Created by root on 23/02/17.
 */

public interface CheckMovieTodayFragmentView extends View {

    void onSetBackgroundBlur();

    void onLoadAdapterRecyclerView();

    void onLoadDataKota();

    void onShowResultCheckMovieToday(String idKota, String namaKota);

    void onFilterNamaKota(String query);

}
