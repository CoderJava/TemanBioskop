package ysn.temanbioskop.views.main;

import ysn.temanbioskop.views.base.Presenter;

/**
 * Created by root on 21/02/17.
 */

public class MainActivityPresenter implements Presenter<MainActivityView> {

    private MainActivityView mainActivityView;

    @Override
    public void onAttach(MainActivityView view) {
        mainActivityView = view;
    }

    @Override
    public void onDetach() {
        mainActivityView = null;
    }

    public void setBackgroundBlur() {
        mainActivityView.onSetBackgroundBlur();
    }

}
