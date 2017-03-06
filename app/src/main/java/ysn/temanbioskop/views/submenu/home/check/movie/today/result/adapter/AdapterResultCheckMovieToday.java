package ysn.temanbioskop.views.submenu.home.check.movie.today.result.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ysn.temanbioskop.R;
import ysn.temanbioskop.internal.model.bioskop.jadwal.DatumJadwal;

/**
 * Created by root on 23/02/17.
 */

public class AdapterResultCheckMovieToday extends RecyclerView.Adapter<AdapterResultCheckMovieToday.ItemContentResultCheckMovieToday> {

    List<DatumJadwal> listDatumJadwal;
    Context context;

    public AdapterResultCheckMovieToday(List<DatumJadwal> listDatumJadwal, Context context) {
        this.listDatumJadwal = listDatumJadwal;
        this.context = context;
    }

    @Override
    public ItemContentResultCheckMovieToday onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content_fragment_result_check_movie_today, null);
        return new ItemContentResultCheckMovieToday(view);
    }

    @Override
    public void onBindViewHolder(final ItemContentResultCheckMovieToday holder, int position) {
        DatumJadwal datumJadwal = listDatumJadwal.get(position);
        String poster = datumJadwal.getPoster();
        if (!poster.equals("-")) {
            Glide.with(context)
                    .load(poster)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageViewPosterItemContentResultCheckMovieToday);
        } else {
            holder.imageViewPosterItemContentResultCheckMovieToday.setImageResource(R.drawable.image_not_found_placeholder);
        }
    }

    @Override
    public int getItemCount() {
        return listDatumJadwal.size();
    }

    public class ItemContentResultCheckMovieToday extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view_poster_item_content_fragment_result_check_movie_today)
        ImageView imageViewPosterItemContentResultCheckMovieToday;

        public ItemContentResultCheckMovieToday(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
