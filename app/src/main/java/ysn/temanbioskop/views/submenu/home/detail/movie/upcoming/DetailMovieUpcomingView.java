package ysn.temanbioskop.views.submenu.home.detail.movie.upcoming;

import ysn.temanbioskop.internal.model.bioskop.moviedb.upcoming.detail.UpcomingMovieDetailDb;
import ysn.temanbioskop.views.base.View;

/**
 * Created by root on 15/03/17.
 */

public interface DetailMovieUpcomingView extends View {

    public void requestDataSuccess(UpcomingMovieDetailDb upcomingMovieDetailDb);

    public void requestDataFailure();

}
