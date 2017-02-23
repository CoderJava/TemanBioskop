package ysn.temanbioskop.views.submenu.home.check.movie.today;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

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
import ysn.temanbioskop.databinding.ActivityCheckMovieTodayBinding;
import ysn.temanbioskop.config.tools.blur.BlurBuilder;
import ysn.temanbioskop.internal.model.bioskop.kota.DataKota;
import ysn.temanbioskop.internal.model.bioskop.kota.DatumKota;
import ysn.temanbioskop.views.submenu.home.check.movie.today.adapter.AdapterCheckMovieToday;

public class CheckMovieTodayActivity extends AppCompatActivity implements CheckMovieTodayActivityView {

    private static final String TAG = "CheckMovieTAG";
    @BindView(R.id.image_view_background_activity_check_movie_today)
    ImageView imageViewBackgroundActivityCheckMovieToday;
    @BindView(R.id.edit_text_nama_kota_activity_check_movie_today)
    EditText editTextNamaKotaActivityCheckMovieToday;
    @BindView(R.id.button_refresh_activity_check_movie_today)
    Button buttonRefreshActivityCheckMovieToday;
    @BindView(R.id.loading_indicator_view_activity_check_movie_today)
    AVLoadingIndicatorView loadingIndicatorViewActivityCheckMovieToday;
    @BindView(R.id.recycler_view_nama_kota_activity_check_movie_today)
    RecyclerView recyclerViewNamaKotaActivityCheckMovieToday;

    private ActivityCheckMovieTodayBinding activityCheckMovieTodayBinding;
    private CheckMovieTodayActivityPresenter checkMovieTodayActivityPresenter;
    private Retrofit retrofit;
    private DataKota dataKota;
    private String idKota;
    private String namaKota;
    private AdapterCheckMovieToday adapterCheckMovieToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_movie_today);
        activityCheckMovieTodayBinding = DataBindingUtil.setContentView(this, R.layout.activity_check_movie_today);
        initPresenter();
        initRetrofit();
        onAttach();
        ButterKnife.bind(this);
        loadComponent();
        checkMovieTodayActivityPresenter.setBackgroundBlur();
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(JadwalBioskopApiService.baseApiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void loadComponent() {
        recyclerViewNamaKotaActivityCheckMovieToday.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_item_decoration_whie));
        recyclerViewNamaKotaActivityCheckMovieToday.addItemDecoration(dividerItemDecoration);
        checkMovieTodayActivityPresenter.loadDataKota();

        editTextNamaKotaActivityCheckMovieToday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //  nothing to do in here
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //  nothing to do in here
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String namaKotaQuery = editTextNamaKotaActivityCheckMovieToday.getText().toString().toLowerCase().trim();
                if (dataKota != null) {
                    checkMovieTodayActivityPresenter.filterNamaKota(namaKotaQuery);
                }
            }
        });
    }

    private void initPresenter() {
        checkMovieTodayActivityPresenter = new CheckMovieTodayActivityPresenter();
    }

    @OnClick({R.id.button_refresh_activity_check_movie_today})
    public void onClick(Button button) {
        switch (button.getId()) {
            case R.id.button_refresh_activity_check_movie_today:
                buttonRefreshActivityCheckMovieToday.setVisibility(View.GONE);
                checkMovieTodayActivityPresenter.loadDataKota();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
    }

    @Override
    public void onAttach() {
        checkMovieTodayActivityPresenter.onAttach(this);
    }

    @Override
    public void onDetach() {
        checkMovieTodayActivityPresenter.onDetach();
    }

    @Override
    public void onSetBackgroundBlur() {
        Drawable drawable = getResources().getDrawable(R.drawable.background_home);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap blurBuilder = BlurBuilder.blur(this, bitmap);
        imageViewBackgroundActivityCheckMovieToday.setImageBitmap(blurBuilder);

    }

    @Override
    public void onLoadAdapterRecyclerView() {
        adapterCheckMovieToday = new AdapterCheckMovieToday(dataKota.getData(), new AdapterCheckMovieToday.OnItemClickListener() {
            @Override
            public void onClick(String idKota, String namaKota) {
                CheckMovieTodayActivity.this.idKota = idKota;
                CheckMovieTodayActivity.this.namaKota = namaKota;
            }
        });
        recyclerViewNamaKotaActivityCheckMovieToday.setAdapter(adapterCheckMovieToday);
    }

    @Override
    public void onLoadDataKota() {
        loadingIndicatorViewActivityCheckMovieToday.smoothToShow();
        JadwalBioskopApiService jadwalBioskopApiService = retrofit.create(JadwalBioskopApiService.class);
        Call<DataKota> resultCallDataKota = jadwalBioskopApiService.getDataKota(jadwalBioskopApiService.apiKey);
        resultCallDataKota.enqueue(new Callback<DataKota>() {
            @Override
            public void onResponse(Call<DataKota> call, Response<DataKota> response) {
                dataKota = response.body();
                checkMovieTodayActivityPresenter.loadAdapterRecyclerView();
                loadingIndicatorViewActivityCheckMovieToday.smoothToHide();
            }

            @Override
            public void onFailure(Call<DataKota> call, Throwable t) {
                t.printStackTrace();
                Snackbar.make(findViewById(android.R.id.content), R.string.str_internet_problem, Snackbar.LENGTH_LONG)
                        .show();
                loadingIndicatorViewActivityCheckMovieToday.smoothToHide();
                buttonRefreshActivityCheckMovieToday.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onShowResultCheckMovieToday() {
        //  do something
    }

    @Override
    public void onFilterNamaKota(String query) {
        List<DatumKota> listDatumKotaFiltered = new ArrayList<>();
        for (DatumKota datumKota : dataKota.getData()) {
            if (datumKota.getKota().toLowerCase().contains(query)) {
                listDatumKotaFiltered.add(datumKota);
                Log.d(TAG, "query masuk " + datumKota.getKota() + " query: " + query);
            }
        }
        adapterCheckMovieToday.refresh(listDatumKotaFiltered);
        adapterCheckMovieToday.notifyDataSetChanged();
    }

}
