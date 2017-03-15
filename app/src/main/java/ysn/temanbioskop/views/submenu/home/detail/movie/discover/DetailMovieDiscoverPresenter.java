package ysn.temanbioskop.views.submenu.home.detail.movie.discover;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ysn.temanbioskop.api.MovieDbApiService;
import ysn.temanbioskop.internal.model.bioskop.moviedb.discover.detail.DiscoverMovieDetailDb;
import ysn.temanbioskop.views.base.Presenter;

/**
 * Created by root on 13/03/17.
 */

public class DetailMovieDiscoverPresenter implements Presenter<DetailMovieDiscoverView> {

    private static final String TAG = "DetailMoviePresenter";
    DetailMovieDiscoverView detailMovieDiscoverView;
    Retrofit retrofit;

    @Override
    public void onAttach(DetailMovieDiscoverView view) {
        detailMovieDiscoverView = view;
    }

    @Override
    public void onDetach() {
        detailMovieDiscoverView = null;
    }

    public void requestData(String movieId) {
        initRetrofit();
        MovieDbApiService movieDbApiService = retrofit.create(MovieDbApiService.class);
        Call<DiscoverMovieDetailDb> resultCallDiscoverMovieDetail = movieDbApiService.getDiscoverMovieDetail(movieId, MovieDbApiService.apiKey);
        resultCallDiscoverMovieDetail.enqueue(new Callback<DiscoverMovieDetailDb>() {
            @Override
            public void onResponse(Call<DiscoverMovieDetailDb> call, Response<DiscoverMovieDetailDb> response) {
                DiscoverMovieDetailDb discoverMovieDetailDb = response.body();
                detailMovieDiscoverView.requestDataSuccess(discoverMovieDetailDb);
            }

            @Override
            public void onFailure(Call<DiscoverMovieDetailDb> call, Throwable t) {
                t.printStackTrace();
                detailMovieDiscoverView.requestDataFailure();
            }
        });

    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MovieDbApiService.baseApiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
