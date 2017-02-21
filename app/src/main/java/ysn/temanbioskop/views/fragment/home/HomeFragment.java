package ysn.temanbioskop.views.fragment.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ysn.temanbioskop.R;
import ysn.temanbioskop.databinding.FragmentHomeBinding;
import ysn.temanbioskop.views.fragment.home.check_movie_today.CheckMovieTodayActivity;
import ysn.temanbioskop.views.fragment.home.slide_show.adapter.SlideShowFragmentPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragmentTAG";
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
        /*fragmentHomeBinding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_home);*/
        ButterKnife.bind(this, view);
        fragmentActivity = getActivity();
        setSlideShow();
        Log.d(TAG, "onCreateView");
        return view;
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

    private void setSlideShow() {
        viewPagerSlideShow.setAdapter(new SlideShowFragmentPagerAdapter(getFragmentManager(), new int[]{
                R.drawable.pict1_backdrops, R.drawable.pict2_backdrops, R.drawable.pict3_backdrops, R.drawable.pict4_backdrops, R.drawable.pict5_backdrops
        }));
        if (timer == null) {
            pageSwitcher(5);
        }
    }

    private void pageSwitcher(int seconds) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    class RemindTask extends TimerTask {

        @Override
        public void run() {
            fragmentActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (page > 4) {
                        page = 0;
                        viewPagerSlideShow.setCurrentItem(page++);
                        /*timer.cancel();*/
                    } else {
                        viewPagerSlideShow.setCurrentItem(page++);
                    }
                }
            });
        }
    }

}
