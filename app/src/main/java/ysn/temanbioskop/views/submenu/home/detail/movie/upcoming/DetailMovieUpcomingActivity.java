package ysn.temanbioskop.views.submenu.home.detail.movie.upcoming;

import android.app.usage.UsageEvents;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import ysn.temanbioskop.internal.model.bioskop.moviedb.upcoming.detail.UpcomingMovieDetailDb;

public class DetailMovieUpcomingActivity extends AppCompatActivity implements DetailMovieUpcomingView {

    private final String TAG = "DetailMovieUpcomingTAG";
    @BindView(R.id.image_view_background_activity_detail_movie_upcoming)
    ImageView imageViewBackgroundActivityDetailMovieUpcoming;
    @BindView(R.id.image_view_background_placeholder_activity_detail_movie_upcoming)
    ImageView imageViewBackgroundPlaceholderActivityDetailMovieUpcoming;
    @BindView(R.id.image_view_poster_activity_detail_movie_upcoming)
    ImageView imageViewPosterActivityDetailMovieUpcoming;
    @BindView(R.id.linear_layout_container_text_activity_detail_movie_upcoming)
    LinearLayout linearLayoutContainerTextActivityDetailMovieUpcoming;
    @BindView(R.id.text_view_title_activity_detail_movie_upcoming)
    TextView textViewTitleActivityDetailMovieUpcoming;
    @BindView(R.id.text_view_genre_activity_detail_movie_upcoming)
    TextView textViewGenreActivityDetailMovieUpcoming;
    @BindView(R.id.text_view_homepage_activity_detail_movie_upcoming)
    TextView textViewHomepageActivityDetailMovieUpcoming;
    @BindView(R.id.text_view_overview_activity_detail_movie_upcoming)
    TextView textViewOverviewActivityDetailMovieUpcoming;
    @BindView(R.id.text_view_production_companies_activity_detail_movie_upcoming)
    TextView textViewProductionCompaniesActivityDetailMovieUpcoming;
    @BindView(R.id.text_view_production_countries_activity_detail_movie_upcoming)
    TextView textViewProductionCountriesActivityDetailMovieUpcoming;
    @BindView(R.id.text_view_release_date_activity_detail_movie_upcoming)
    TextView textViewReleaseDateActivityDetailMovieUpcoming;
    @BindView(R.id.text_view_spoken_language_activity_detail_movie_upcoming)
    TextView textViewSpokenLanguageActivityDetailMovieUpcoming;

    private DetailMovieUpcomingPresenter detailMovieUpcomingPresenter;
    private Bitmap bitmapPoster;
    private int heightContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie_upcoming);
        initPresenter();
        onAttach();
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

    }

    private void initPresenter() {
        detailMovieUpcomingPresenter = new DetailMovieUpcomingPresenter();
    }

    @Subscribe(sticky = true)
    public void onMessageEvent(Bitmap bitmap) {
        bitmapPoster = bitmap;
        imageViewPosterActivityDetailMovieUpcoming.setImageBitmap(bitmapPoster);
        Bundle bundle = getIntent().getExtras();
        String idMovie = String.valueOf(bundle.getInt("movieId"));
        detailMovieUpcomingPresenter.requestData(idMovie);
    }

    @Override
    protected void onDestroy() {
        onDetach();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onAttach() {
        detailMovieUpcomingPresenter.onAttach(this);
    }

    @Override
    public void onDetach() {
        detailMovieUpcomingPresenter.onDetach();
    }

    @Override
    public void requestDataSuccess(final UpcomingMovieDetailDb upcomingMovieDetailDb) {
        Observable<UpcomingMovieDetailDb> observableData = Observable.create(new Observable.OnSubscribe<UpcomingMovieDetailDb>() {
            @Override
            public void call(Subscriber<? super UpcomingMovieDetailDb> subscriber) {
                subscriber.onNext(upcomingMovieDetailDb);
            }
        });
        Subscriber<UpcomingMovieDetailDb> subscriberData = new Subscriber<UpcomingMovieDetailDb>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(UpcomingMovieDetailDb upcomingMovieDetailDb) {
                //  text
                textViewTitleActivityDetailMovieUpcoming.setText(
                        upcomingMovieDetailDb.getTitle().isEmpty() ? "-" : upcomingMovieDetailDb.getTitle()
                );
                String genres = "";
                for(int a = 0; a < upcomingMovieDetailDb.getGenres().size(); a++) {
                    genres += (upcomingMovieDetailDb.getGenres().get(a).getName())
                            + (a == upcomingMovieDetailDb.getGenres().size() - 1 ? "" : "\n");
                }
                textViewGenreActivityDetailMovieUpcoming.setText(genres.isEmpty() ? "" : genres);
                textViewHomepageActivityDetailMovieUpcoming.setText(
                        upcomingMovieDetailDb.getHomepage().isEmpty() ? "-" : upcomingMovieDetailDb.getHomepage()
                );
                textViewOverviewActivityDetailMovieUpcoming.setText(
                        upcomingMovieDetailDb.getOverview().isEmpty() ? "-" : upcomingMovieDetailDb.getOverview()
                );
                String productionCompanies = "";
                for(int a = 0; a < upcomingMovieDetailDb.getProductionCompanies().size(); a++) {
                    productionCompanies += upcomingMovieDetailDb.getProductionCompanies().get(a).getName()
                            + (a == upcomingMovieDetailDb.getProductionCompanies().size() - 1 ? "" : "\n");
                }
                textViewProductionCompaniesActivityDetailMovieUpcoming.setText(
                        productionCompanies.isEmpty() ? "-" : productionCompanies
                );
                String productionCountries = "";
                for(int a = 0; a < upcomingMovieDetailDb.getProductionCountries().size(); a++) {
                    productionCountries += upcomingMovieDetailDb.getProductionCountries().get(a).getName()
                            + (a == upcomingMovieDetailDb.getProductionCountries().size() - 1 ? "" : "\n");
                }
                textViewProductionCountriesActivityDetailMovieUpcoming.setText(
                        productionCountries.isEmpty() ? "-" : productionCountries
                );
                textViewReleaseDateActivityDetailMovieUpcoming.setText(
                        upcomingMovieDetailDb.getReleaseDate().isEmpty() ? "-" : upcomingMovieDetailDb.getReleaseDate()
                );
                String spokenLanguage = "";
                for(int a = 0; a < upcomingMovieDetailDb.getSpokenLanguages().size(); a++) {
                    spokenLanguage += upcomingMovieDetailDb.getSpokenLanguages().get(a).getName()
                            + (a == upcomingMovieDetailDb.getSpokenLanguages().size() - 1 ? "" : "\n");
                }
                textViewSpokenLanguageActivityDetailMovieUpcoming.setText(
                        spokenLanguage.isEmpty() ? "-" : spokenLanguage
                );

                //  poster path and background
                linearLayoutContainerTextActivityDetailMovieUpcoming.post(new Runnable() {
                    @Override
                    public void run() {
                        heightContainer = linearLayoutContainerTextActivityDetailMovieUpcoming.getHeight();
                        bitmapPoster = BlurBuilder.blurRenderScript(bitmapPoster, 25, DetailMovieUpcomingActivity.this, heightContainer);
                        imageViewBackgroundActivityDetailMovieUpcoming.setImageBitmap(bitmapPoster);
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
        linearLayoutContainerTextActivityDetailMovieUpcoming.post(new Runnable() {
            @Override
            public void run() {
                heightContainer = linearLayoutContainerTextActivityDetailMovieUpcoming.getHeight();
                bitmapPoster = ((BitmapDrawable) getResources().getDrawable(R.drawable.image_not_found_placeholder)).getBitmap();
                bitmapPoster = BlurBuilder.blurRenderScript(bitmapPoster, 25, DetailMovieUpcomingActivity.this, heightContainer);
                imageViewBackgroundActivityDetailMovieUpcoming.setImageBitmap(bitmapPoster);
                setBitmapBackgroundPlaceholder(heightContainer);
            }
        });

    }

    private void setBitmapBackgroundPlaceholder(int heightContainer) {
        Bitmap bitmapBackgroundPlaceholder = ((BitmapDrawable) getResources().getDrawable(R.drawable.black)).getBitmap();
        bitmapBackgroundPlaceholder = Bitmap.createScaledBitmap(bitmapBackgroundPlaceholder, bitmapPoster.getWidth(), heightContainer, false);
        imageViewBackgroundPlaceholderActivityDetailMovieUpcoming.setImageBitmap(bitmapBackgroundPlaceholder);
    }

}
