package ysn.temanbioskop.views.fragment.home.slideshow.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ysn.temanbioskop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SlideShow4HomeFragment extends Fragment {

    @BindView(R.id.image_view_slide_show_4)
    ImageView imageViewSlideShow4;
    public int imgSrc;

    public SlideShow4HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_slide_show4_home, container, false);
        ButterKnife.bind(this, view);
        imageViewSlideShow4.setImageResource(imgSrc);
        return view;
    }

}
