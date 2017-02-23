package ysn.temanbioskop.views.submenu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ysn.temanbioskop.views.submenu.favorite.movie.FavoriteMovieFragment;
import ysn.temanbioskop.views.submenu.home.HomeFragment;
import ysn.temanbioskop.views.submenu.popular.movie.PopularMovieFragment;

/**
 * Created by root on 02/02/17.
 */

public class TabFragmentPagerAdapter extends FragmentStatePagerAdapter {

    String[] title;

    public TabFragmentPagerAdapter(FragmentManager fm, String[] title) {
        super(fm);
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new FavoriteMovieFragment();
                break;
            case 2:
                fragment = new PopularMovieFragment();
                break;
            default:
                fragment = null;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return title.length;
    }

}
