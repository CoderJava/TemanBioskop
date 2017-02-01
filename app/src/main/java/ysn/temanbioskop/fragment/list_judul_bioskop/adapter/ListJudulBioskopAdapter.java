package ysn.temanbioskop.fragment.list_judul_bioskop.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import ysn.temanbioskop.R;
import ysn.temanbioskop.model.ModelDataDetailJadwalBioskop;
import ysn.temanbioskop.model.ModelDataJadwalBioskop;
import ysn.temanbioskop.model.ModelItemCardViewListJudulBioskop;

/**
 * Created by root on 11/26/16.
 */

public class ListJudulBioskopAdapter extends RecyclerView.Adapter<ListJudulBioskopAdapter.ListJudulViewHolder> {

    private ArrayList<ModelDataDetailJadwalBioskop> modelDataDetailJadwalBioskopArrayList;
    private ArrayList<ModelItemCardViewListJudulBioskop> modelItemCardViewListJudulBioskopArrayList;
    private OnItemClickListener onItemClickListener;
    private Context context;

    public ListJudulBioskopAdapter(ArrayList<ModelItemCardViewListJudulBioskop> modelItemCardViewListJudulBioskopArrayList,
                                   ArrayList<ModelDataDetailJadwalBioskop> modelDataDetailJadwalBioskopArrayList,
                                   OnItemClickListener onItemClickListener,
                                   Context context) {
        this.modelItemCardViewListJudulBioskopArrayList = modelItemCardViewListJudulBioskopArrayList;
        this.modelDataDetailJadwalBioskopArrayList = modelDataDetailJadwalBioskopArrayList;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
    }

    @Override
    public ListJudulViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_judul_bioskop, null);
        ListJudulViewHolder listJudulViewHolder = new ListJudulViewHolder(view);
        return listJudulViewHolder;
    }

    @Override
    public void onBindViewHolder(ListJudulViewHolder holder, int position) {
        //  mengatur ukuran poster
        /*HashMap<String, Integer> screenSize = getSizeScreen();
        int widthScreen = screenSize.get("widthScreen");
        int heightScreen = screenSize.get("heightScreen");*/
        holder.textViewJudulFilm.setText(modelItemCardViewListJudulBioskopArrayList.get(position).getJudul());

        String posterPath = modelItemCardViewListJudulBioskopArrayList.get(position).getPoster();
        if (posterPath.equals("-")) {
            holder.imageViewPosterFilmBioskop.setImageDrawable(context.getResources().getDrawable(R.drawable.image_error_loaded_2));
        } else {
            Picasso.with(context)
                    .load(posterPath)
                    .placeholder(context.getResources().getDrawable(R.drawable.image_prepare_loaded))
                    .error(context.getResources().getDrawable(R.drawable.image_prepare_loaded))
                    .into(holder.imageViewPosterFilmBioskop);
        }
    }

    @Override
    public int getItemCount() {
        return modelItemCardViewListJudulBioskopArrayList.size();
    }

    //  not used
    private HashMap<String, Integer> getSizeScreen() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        HashMap<String, Integer> sizeScreen = new HashMap<>();
        int widthScreen = displayMetrics.widthPixels;
        int heightScreen = displayMetrics.heightPixels;
        sizeScreen.put("widthScreen", widthScreen);
        sizeScreen.put("heightScreen", heightScreen);
        return sizeScreen;
    }

    private Drawable getDrawableScalePoster(Drawable drawable, int widthScreen, int heightScreen) {
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Drawable drawableNew = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, widthScreen / 2, heightScreen / 2, true));
        return drawableNew;
    }

    class ListJudulViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //  komponen
        CardView cardViewItemListJudulBioskop;
        ImageView imageViewPosterFilmBioskop;
        TextView textViewJudulFilm;

        public ListJudulViewHolder(View itemView) {
            super(itemView);
            cardViewItemListJudulBioskop = (CardView) itemView.findViewById(R.id.card_view_item_list_judul_bioskop);
            imageViewPosterFilmBioskop = (ImageView) itemView.findViewById(R.id.image_view_poster_film_bioskop);
            textViewJudulFilm = (TextView) itemView.findViewById(R.id.text_view_judul_film_bioskop);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Bitmap bitmap = ((BitmapDrawable) imageViewPosterFilmBioskop.getDrawable()).getBitmap();
            onItemClickListener.onClick(view, getAdapterPosition(), modelDataDetailJadwalBioskopArrayList, bitmap);
        }
    }

    public interface OnItemClickListener {
        void onClick(View v, int adapterPosition, ArrayList<ModelDataDetailJadwalBioskop> modelDataDetailJadwalBioskopArrayList, Bitmap bitmap);
    }

}
