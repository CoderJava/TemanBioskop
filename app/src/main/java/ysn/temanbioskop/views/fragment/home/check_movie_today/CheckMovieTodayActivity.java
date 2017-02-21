package ysn.temanbioskop.views.fragment.home.check_movie_today;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ysn.temanbioskop.R;
import ysn.temanbioskop.databinding.ActivityCheckMovieTodayBinding;
import ysn.temanbioskop.tools.blur.BlurBuilder;

public class CheckMovieTodayActivity extends AppCompatActivity {

    @BindView(R.id.image_view_background_activity_check_movie_today)
    ImageView imageViewBackgroundActivityCheckMovieToday;

    ActivityCheckMovieTodayBinding activityCheckMovieTodayBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_movie_today);
        activityCheckMovieTodayBinding = DataBindingUtil.setContentView(this, R.layout.activity_check_movie_today);
        ButterKnife.bind(this);

        setBackgroundBlur();
    }

    private void setBackgroundBlur() {
        Drawable drawable = getResources().getDrawable(R.drawable.background_home);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap blurBuilder = BlurBuilder.blur(this, bitmap);
        imageViewBackgroundActivityCheckMovieToday.setImageBitmap(blurBuilder);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
    }
}
