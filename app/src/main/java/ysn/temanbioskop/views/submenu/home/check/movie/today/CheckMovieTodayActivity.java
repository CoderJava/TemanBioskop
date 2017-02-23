package ysn.temanbioskop.views.submenu.home.check.movie.today;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
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
import ysn.temanbioskop.views.submenu.home.check.movie.today.result.ResultCheckMovieTodayFragment;

public class CheckMovieTodayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_movie_today);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_container_activity_check_movie_today, new CheckMovieTodayFragment(), CheckMovieTodayFragment.class.getSimpleName())
                .commit();
    }
}
