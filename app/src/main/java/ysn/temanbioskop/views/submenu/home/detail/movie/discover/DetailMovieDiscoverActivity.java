package ysn.temanbioskop.views.submenu.home.detail.movie.discover;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ysn.temanbioskop.R;
import ysn.temanbioskop.config.tools.blur.BlurBuilder;

public class DetailMovieDiscoverActivity extends AppCompatActivity {

    @BindView(R.id.image_view_background_activity_detail_movie_discover)
    ImageView imageViewBackgroundActivityDetailMovieDiscover;
    @BindView(R.id.image_view_background_placeholder_activity_detail_movie_discover)
    ImageView imageViewBackgroundPlaceHolderActivityDetailMovieDiscover;
    @BindView(R.id.image_view_poster_activity_detail_movie_discover)
    ImageView imageViewPosterActivityDetailMovieDiscover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie_discover);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

    }

    @Subscribe(sticky = true)
    public void onMessageEvent(Bitmap bitmap) {
        Bitmap blurBuilder = BlurBuilder.blurRenderScript(bitmap, 25, this);
        imageViewBackgroundActivityDetailMovieDiscover.setImageBitmap(blurBuilder);

        imageViewPosterActivityDetailMovieDiscover.setImageBitmap(bitmap);

        Bitmap bitmapBackgroundPlaceholder = ((BitmapDrawable) getResources().getDrawable(R.drawable.black)).getBitmap();
        bitmapBackgroundPlaceholder = Bitmap.createScaledBitmap(bitmapBackgroundPlaceholder, bitmap.getWidth(), bitmap.getHeight(), false);
        imageViewBackgroundPlaceHolderActivityDetailMovieDiscover.setImageBitmap(bitmapBackgroundPlaceholder);
    }
}
