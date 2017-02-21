package ysn.temanbioskop.views.fragment.home.slide_show.fragment;


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
public class SlideShow1HomeFragment extends Fragment {

    @BindView(R.id.image_view_slide_show_1)
    ImageView imageViewSlideShow1;
    public int imgSrc;

    public SlideShow1HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_slide_show1_home, container, false);
        ButterKnife.bind(this, view);
        imageViewSlideShow1.setImageResource(imgSrc);
        return view;
    }

}
