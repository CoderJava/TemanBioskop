package ysn.temanbioskop.views.submenu.home.check.movie.today;

import ysn.temanbioskop.views.base.View;

/**
 * Created by root on 23/02/17.
 */

public interface CheckMovieTodayActivityView extends View {

    void onSetBackgroundBlur();

    void onLoadAdapterRecyclerView();

    void onLoadDataKota();

    void onShowResultCheckMovieToday();

    void onFilterNamaKota(String query);

}
