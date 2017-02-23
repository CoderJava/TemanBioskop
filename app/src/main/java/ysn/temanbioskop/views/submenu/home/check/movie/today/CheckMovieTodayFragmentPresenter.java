package ysn.temanbioskop.views.submenu.home.check.movie.today;

import ysn.temanbioskop.views.base.Presenter;

/**
 * Created by root on 23/02/17.
 */

public class CheckMovieTodayFragmentPresenter implements Presenter<CheckMovieTodayFragmentView> {

    private CheckMovieTodayFragmentView checkMovieTodayFragmentView;

    @Override
    public void onAttach(CheckMovieTodayFragmentView view) {
        checkMovieTodayFragmentView = view;
    }

    @Override
    public void onDetach() {
        checkMovieTodayFragmentView = null;
    }

    public void setBackgroundBlur() {
        checkMovieTodayFragmentView.onSetBackgroundBlur();
    }

    public void loadAdapterRecyclerView() {
        checkMovieTodayFragmentView.onLoadAdapterRecyclerView();
    }

    public void loadDataKota() {
        checkMovieTodayFragmentView.onLoadDataKota();
    }

    public void showResultCheckMovieToday(String idKota, String namaKota) {
        checkMovieTodayFragmentView.onShowResultCheckMovieToday(idKota, namaKota);
    }

    public void filterNamaKota(String query) {
        checkMovieTodayFragmentView.onFilterNamaKota(query);
    }

}
