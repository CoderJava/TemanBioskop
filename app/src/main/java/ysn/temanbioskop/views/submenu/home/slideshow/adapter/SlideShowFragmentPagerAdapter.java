package ysn.temanbioskop.views.submenu.home.slideshow.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ysn.temanbioskop.views.submenu.home.slideshow.fragment.SlideShow1HomeFragment;
import ysn.temanbioskop.views.submenu.home.slideshow.fragment.SlideShow2HomeFragment;
import ysn.temanbioskop.views.submenu.home.slideshow.fragment.SlideShow3HomeFragment;
import ysn.temanbioskop.views.submenu.home.slideshow.fragment.SlideShow4HomeFragment;
import ysn.temanbioskop.views.submenu.home.slideshow.fragment.SlideShow5HomeFragment;

/**
 * Created by root on 13/02/17.
 */

public class SlideShowFragmentPagerAdapter extends FragmentStatePagerAdapter {

    int[] imgSrc;

    public SlideShowFragmentPagerAdapter(FragmentManager fm, int[] imgSrc) {
        super(fm);
        this.imgSrc = imgSrc;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SlideShow1HomeFragment slideShow1HomeFragment = new SlideShow1HomeFragment();
                slideShow1HomeFragment.imgSrc = imgSrc[position];
                return slideShow1HomeFragment;
            case 1:
                SlideShow2HomeFragment slideShow2HomeFragment = new SlideShow2HomeFragment();
                slideShow2HomeFragment.imgSrc = imgSrc[position];
                return slideShow2HomeFragment;
            case 2:
                SlideShow3HomeFragment slideShow3HomeFragment = new SlideShow3HomeFragment();
                slideShow3HomeFragment.imgSrc = imgSrc[position];
                return slideShow3HomeFragment;
            case 3:
                SlideShow4HomeFragment slideShow4HomeFragment = new SlideShow4HomeFragment();
                slideShow4HomeFragment.imgSrc = imgSrc[position];
                return slideShow4HomeFragment;
            case 4:
                SlideShow5HomeFragment slideShow5HomeFragment = new SlideShow5HomeFragment();
                slideShow5HomeFragment.imgSrc = imgSrc[position];
                return slideShow5HomeFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return imgSrc.length;
    }
}
