package ysn.temanbioskop.views.fragment.home;

import ysn.temanbioskop.views.base.Presenter;

/**
 * Created by root on 21/02/17.
 */

public class HomeFragmentPresenter implements Presenter<HomeFragmentView> {

    HomeFragmentView homeFragmentView;

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

    public void setTimerTask(int page) {
        homeFragmentView.onSetTimerTask(page);
    }
}
