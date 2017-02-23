package ysn.temanbioskop.views.fragment.home.check.movie.today;

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
}
