package ysn.temanbioskop.views.submenu.home.check.movie.today;

import ysn.temanbioskop.views.base.Presenter;

/**
 * Created by root on 23/02/17.
 */

public class CheckMovieTodayActivityPresenter implements Presenter<CheckMovieTodayActivityView> {

    private CheckMovieTodayActivityView checkMovieTodayActivityView;

    @Override
    public void onAttach(CheckMovieTodayActivityView view) {
        checkMovieTodayActivityView = view;
    }

    @Override
    public void onDetach() {
        checkMovieTodayActivityView = null;
    }

    public void setBackgroundBlur() {
        checkMovieTodayActivityView.onSetBackgroundBlur();
    }

    public void loadAdapterRecyclerView() {
        checkMovieTodayActivityView.onLoadAdapterRecyclerView();
    }

    public void loadDataKota() {
        checkMovieTodayActivityView.onLoadDataKota();
    }

    public void showResultCheckMovieToday() {
        checkMovieTodayActivityView.onShowResultCheckMovieToday();
    }

    public void filterNamaKota(String query) {
        checkMovieTodayActivityView.onFilterNamaKota(query);
    }

}
