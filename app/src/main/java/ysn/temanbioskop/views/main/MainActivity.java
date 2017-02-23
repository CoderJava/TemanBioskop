package ysn.temanbioskop.views.main;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ysn.temanbioskop.R;
import ysn.temanbioskop.databinding.ActivityMainBinding;
import ysn.temanbioskop.config.tools.blur.BlurBuilder;
import ysn.temanbioskop.views.fragment.adapter.TabFragmentPagerAdapter;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private MainActivityPresenter mainActivityPresenter;

    @BindView(R.id.image_view_background_activity_main)
    ImageView imageViewBackgroundActivityMain;
    @BindView(R.id.tab_layout_menu_activity_main)
    TabLayout tabLayoutMenuActivityMain;
    @BindView(R.id.view_pager_menu_activity_main)
    ViewPager viewPagerMenuActivityMain;

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();
        onAttach();
        loadComponent();
        mainActivityPresenter.setBackgroundBlur();
    }

    private void loadComponent() {
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ButterKnife.bind(this);


        final String[] title = new String[]{"Home", "Favorite", "Popular"};
        viewPagerMenuActivityMain.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager(), title));
        tabLayoutMenuActivityMain.setTabTextColors(getResources().getColor(android.R.color.white), getResources().getColor(R.color.colorPrimaryDark));
        tabLayoutMenuActivityMain.setupWithViewPager(viewPagerMenuActivityMain);

        Drawable iconHomeUnselected = getResources().getDrawable(R.drawable.ic_home_unselected_24dp);
        Drawable iconFavoriteUnselected = getResources().getDrawable(R.drawable.ic_favorite_unselected_24dp);
        Drawable iconPopularUnselected = getResources().getDrawable(R.drawable.ic_movie_filter_unselected_24dp);
        Drawable iconHomeSelected = getResources().getDrawable(R.drawable.ic_home_selected_24dp);
        Drawable iconFavoriteSelected = getResources().getDrawable(R.drawable.ic_favorite_selected_24dp);
        Drawable iconPopularSelected = getResources().getDrawable(R.drawable.ic_movie_filter_selected_24dp);

        final List<Drawable> iconsUnselected = new ArrayList<>();
        iconsUnselected.add(iconHomeUnselected);
        iconsUnselected.add(iconFavoriteUnselected);
        iconsUnselected.add(iconPopularUnselected);

        final List<Drawable> iconsSelected = new ArrayList<>();
        iconsSelected.add(iconHomeSelected);
        iconsSelected.add(iconFavoriteSelected);
        iconsSelected.add(iconPopularSelected);

        for(int a = 0; a < title.length; a++) {
            if (a == 0) {
                tabLayoutMenuActivityMain.getTabAt(a).setIcon(iconsSelected.get(a));
            } else {
                tabLayoutMenuActivityMain.getTabAt(a).setIcon(iconsUnselected.get(a));
            }
        }

        viewPagerMenuActivityMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //  nothing to do
            }

            @Override
            public void onPageSelected(int position) {
                for(int a = 0; a < title.length; a++) {
                    if (a == position) {
                        tabLayoutMenuActivityMain.getTabAt(a).setIcon(iconsSelected.get(a));
                    } else {
                        tabLayoutMenuActivityMain.getTabAt(a).setIcon(iconsUnselected.get(a));
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //  nothing to do
            }
        });
    }

    private void initPresenter() {
        mainActivityPresenter = new MainActivityPresenter();
    }

    @Override
    public void onAttach() {
        mainActivityPresenter.onAttach(this);
    }

    @Override
    public void onDetach() {
        mainActivityPresenter.onDetach();
    }

    @Override
    public void onSetBackgroundBlur() {
        Drawable drawable = getResources().getDrawable(R.drawable.background_home);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap blurBuilder = BlurBuilder.blur(this, bitmap);
        imageViewBackgroundActivityMain.setImageBitmap(blurBuilder);
    }
}
