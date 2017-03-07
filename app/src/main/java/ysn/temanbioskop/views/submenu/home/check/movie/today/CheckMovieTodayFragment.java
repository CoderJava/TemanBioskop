package ysn.temanbioskop.views.submenu.home.check.movie.today;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import ysn.temanbioskop.config.tools.blur.BlurBuilder;
import ysn.temanbioskop.internal.model.bioskop.kota.DataKota;
import ysn.temanbioskop.internal.model.bioskop.kota.DatumKota;
import ysn.temanbioskop.views.submenu.home.check.movie.today.adapter.AdapterCheckMovieToday;
import ysn.temanbioskop.views.submenu.home.check.movie.today.result.ResultCheckMovieTodayFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckMovieTodayFragment extends Fragment implements CheckMovieTodayFragmentView {

    private View view;
    private static final String TAG = "CheckMovieTAG";
    @BindView(R.id.image_view_background_fragment_check_movie_today)
    ImageView imageViewBackgroundFragmentCheckMovieToday;
    @BindView(R.id.edit_text_nama_kota_fragment_check_movie_today)
    EditText editTextNamaKotaFragmentCheckMovieToday;
    @BindView(R.id.button_refresh_fragment_check_movie_today)
    Button buttonRefreshFragmentCheckMovieToday;
    @BindView(R.id.loading_indicator_view_fragment_check_movie_today)
    AVLoadingIndicatorView loadingIndicatorViewFragmentCheckMovieToday;
    @BindView(R.id.recycler_view_nama_kota_fragment_check_movie_today)
    RecyclerView recyclerViewNamaKotaFragmentCheckMovieToday;

    private CheckMovieTodayFragmentPresenter checkMovieTodayFragmentPresenter;
    private Retrofit retrofit;
    private DataKota dataKota;
    private AdapterCheckMovieToday adapterCheckMovieToday;

    public CheckMovieTodayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_check_movie_today, container, false);
        initPresenter();
        ButterKnife.bind(this, view);
        onAttach();
        initRetrofit();
        onAttach();
        loadComponent();
        checkMovieTodayFragmentPresenter.setBackgroundBlur();
        return view;
    }

    private void loadComponent() {
        recyclerViewNamaKotaFragmentCheckMovieToday.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_item_decoration_whie));
        recyclerViewNamaKotaFragmentCheckMovieToday.addItemDecoration(dividerItemDecoration);
        checkMovieTodayFragmentPresenter.loadDataKota();

        editTextNamaKotaFragmentCheckMovieToday.addTextChangedListener(new TextWatcher() {
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
                String namaKotaQuery = editTextNamaKotaFragmentCheckMovieToday.getText().toString().toLowerCase().trim();
                if (dataKota != null) {
                    checkMovieTodayFragmentPresenter.filterNamaKota(namaKotaQuery);
                }
            }
        });
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(JadwalBioskopApiService.baseApiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void initPresenter() {
        checkMovieTodayFragmentPresenter = new CheckMovieTodayFragmentPresenter();
    }

    @OnClick({R.id.button_refresh_fragment_check_movie_today})
    public void onClick(Button button) {
        switch (button.getId()) {
            case R.id.button_refresh_fragment_check_movie_today:
                buttonRefreshFragmentCheckMovieToday.setVisibility(View.GONE);
                checkMovieTodayFragmentPresenter.loadDataKota();
                break;
        }
    }

    @Override
    public void onAttach() {
        checkMovieTodayFragmentPresenter.onAttach(this);
    }

    @Override
    public void onSetBackgroundBlur() {
        Drawable drawable = getResources().getDrawable(R.drawable.background_home);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap blurBuilder = BlurBuilder.blur(getActivity(), bitmap, 0.3f);
        imageViewBackgroundFragmentCheckMovieToday.setImageBitmap(blurBuilder);
    }

    @Override
    public void onLoadAdapterRecyclerView() {
        adapterCheckMovieToday = new AdapterCheckMovieToday(dataKota.getData(), new AdapterCheckMovieToday.OnItemClickListener() {
            @Override
            public void onClick(String idKota, String namaKota) {
                checkMovieTodayFragmentPresenter.showResultCheckMovieToday(idKota, namaKota);
            }
        });
        recyclerViewNamaKotaFragmentCheckMovieToday.setAdapter(adapterCheckMovieToday);
    }

    @Override
    public void onLoadDataKota() {
        loadingIndicatorViewFragmentCheckMovieToday.smoothToShow();
        JadwalBioskopApiService jadwalBioskopApiService = retrofit.create(JadwalBioskopApiService.class);
        Call<DataKota> resultCallDataKota = jadwalBioskopApiService.getDataKota(jadwalBioskopApiService.apiKey);
        resultCallDataKota.enqueue(new Callback<DataKota>() {
            @Override
            public void onResponse(Call<DataKota> call, Response<DataKota> response) {
                dataKota = response.body();
                checkMovieTodayFragmentPresenter.loadAdapterRecyclerView();
                loadingIndicatorViewFragmentCheckMovieToday.smoothToHide();
            }

            @Override
            public void onFailure(Call<DataKota> call, Throwable t) {
                t.printStackTrace();
                Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.str_internet_problem, Snackbar.LENGTH_LONG)
                        .show();
                loadingIndicatorViewFragmentCheckMovieToday.smoothToHide();
                buttonRefreshFragmentCheckMovieToday.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onShowResultCheckMovieToday(String idKota, String namaKota) {
        Bundle bundle = new Bundle();
        bundle.putString("idKota", idKota);
        bundle.putString("namaKota", namaKota);
        ResultCheckMovieTodayFragment resultCheckMovieTodayFragment = new ResultCheckMovieTodayFragment();
        resultCheckMovieTodayFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                .add(R.id.frame_layout_container_activity_check_movie_today, resultCheckMovieTodayFragment, ResultCheckMovieTodayFragment.class.getSimpleName())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onFilterNamaKota(String query) {
        List<DatumKota> listDatumKotaFiltered = new ArrayList<>();
        for (DatumKota datumKota : dataKota.getData()) {
            if (datumKota.getKota().toLowerCase().contains(query)) {
                listDatumKotaFiltered.add(datumKota);
            }
        }
        adapterCheckMovieToday.refresh(listDatumKotaFiltered);
        adapterCheckMovieToday.notifyDataSetChanged();
    }
}
