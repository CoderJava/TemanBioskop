package ysn.temanbioskop.views.submenu.favorite.movie;

import ysn.temanbioskop.views.base.Presenter;

/**
 * Created by root on 23/02/17.
 */

public class FavoriteMovieFragmentPresenter implements Presenter<FavoriteMovieFragmentView> {

    FavoriteMovieFragmentView favoriteMovieFragmentView;

    @Override
    public void onAttach(FavoriteMovieFragmentView view) {
        favoriteMovieFragmentView = view;
    }

    @Override
    public void onDetach() {
        favoriteMovieFragmentView = null;
    }
}
