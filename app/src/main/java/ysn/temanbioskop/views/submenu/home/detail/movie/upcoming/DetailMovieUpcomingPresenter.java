package ysn.temanbioskop.views.submenu.home.detail.movie.upcoming;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ysn.temanbioskop.api.MovieDbApiService;
import ysn.temanbioskop.internal.model.bioskop.moviedb.upcoming.detail.UpcomingMovieDetailDb;
import ysn.temanbioskop.views.base.Presenter;

/**
 * Created by root on 15/03/17.
 */

public class DetailMovieUpcomingPresenter implements Presenter<DetailMovieUpcomingView> {

    private DetailMovieUpcomingView detailMovieUpcomingView;
    private Retrofit retrofit;

    @Override
    public void onAttach(DetailMovieUpcomingView view) {
        detailMovieUpcomingView = view;
    }

    @Override
    public void onDetach() {
        detailMovieUpcomingView = null;
    }

    public void requestData(String movieId) {
        initRetrofit();
        MovieDbApiService movieDbApiService = retrofit.create(MovieDbApiService.class);
        Call<UpcomingMovieDetailDb> resultCallUpcomingMovieDetail = movieDbApiService.getUpcomingMovieDetail(movieId, MovieDbApiService.apiKey);
        resultCallUpcomingMovieDetail.enqueue(new Callback<UpcomingMovieDetailDb>() {
            @Override
            public void onResponse(Call<UpcomingMovieDetailDb> call, Response<UpcomingMovieDetailDb> response) {
                UpcomingMovieDetailDb upcomingMovieDetailDb = response.body();
                detailMovieUpcomingView.requestDataSuccess(upcomingMovieDetailDb);
            }

            @Override
            public void onFailure(Call<UpcomingMovieDetailDb> call, Throwable t) {
                t.printStackTrace();
                detailMovieUpcomingView.requestDataFailure();
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
