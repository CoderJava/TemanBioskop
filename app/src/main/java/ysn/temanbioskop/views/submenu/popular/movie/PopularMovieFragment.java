package ysn.temanbioskop.views.submenu.popular.movie;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ysn.temanbioskop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularMovieFragment extends Fragment implements PopularMovieFragmentView {

    private PopularMovieFragmentPresenter popularMovieFragmentPresenter;
    private View view;

    public PopularMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_popular_movie, container, false);
        initPresenter();
        onAttach();
        return view;
    }

    private void initPresenter() {
        popularMovieFragmentPresenter = new PopularMovieFragmentPresenter();
    }

    @Override
    public void onAttach() {
        popularMovieFragmentPresenter.onAttach(this);
    }

    @Override
    public void onDetach() {
        popularMovieFragmentPresenter.onDetach();
        super.onDetach();
    }
}
