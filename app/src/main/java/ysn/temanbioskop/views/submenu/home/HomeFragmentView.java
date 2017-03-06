package ysn.temanbioskop.views.submenu.home;

import ysn.temanbioskop.views.base.View;

/**
 * Created by root on 21/02/17.
 */

public interface HomeFragmentView extends View {

    void onSetSlideShow();

    void onSetPageSwitcher(int seconds);

    void onSetTimerTask();

    void onLoadDataDiscoverMovies();

    void onLoadDataUpcomingMovies();
}
