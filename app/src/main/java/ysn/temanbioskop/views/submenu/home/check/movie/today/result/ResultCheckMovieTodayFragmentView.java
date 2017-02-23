package ysn.temanbioskop.views.submenu.home.check.movie.today.result;

import ysn.temanbioskop.views.base.View;

/**
 * Created by root on 23/02/17.
 */

public interface ResultCheckMovieTodayFragmentView extends View {

    void onLoadDataJadwal();

    void onLoadPoster();

    void onSetBackgroundBlur();

    void onShowConnectionError();

    void onLoadAdapter();

}
