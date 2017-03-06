package ysn.temanbioskop.views.submenu.home.check.movie.today.result;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

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
import ysn.temanbioskop.config.tools.blur.BlurBuilder;
import ysn.temanbioskop.internal.model.bioskop.jadwal.DataJadwal;
import ysn.temanbioskop.internal.model.bioskop.moviedb.search.SearchMovieDb;
import ysn.temanbioskop.views.submenu.home.check.movie.today.result.adapter.AdapterResultCheckMovieToday;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultCheckMovieTodayFragment extends Fragment implements ResultCheckMovieTodayFragmentView {

    private static final String TAG = "ResultCheckMovieTAG";
    @BindView(R.id.image_view_background_fragment_result_check_movie_today)
    ImageView imageViewFragmentResultCheckMovieToday;
    @BindView(R.id.text_view_nama_kota_fragment_result_check_movie_today)
    TextView textViewNamaKotaFragmentResultCheckMovieToday;
    @BindView(R.id.text_view_tanggal_fragment_result_check_movie_today)
    TextView textViewTanggalFragmentResultCheckMovieToday;
    @BindView(R.id.loading_indicator_view_fragment_result_check_movie_today)
    AVLoadingIndicatorView loadingIndicatorViewFragmentResultCheckMovieToday;
    @BindView(R.id.recycler_view_content_fragment_result_check_movie_today)
    RecyclerView recyclerViewContentFragmentResultCheckMovieToday;
    @BindView(R.id.button_refresh_fragment_result_check_movie_today)
    Button buttonRefreshFragmentResultCheckMovieToday;

    private View view;
    private ResultCheckMovieTodayFragmentPresenter resultCheckMovieTodayFragmentPresenter;
    private Retrofit retrofit;
    private String idKota;
    private DataJadwal dataJadwal;
    private boolean isSuccessfully;

    public ResultCheckMovieTodayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_result_check_movie_today, container, false);
        initPresenter();
        initRetrofit(JadwalBioskopApiService.baseApiUrl);
        onAttach();
        ButterKnife.bind(this, view);
        loadComponent();
        resultCheckMovieTodayFragmentPresenter.setBackgroundBlur();
        return view;
    }

    private void initRetrofit(String baseApiUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseApiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void loadComponent() {
        Bundle args = getArguments();
        idKota = args.getString("idKota");
        String namaKota = args.getString("namaKota");
        textViewNamaKotaFragmentResultCheckMovieToday.setText(namaKota);

        recyclerViewContentFragmentResultCheckMovieToday.setLayoutManager(new LinearLayoutManager(getContext()));
        resultCheckMovieTodayFragmentPresenter.loadDataJadwal();
    }

    private void initPresenter() {
        resultCheckMovieTodayFragmentPresenter = new ResultCheckMovieTodayFragmentPresenter();
    }

    @OnClick({R.id.button_refresh_fragment_result_check_movie_today})
    public void onClick(Button button) {
        switch (button.getId()) {
            case R.id.button_refresh_fragment_result_check_movie_today:
                resultCheckMovieTodayFragmentPresenter.loadDataJadwal();
                buttonRefreshFragmentResultCheckMovieToday.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onAttach() {
        resultCheckMovieTodayFragmentPresenter.onAttach(this);
    }

    @Override
    public void onDetach() {
        resultCheckMovieTodayFragmentPresenter.onDetach();
        super.onDetach();
    }

    @Override
    public void onLoadDataJadwal() {
        Log.d(TAG, "idKota: " + idKota);
        loadingIndicatorViewFragmentResultCheckMovieToday.smoothToShow();
        JadwalBioskopApiService jadwalBioskopApiService = retrofit.create(JadwalBioskopApiService.class);
        Call<DataJadwal> resultCallDataJadwal = jadwalBioskopApiService.getDataJadwal(idKota, JadwalBioskopApiService.apiKey);
        resultCallDataJadwal.enqueue(new Callback<DataJadwal>() {
            @Override
            public void onResponse(Call<DataJadwal> call, Response<DataJadwal> response) {
                dataJadwal = response.body();
                resultCheckMovieTodayFragmentPresenter.loadPoster();
            }

            @Override
            public void onFailure(Call<DataJadwal> call, Throwable t) {
                t.printStackTrace();
                loadingIndicatorViewFragmentResultCheckMovieToday.smoothToHide();
                resultCheckMovieTodayFragmentPresenter.showConnectionError();
            }
        });
    }

    @Override
    public void onLoadPoster() {
        initRetrofit(MovieDbApiService.baseApiUrl);
        MovieDbApiService movieDbApiService = retrofit.create(MovieDbApiService.class);
        for(int a = 0; a < dataJadwal.getData().size(); a++) {
            final int b = a;
            isSuccessfully = true;
            final String query = dataJadwal.getData().get(a).getMovie().replaceAll(" ", "+".replaceAll(":", ""));
            final Call<SearchMovieDb> resultCallSearchMovieByName = movieDbApiService.getSearchMovieByName(MovieDbApiService.apiKey, query);
            resultCallSearchMovieByName.enqueue(new Callback<SearchMovieDb>() {
                @Override
                public void onResponse(Call<SearchMovieDb> call, Response<SearchMovieDb> response) {
                    /*try {
                        String searchMovieByNameJson = response.body().string();
                        Log.d(TAG, "searchMovieByNameJson(" + query + "): " + searchMovieByNameJson);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                    loadingIndicatorViewFragmentResultCheckMovieToday.smoothToHide();
                    SearchMovieDb searchMovieDb = response.body();
                    Log.d(TAG, "searchMovieDb: " + searchMovieDb);
                    if (searchMovieDb.getResults().size() > 0) {
                        String posterPath = searchMovieDb.getResults().get(0).getPosterPath();
                        dataJadwal.getData().get(b).setPoster(MovieDbApiService.baseImageUrl + "" + posterPath);
                        Log.d(TAG, "posterNew");
                    } else {
                        dataJadwal.getData().get(b).setPoster("-");
                        Log.d(TAG, "posterNotFound");
                    }
                }

                @Override
                public void onFailure(Call<SearchMovieDb> call, Throwable t) {
                    t.printStackTrace();
                    loadingIndicatorViewFragmentResultCheckMovieToday.smoothToHide();
                    resultCheckMovieTodayFragmentPresenter.showConnectionError();
                    isSuccessfully = false;
                }
            });
            if (!isSuccessfully) {
                break;
            }
        }
        if (isSuccessfully) {
            resultCheckMovieTodayFragmentPresenter.loadAdapter();
        }

    }

    @Override
    public void onSetBackgroundBlur() {
        Drawable drawable = getResources().getDrawable(R.drawable.background_home);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap blurBuilder = BlurBuilder.blur(getContext(), bitmap);
        imageViewFragmentResultCheckMovieToday.setImageBitmap(blurBuilder);
    }

    @Override
    public void onShowConnectionError() {
        Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.str_internet_problem, Snackbar.LENGTH_LONG)
                .show();
        loadingIndicatorViewFragmentResultCheckMovieToday.smoothToHide();
        buttonRefreshFragmentResultCheckMovieToday.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadAdapter() {
        AdapterResultCheckMovieToday adapterResultCheckMovieToday = new AdapterResultCheckMovieToday(dataJadwal.getData(), getContext());
        recyclerViewContentFragmentResultCheckMovieToday.setAdapter(adapterResultCheckMovieToday);
    }
}
