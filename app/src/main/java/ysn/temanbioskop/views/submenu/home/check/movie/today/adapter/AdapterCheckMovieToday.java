package ysn.temanbioskop.views.submenu.home.check.movie.today.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ysn.temanbioskop.R;
import ysn.temanbioskop.internal.model.bioskop.kota.DatumKota;

/**
 * Created by root on 23/02/17.
 */

public class AdapterCheckMovieToday extends RecyclerView.Adapter<AdapterCheckMovieToday.ItemNamaKotaViewHolder> {

    private List<DatumKota> listDatumKota;
    private OnItemClickListener onItemClickListener;

    public AdapterCheckMovieToday(List<DatumKota> listDatumKota, OnItemClickListener onItemClickListener) {
        this.listDatumKota = listDatumKota;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ItemNamaKotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nama_kota_activity_check_movie_today, null);
        return new ItemNamaKotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemNamaKotaViewHolder holder, int position) {
        holder.textViewNamaKotaItemNamaKotaViewHolder.setText(listDatumKota.get(position).getKota());
        holder.onClick(listDatumKota.get(position).getId(), listDatumKota.get(position).getKota());
    }

    @Override
    public int getItemCount() {
        return listDatumKota.size();
    }

    public void refresh(List<DatumKota> listDatumKotaNew) {
        this.listDatumKota = listDatumKotaNew;
    }

    public class ItemNamaKotaViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_nama_kota_item_nama_kota_activity_check_movie_today)
        TextView textViewNamaKotaItemNamaKotaViewHolder;

        public ItemNamaKotaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onClick(final String namaKota, final String idKota) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(idKota, namaKota);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onClick(String idKota, String namaKota);
    }

}
