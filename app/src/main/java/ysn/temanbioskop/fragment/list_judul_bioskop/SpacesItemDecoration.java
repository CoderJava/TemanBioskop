package ysn.temanbioskop.fragment.list_judul_bioskop;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by root on 11/27/16.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = space;
        outRect.right = space;
        outRect.bottom = space;
        outRect.left = space;

        /*if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = 0;
        } else {
            outR
        } */
    }
}
