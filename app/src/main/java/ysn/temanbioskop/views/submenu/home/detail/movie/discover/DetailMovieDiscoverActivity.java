package ysn.temanbioskop.views.submenu.home.detail.movie.discover;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import ysn.temanbioskop.R;
import ysn.temanbioskop.config.tools.blur.BlurBuilder;
import ysn.temanbioskop.internal.model.bioskop.moviedb.discover.detail.DiscoverMovieDetailDb;

public class DetailMovieDiscoverActivity extends AppCompatActivity implements DetailMovieDiscoverView {

    private static final String TAG = "DetailMovieDiscoverTAG";
    @BindView(R.id.image_view_background_activity_detail_movie_discover)
    ImageView imageViewBackgroundActivityDetailMovieDiscover;
    @BindView(R.id.image_view_background_placeholder_activity_detail_movie_discover)
    ImageView imageViewBackgroundPlaceholderActivityDetailMovieDiscover;
    @BindView(R.id.image_view_poster_activity_detail_movie_discover)
    ImageView imageViewPosterActivityDetailMovieDiscover;
    @BindView(R.id.linear_layout_container_text_activity_detail_movie_discover)
    LinearLayout linearLayoutContainerTextActivityDetailMovieDiscover;
    @BindView(R.id.text_view_title_activity_detail_movie_discover)
    TextView textViewTitleActivityDetailMovieDiscover;
    @BindView(R.id.text_view_genre_activity_detail_movie_discover)
    TextView textViewGenreActivityDetailMovieDiscover;
    @BindView(R.id.text_view_homepage_activity_detail_movie_discover)
    TextView textViewHomepageActivityDetailMovieDiscover;
    @BindView(R.id.text_view_overview_activity_detail_movie_discover)
    TextView textViewOverviewActivityDetailMovieDiscover;
    @BindView(R.id.text_view_production_companies_activity_detail_movie_discover)
    TextView textViewProductionCompaniesActivityDetailMovieDiscover;
    @BindView(R.id.text_view_production_countries_activity_detail_movie_discover)
    TextView textViewProductionCountriesActivityDetailMovieDiscover;
    @BindView(R.id.text_view_release_date_activity_detail_movie_discover)
    TextView textViewReleaseDateActivityDetailMovieDiscover;
    @BindView(R.id.text_view_spoken_language_activity_detail_movie_discover)
    TextView textViewSpokenLanguageActivityDetailMovieDiscover;

    private DetailMovieDiscoverPresenter detailMovieDiscoverPresenter;
    private Bitmap bitmapPoster;
    private int heightContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie_discover);
        initPresenter();
        onAttach();
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    private void initPresenter() {
        detailMovieDiscoverPresenter = new DetailMovieDiscoverPresenter();
    }

    @Subscribe(sticky = true)
    public void onMessageEvent(Bitmap bitmap) {
        bitmapPoster = bitmap;
        imageViewPosterActivityDetailMovieDiscover.setImageBitmap(bitmapPoster);
        Bundle bundle = getIntent().getExtras();
        String idMovie = String.valueOf(bundle.getInt("movieId"));
        detailMovieDiscoverPresenter.requestData(idMovie);
    }

    @Override
    protected void onDestroy() {
        onDetach();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onAttach() {
        detailMovieDiscoverPresenter.onAttach(this);
    }

    @Override
    public void onDetach() {
        detailMovieDiscoverPresenter.onDetach();
    }

    @Override
    public void requestDataSuccess(final DiscoverMovieDetailDb discoverMovieDetailDb) {
        Observable<DiscoverMovieDetailDb> observableData = Observable.create(new Observable.OnSubscribe<DiscoverMovieDetailDb>() {
            @Override
            public void call(final Subscriber<? super DiscoverMovieDetailDb> subscriber) {
                subscriber.onNext(discoverMovieDetailDb);
            }
        });
        Subscriber<DiscoverMovieDetailDb> subscriberData = new Subscriber<DiscoverMovieDetailDb>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(DiscoverMovieDetailDb discoverMovieDetailDb) {
                //  text
                textViewTitleActivityDetailMovieDiscover.setText(discoverMovieDetailDb.getTitle());
                String genres = "";
                for(int a = 0; a < discoverMovieDetailDb.getGenres().size(); a++) {
                    genres += discoverMovieDetailDb.getGenres().get(a).getName()
                            + (a == discoverMovieDetailDb.getGenres().size() - 1 ? "" : "\n");
                }
                textViewGenreActivityDetailMovieDiscover.setText(genres);
                textViewHomepageActivityDetailMovieDiscover.setText(discoverMovieDetailDb.getHomepage());
                textViewOverviewActivityDetailMovieDiscover.setText(discoverMovieDetailDb.getOverview());
                String productCompanies = "";
                for(int a = 0; a < discoverMovieDetailDb.getProductionCompanies().size(); a++) {
                    productCompanies += discoverMovieDetailDb.getProductionCompanies().get(a).getName()
                            + (a == discoverMovieDetailDb.getProductionCompanies().size() - 1 ? "" : "\n");
                }
                textViewProductionCompaniesActivityDetailMovieDiscover.setText(productCompanies);
                String productCountries = "";
                for(int a = 0; a < discoverMovieDetailDb.getProductionCountries().size(); a++) {
                    productCountries += discoverMovieDetailDb.getProductionCountries().get(a).getName()
                            + (a == discoverMovieDetailDb.getProductionCountries().size() - 1 ? "" : "\n");
                }
                textViewProductionCountriesActivityDetailMovieDiscover.setText(productCountries);
                textViewReleaseDateActivityDetailMovieDiscover.setText(discoverMovieDetailDb.getReleaseDate());
                String spokenLanguage = "";
                for(int a = 0; a < discoverMovieDetailDb.getSpokenLanguages().size(); a++) {
                    spokenLanguage += discoverMovieDetailDb.getSpokenLanguages().get(a).getName()
                            + (a == discoverMovieDetailDb.getSpokenLanguages().size() - 1 ? "" : "\n");
                }
                textViewSpokenLanguageActivityDetailMovieDiscover.setText(spokenLanguage);

                //  poster path and background
                linearLayoutContainerTextActivityDetailMovieDiscover.post(new Runnable() {
                    @Override
                    public void run() {
                        heightContainer = linearLayoutContainerTextActivityDetailMovieDiscover.getHeight();
                        bitmapPoster = BlurBuilder.blurRenderScript(bitmapPoster, 25, DetailMovieDiscoverActivity.this, heightContainer);
                        imageViewBackgroundActivityDetailMovieDiscover.setImageBitmap(bitmapPoster);
                        setBitmapBackgroundPlaceholder(heightContainer);
                    }
                });

            }
        };
        observableData.subscribeOn(Schedulers.newThread());
        observableData.observeOn(AndroidSchedulers.mainThread());
        observableData.subscribe(subscriberData);
    }

    @Override
    public void requestDataFailure() {
        Snackbar.make(findViewById(android.R.id.content), R.string.str_internet_problem, Snackbar.LENGTH_LONG)
                .show();
        linearLayoutContainerTextActivityDetailMovieDiscover.post(new Runnable() {
            @Override
            public void run() {
                heightContainer = linearLayoutContainerTextActivityDetailMovieDiscover.getHeight();
                bitmapPoster = ((BitmapDrawable) getResources().getDrawable(R.drawable.image_not_found_placeholder)).getBitmap();
                bitmapPoster = BlurBuilder.blurRenderScript(bitmapPoster, 25, DetailMovieDiscoverActivity.this, heightContainer);
                imageViewBackgroundActivityDetailMovieDiscover.setImageBitmap(bitmapPoster);
                setBitmapBackgroundPlaceholder(heightContainer);
            }
        });
    }

    private void setBitmapBackgroundPlaceholder(int heightContainer) {
        Bitmap bitmapBackgroundPlaceholder = ((BitmapDrawable) getResources().getDrawable(R.drawable.black)).getBitmap();
        bitmapBackgroundPlaceholder = Bitmap.createScaledBitmap(bitmapBackgroundPlaceholder, bitmapPoster.getWidth(), heightContainer, false);
        imageViewBackgroundPlaceholderActivityDetailMovieDiscover.setImageBitmap(bitmapBackgroundPlaceholder);
    }
}
