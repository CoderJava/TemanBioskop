package ysn.temanbioskop.views.submenu.home;

import ysn.temanbioskop.views.base.Presenter;

/**
 * Created by root on 21/02/17.
 */

public class HomeFragmentPresenter implements Presenter<HomeFragmentView> {

    private HomeFragmentView homeFragmentView;

    @Override
    public void onAttach(HomeFragmentView view) {
        homeFragmentView = view;
    }

    @Override
    public void onDetach() {
        homeFragmentView = null;
    }


    public void setSlideShow() {
        homeFragmentView.onSetSlideShow();
    }

    public void setPageSwitcher(int seconds) {
        homeFragmentView.onSetPageSwitcher(seconds);
    }

    public void setTimerTask() {
        homeFragmentView.onSetTimerTask();
    }

    public void loadDataDiscoverMovies() {
        homeFragmentView.onLoadDataDiscoverMovies();
    }

    public void loadDataUpcomingMovies() {
        homeFragmentView.onLoadDataUpcomingMovies();
    }
}
