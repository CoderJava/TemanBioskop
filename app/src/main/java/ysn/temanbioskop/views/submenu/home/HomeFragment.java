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
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ysn.temanbioskop.R;
import ysn.temanbioskop.api.JadwalBioskopApiService;
import ysn.temanbioskop.api.MovieDbApiService;
import ysn.temanbioskop.databinding.FragmentHomeBinding;
import ysn.temanbioskop.internal.model.bioskop.moviedb.search.Result;
import ysn.temanbioskop.internal.model.bioskop.moviedb.search.SearchMovieDb;
import ysn.temanbioskop.views.submenu.home.check.movie.today.CheckMovieTodayActivity;
import ysn.temanbioskop.views.submenu.home.slideshow.adapter.SlideShowFragmentPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeFragmentView {

    private static final String TAG = "HomeFragmentTAG";
    private View view;
    @BindView(R.id.view_pager_slide_show_fragment_home)
    ViewPager viewPagerSlideShow;
    @BindView(R.id.image_view_1_poster_discover_movie_fragment_home)
    ImageView imageView1PosterDiscoverMovie;
    @BindView(R.id.image_view_2_poster_discover_movie_fragment_home)
    ImageView imageView2PosterDiscoverMovie;
    @BindView(R.id.image_view_3_poster_discover_movie_fragment_home)
    ImageView imageView3PosterDiscoverMovie;
    @BindView(R.id.image_view_1_poster_upcoming_movie_fragment_home)
    ImageView imageView1PosterUpcomingMovie;
    @BindView(R.id.image_view_2_poster_upcoming_movie_fragment_home)
    ImageView imageView2PosterUpcomingMovie;
    @BindView(R.id.image_view_3_poster_upcoming_movie_fragment_home)
    ImageView imageView3PosterUpcomingMovie;

    private HomeFragmentPresenter homeFragmentPresenter;
    private FragmentHomeBinding fragmentHomeBinding;
    private FragmentActivity fragmentActivity;
    private Timer timer;
    private Retrofit retrofit;
    private int page = 0;

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
        homeFragmentPresenter.loadDataDiscover();
        return view;
    }

    private void initRetrofit(String baseApiUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseApiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
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

    @Override
    public void onLoadDataDiscover() {
        initRetrofit(MovieDbApiService.baseApiUrl);
        MovieDbApiService movieDbApiService = retrofit.create(MovieDbApiService.class);
        Call<SearchMovieDb> resultCallDiscoverMovie = movieDbApiService.getDiscoverMovie(MovieDbApiService.apiKey, "id", "1");
        resultCallDiscoverMovie.enqueue(new Callback<SearchMovieDb>() {
            @Override
            public void onResponse(Call<SearchMovieDb> call, Response<SearchMovieDb> response) {
                SearchMovieDb searchMovieDb = response.body();
                List<Result> listResult = searchMovieDb.getResults();
                Log.d(TAG, "onResponse loadDataDiscoverMovies");

                //  image view 1 discover movie
                String posterPath = MovieDbApiService.baseImageUrl + "" + listResult.get(0).getPosterPath();
                Glide.with(fragmentActivity)
                        .load(posterPath)
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView1PosterDiscoverMovie);

                //  image view 2 discover movie
                posterPath = MovieDbApiService.baseImageUrl + "" + listResult.get(1).getPosterPath();
                Glide.with(fragmentActivity)
                        .load(posterPath)
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView2PosterDiscoverMovie);

                //  image view 3 discover movie
                posterPath = MovieDbApiService.baseImageUrl + "" + listResult.get(2).getPosterPath();
                Glide.with(fragmentActivity)
                        .load(posterPath)
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView3PosterDiscoverMovie);
            }

            @Override
            public void onFailure(Call<SearchMovieDb> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "onFailure loadDataDiscoverMovies");
                imageView1PosterDiscoverMovie.setImageResource(R.drawable.image_not_found_placeholder);
                imageView2PosterDiscoverMovie.setImageResource(R.drawable.image_not_found_placeholder);
                imageView3PosterDiscoverMovie.setImageResource(R.drawable.image_not_found_placeholder);
            }
        });
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
