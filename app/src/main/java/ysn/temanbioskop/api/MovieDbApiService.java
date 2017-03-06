package ysn.temanbioskop.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ysn.temanbioskop.internal.model.bioskop.moviedb.discover.DiscoverMovieDb;
import ysn.temanbioskop.internal.model.bioskop.moviedb.search.SearchMovieDb;
import ysn.temanbioskop.internal.model.bioskop.moviedb.upcoming.UpcomingMovieDb;

/**
 * Created by root on 23/02/17.
 */

public interface MovieDbApiService {

    public final String baseApiUrl = "https://api.themoviedb.org/3/";
    public final String baseImageUrl = "https://image.tmdb.org/t/p/w500";
    public final String apiKey = "fe71629f457c2466875babf7fbe5bb6c";

    /*http://api.themoviedb.org/3/search/movie?api_key=fe71629f457c2466875babf7fbe5bb6c&query=*/
    @GET("search/movie")
    Call<SearchMovieDb> getSearchMovieByName(
            @Query("api_key") String apiKey,
            @Query("query") String query
    );

    /*https://api.themoviedb.org/3/discover/movie?api_key=fe71629f457c2466875babf7fbe5bb6c&region=id&page=3*/
    @GET("discover/movie")
    Call<DiscoverMovieDb> getDiscoverMovie(
            @Query("api_key") String apiKey,
            @Query("region") String region,
            @Query("page") String page
    );

    /*https://api.themoviedb.org/3/movie/upcoming?api_key=fe71629f457c2466875babf7fbe5bb6c&region=id*/
    @GET("movie/upcoming")
    Call<UpcomingMovieDb> getUpcomingMovie(
            @Query("api_key") String apiKey,
            @Query("region") String region
    );

}
