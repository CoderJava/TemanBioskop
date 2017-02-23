package ysn.temanbioskop.views.submenu.favorite.movie;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ysn.temanbioskop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment implements FavoriteMovieFragmentView {

    private FavoriteMovieFragmentPresenter favoriteMovieFragmentPresenter;
    private View view;

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_favorite_movie, container, false);
        initPresenter();
        onAttach();
        return view;
    }

    private void initPresenter() {
        favoriteMovieFragmentPresenter = new FavoriteMovieFragmentPresenter();
    }

    @Override
    public void onAttach() {
        favoriteMovieFragmentPresenter.onAttach(this);
    }

    @Override
    public void onDetach() {
        favoriteMovieFragmentPresenter.onDetach();
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        favoriteMovieFragmentPresenter.onDetach();
        super.onDestroy();
    }
}
