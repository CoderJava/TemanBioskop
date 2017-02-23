package ysn.temanbioskop.views.submenu.home.check.movie.today.result;

import ysn.temanbioskop.views.base.Presenter;

/**
 * Created by root on 23/02/17.
 */

public class ResultCheckMovieTodayFragmentPresenter implements Presenter<ResultCheckMovieTodayFragmentView> {

    ResultCheckMovieTodayFragmentView resultCheckMovieTodayFragmentView;

    @Override
    public void onAttach(ResultCheckMovieTodayFragmentView view) {
        resultCheckMovieTodayFragmentView = view;
    }

    @Override
    public void onDetach() {
        resultCheckMovieTodayFragmentView = null;
    }

    public void loadDataJadwal() {
        resultCheckMovieTodayFragmentView.onLoadDataJadwal();
    }

    public void loadPoster() {
        resultCheckMovieTodayFragmentView.onLoadPoster();
    }

    public void setBackgroundBlur() {
        resultCheckMovieTodayFragmentView.onSetBackgroundBlur();
    }

    public void showConnectionError() {
        resultCheckMovieTodayFragmentView.onShowConnectionError();
    }

    public void loadAdapter() {
        resultCheckMovieTodayFragmentView.onLoadAdapter();
    }
}
