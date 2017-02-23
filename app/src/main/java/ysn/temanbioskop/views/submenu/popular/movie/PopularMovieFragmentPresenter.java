package ysn.temanbioskop.views.submenu.popular.movie;

import ysn.temanbioskop.views.base.Presenter;

/**
 * Created by root on 23/02/17.
 */

public class PopularMovieFragmentPresenter implements Presenter<PopularMovieFragmentView> {

    PopularMovieFragmentView popularMovieFragmentView;

    @Override
    public void onAttach(PopularMovieFragmentView view) {
        popularMovieFragmentView =view;
    }

    @Override
    public void onDetach() {
        popularMovieFragmentView = null;
    }
}
