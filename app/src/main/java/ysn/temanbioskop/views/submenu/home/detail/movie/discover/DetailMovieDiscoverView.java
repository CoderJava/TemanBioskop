package ysn.temanbioskop.views.submenu.home.detail.movie.discover;

import ysn.temanbioskop.internal.model.bioskop.moviedb.discover.DiscoverMovieDb;
import ysn.temanbioskop.internal.model.bioskop.moviedb.discover.detail.DiscoverMovieDetailDb;
import ysn.temanbioskop.views.base.View;

/**
 * Created by root on 13/03/17.
 */

public interface DetailMovieDiscoverView extends View {

    public void requestDataSuccess(DiscoverMovieDetailDb discoverMovieDetailDb);

    public void requestDataFailure();

}
