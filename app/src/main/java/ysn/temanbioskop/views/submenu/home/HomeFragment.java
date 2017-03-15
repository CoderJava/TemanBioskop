package ysn.temanbioskop.views.submenu.home;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.greenrobot.eventbus.EventBus;

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
import ysn.temanbioskop.api.MovieDbApiService;
import ysn.temanbioskop.databinding.FragmentHomeBinding;
import ysn.temanbioskop.internal.model.bioskop.moviedb.discover.DiscoverMovieDb;
import ysn.temanbioskop.internal.model.bioskop.moviedb.upcoming.UpcomingMovieDb;
import ysn.temanbioskop.views.submenu.home.check.movie.today.CheckMovieTodayActivity;
import ysn.temanbioskop.views.submenu.home.detail.movie.discover.DetailMovieDiscoverActivity;
import ysn.temanbioskop.views.submenu.home.detail.movie.upcoming.DetailMovieUpcomingActivity;
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
    @BindView(R.id.relative_layout_header_upcoming_movies_fragment_home)
    RelativeLayout relativeLayoutUpcomingMovies;
    @BindView(R.id.linear_layout_poster_upcoming_movies_fragment_home)
    LinearLayout linearLayoutPosterUpcomingMovies;
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
    private DiscoverMovieDb discoverMovieDb;
    private UpcomingMovieDb upcomingMovieDb;
    private int idMovieDiscover1 = 0;
    private int idMovieDiscover2 = 0;
    private int idMovieDiscover3 = 0;
    private Bitmap bitmap1PosterDiscoverMovie;
    private Bitmap bitmap2PosterDiscoverMovie;
    private Bitmap bitmap3PosterDiscoverMovie;
    private int idMovieUpcoming1;
    private int idMovieUpcoming2;
    private int idMovieUpcoming3;
    private Bitmap bitmap1PosterUpcoming;
    private Bitmap bitmap2PosterUpcoming;
    private Bitmap bitmap3PosterUpcoming;

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
        homeFragmentPresenter.loadDataDiscoverMovies();
        homeFragmentPresenter.loadDataUpcomingMovies();
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

    @OnClick({
            R.id.relative_layout_check_list_movie_today_fragment_home,
            R.id.image_view_1_poster_discover_movie_fragment_home,
            R.id.image_view_2_poster_discover_movie_fragment_home,
            R.id.image_view_3_poster_discover_movie_fragment_home,
            R.id.image_view_1_poster_upcoming_movie_fragment_home,
            R.id.image_view_2_poster_upcoming_movie_fragment_home,
            R.id.image_view_3_poster_upcoming_movie_fragment_home
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_layout_check_list_movie_today_fragment_home:
                Intent intent = new Intent(fragmentActivity, CheckMovieTodayActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                break;
            case R.id.image_view_1_poster_discover_movie_fragment_home:
                if (idMovieDiscover1 != 0 && bitmap1PosterDiscoverMovie != null) {
                    EventBus.getDefault().postSticky(bitmap1PosterDiscoverMovie);
                    intent = new Intent(getActivity(), DetailMovieDiscoverActivity.class);
                    intent.putExtra("movieId", idMovieDiscover1);
                    startActivity(intent);
                }
                break;
            case R.id.image_view_2_poster_discover_movie_fragment_home:
                if(idMovieDiscover2 != 0 && bitmap2PosterDiscoverMovie != null) {
                    EventBus.getDefault().postSticky(bitmap2PosterDiscoverMovie);
                    intent = new Intent(getActivity(), DetailMovieDiscoverActivity.class);
                    intent.putExtra("movieId", idMovieDiscover2);
                    startActivity(intent);
                }
                break;
            case R.id.image_view_3_poster_discover_movie_fragment_home:
                if(idMovieDiscover3 != 0 && bitmap3PosterDiscoverMovie != null) {
                    EventBus.getDefault().postSticky(bitmap3PosterDiscoverMovie);
                    intent = new Intent(getActivity(), DetailMovieDiscoverActivity.class);
                    intent.putExtra("movieId", idMovieDiscover3);
                    startActivity(intent);
                }
                break;
            case R.id.image_view_1_poster_upcoming_movie_fragment_home:
                if (idMovieUpcoming1 != 0 && bitmap1PosterUpcoming != null) {
                    EventBus.getDefault().postSticky(bitmap1PosterUpcoming);
                    intent = new Intent(getActivity(), DetailMovieUpcomingActivity.class);
                    intent.putExtra("movieId", idMovieUpcoming1);
                    startActivity(intent);
                }
                break;
            case R.id.image_view_2_poster_upcoming_movie_fragment_home:
                if (idMovieUpcoming2 != 0 && bitmap2PosterUpcoming != null) {
                    EventBus.getDefault().postSticky(bitmap2PosterUpcoming);
                    intent = new Intent(getActivity(), DetailMovieUpcomingActivity.class);
                    intent.putExtra("movieId", idMovieUpcoming2);
                    startActivity(intent);
                }
                break;
            case R.id.image_view_3_poster_upcoming_movie_fragment_home:
                if (idMovieUpcoming3 != 0 && bitmap3PosterUpcoming != null) {
                    EventBus.getDefault().postSticky(bitmap3PosterUpcoming);
                    intent = new Intent(getActivity(), DetailMovieUpcomingActivity.class);
                    intent.putExtra("movieId", idMovieUpcoming3);
                    startActivity(intent);
                }
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
        timer.cancel();
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
    public void onLoadDataDiscoverMovies() {
        initRetrofit(MovieDbApiService.baseApiUrl);
        MovieDbApiService movieDbApiService = retrofit.create(MovieDbApiService.class);
        final Call<DiscoverMovieDb> resultCallDiscoverMovie = movieDbApiService.getDiscoverMovie(MovieDbApiService.apiKey, "id", "1");
        resultCallDiscoverMovie.enqueue(new Callback<DiscoverMovieDb>() {
            @Override
            public void onResponse(Call<DiscoverMovieDb> call, Response<DiscoverMovieDb> response) {
                discoverMovieDb = response.body();
                final List<ysn.temanbioskop.internal.model.bioskop.moviedb.discover.Result> listResult = discoverMovieDb.getResults();
                idMovieDiscover1 = listResult.get(0).getId();
                idMovieDiscover2 = listResult.get(1).getId();
                idMovieDiscover3 = listResult.get(2).getId();

                //  image view 1 discover movie
                String posterPath = MovieDbApiService.baseImageUrl600 + "" + listResult.get(0).getPosterPath();
                Glide.with(fragmentActivity)
                        .load(posterPath)
                        .asBitmap()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                bitmap1PosterDiscoverMovie = resource;
                            }
                        });
                Glide.with(fragmentActivity)
                        .load(posterPath)
                        .error(R.drawable.image_not_found_placeholder)
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView1PosterDiscoverMovie);

                //  image view 2 discover movie
                posterPath = MovieDbApiService.baseImageUrl600 + "" + listResult.get(1).getPosterPath();
                Glide.with(fragmentActivity)
                        .load(posterPath)
                        .asBitmap()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                bitmap2PosterDiscoverMovie = resource;
                            }
                        });
                Glide.with(fragmentActivity)
                        .load(posterPath)
                        .error(R.drawable.image_not_found_placeholder)
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView2PosterDiscoverMovie);

                //  image view 3 discover movie
                posterPath = MovieDbApiService.baseImageUrl600 + "" + listResult.get(2).getPosterPath();
                Glide.with(fragmentActivity)
                        .load(posterPath)
                        .asBitmap()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                bitmap3PosterDiscoverMovie = resource;
                            }
                        });
                Glide.with(fragmentActivity)
                        .load(posterPath)
                        .error(R.drawable.image_not_found_placeholder)
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView3PosterDiscoverMovie);

            }

            @Override
            public void onFailure(Call<DiscoverMovieDb> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "onFailure loadDataDiscoverMovies");
                imageView1PosterDiscoverMovie.setImageResource(R.drawable.image_not_found_placeholder);
                imageView2PosterDiscoverMovie.setImageResource(R.drawable.image_not_found_placeholder);
                imageView3PosterDiscoverMovie.setImageResource(R.drawable.image_not_found_placeholder);
            }
        });
    }

    @Override
    public void onLoadDataUpcomingMovies() {
        initRetrofit(MovieDbApiService.baseApiUrl);
        MovieDbApiService movieDbApiService = retrofit.create(MovieDbApiService.class);
        Call<UpcomingMovieDb> resultCallUpcomingMovie = movieDbApiService.getUpcomingMovie(MovieDbApiService.apiKey, "id");
        resultCallUpcomingMovie.enqueue(new Callback<UpcomingMovieDb>() {
            @Override
            public void onResponse(Call<UpcomingMovieDb> call, Response<UpcomingMovieDb> response) {
                upcomingMovieDb = response.body();
                List<ysn.temanbioskop.internal.model.bioskop.moviedb.upcoming.Result> listResult = upcomingMovieDb.getResults();
                String posterPath;
                if (listResult.size() >= 1) {
                    posterPath = MovieDbApiService.baseImageUrl600 + "" + listResult.get(0).getPosterPath();
                    idMovieUpcoming1 = listResult.get(0).getId();
                    Glide.with(fragmentActivity)
                            .load(posterPath)
                            .asBitmap()
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    bitmap1PosterUpcoming = resource;
                                }
                            });
                    Glide.with(fragmentActivity)
                            .load(posterPath)
                            .placeholder(R.drawable.image_coming_soon)
                            .thumbnail(0.5f)
                            .crossFade()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(imageView1PosterUpcomingMovie);

                }

                if (listResult.size() >= 2) {
                    posterPath = MovieDbApiService.baseImageUrl600 + "" + listResult.get(1).getPosterPath();
                    idMovieUpcoming2 = listResult.get(1).getId();
                    Glide.with(fragmentActivity)
                            .load(posterPath)
                            .asBitmap()
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    bitmap2PosterUpcoming = resource;
                                }
                            });
                    Glide.with(fragmentActivity)
                            .load(posterPath)
                            .placeholder(R.drawable.image_coming_soon)
                            .thumbnail(0.5f)
                            .crossFade()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(imageView2PosterUpcomingMovie);
                }

                if (listResult.size() >= 3) {
                    posterPath = MovieDbApiService.baseImageUrl600 + "" + listResult.get(2).getPosterPath();
                    idMovieUpcoming3 = listResult.get(2).getId();
                    Glide.with(fragmentActivity)
                            .load(posterPath)
                            .asBitmap()
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    bitmap3PosterUpcoming = resource;
                                }
                            });
                    Glide.with(fragmentActivity)
                            .load(posterPath)
                            .placeholder(R.drawable.image_coming_soon)
                            .thumbnail(0.5f)
                            .crossFade()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(imageView3PosterUpcomingMovie);
                }

                if (listResult.size() == 0) {
                    relativeLayoutUpcomingMovies.setVisibility(View.GONE);
                    linearLayoutPosterUpcomingMovies.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<UpcomingMovieDb> call, Throwable t) {
                t.printStackTrace();
                imageView1PosterUpcomingMovie.setImageResource(R.drawable.image_not_found_placeholder);
                imageView2PosterUpcomingMovie.setImageResource(R.drawable.image_not_found_placeholder);
                imageView3PosterUpcomingMovie.setImageResource(R.drawable.image_not_found_placeholder);
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
