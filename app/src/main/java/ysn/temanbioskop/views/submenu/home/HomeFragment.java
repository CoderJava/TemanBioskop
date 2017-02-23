package ysn.temanbioskop.views.submenu.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ysn.temanbioskop.R;
import ysn.temanbioskop.databinding.FragmentHomeBinding;
import ysn.temanbioskop.views.submenu.home.check.movie.today.CheckMovieTodayActivity;
import ysn.temanbioskop.views.submenu.home.slideshow.adapter.SlideShowFragmentPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeFragmentView {

    private static final String TAG = "HomeFragmentTAG";
    private HomeFragmentPresenter homeFragmentPresenter;
    private View view;
    @BindView(R.id.view_pager_slide_show_fragment_home)
    ViewPager viewPagerSlideShow;

    FragmentHomeBinding fragmentHomeBinding;
    FragmentActivity fragmentActivity;
    Timer timer;

    int page = 0;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initPresenter();
        onAttach();
        ButterKnife.bind(this, view);
        fragmentActivity = getActivity();
        homeFragmentPresenter.setSlideShow();
        return view;
    }

    private void initPresenter() {
        homeFragmentPresenter = new HomeFragmentPresenter();
    }

    @OnClick({R.id.relative_layout_check_list_movie_today_fragment_home})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_layout_check_list_movie_today_fragment_home:
                Intent intent = new Intent(fragmentActivity, CheckMovieTodayActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        homeFragmentPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onAttach() {
        homeFragmentPresenter.onAttach(this);
    }

    @Override
    public void onDetach() {
        homeFragmentPresenter.onDetach();
        super.onDetach();
    }

    @Override
    public void onSetSlideShow() {
        viewPagerSlideShow.setAdapter(new SlideShowFragmentPagerAdapter(getFragmentManager(), new int[]{
                R.drawable.pict1_backdrops, R.drawable.pict2_backdrops, R.drawable.pict3_backdrops, R.drawable.pict4_backdrops, R.drawable.pict5_backdrops
        }));
        if (timer == null) {
            homeFragmentPresenter.setPageSwitcher(5);
        }
    }

    @Override
    public void onSetPageSwitcher(int seconds) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000);
        Log.d(TAG, "setPageSwitcher");
    }

    @Override
    public void onSetTimerTask() {
        if (page > 4) {
            page = 0;
            viewPagerSlideShow.setCurrentItem(page
            );
            /*timer.cancel();*/
        } else {
            viewPagerSlideShow.setCurrentItem(page++);
        }
    }

    private class RemindTask extends TimerTask {

        @Override
        public void run() {
            fragmentActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    homeFragmentPresenter.setTimerTask();
                }
            });
        }
    }

}
