package ysn.temanbioskop.fragment.list_lokasi_bioskop.adapter;

import android.app.ActionBar;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ysn.temanbioskop.R;
import ysn.temanbioskop.model.ModelDataJadwalBioskop;

/**
 * Created by root on 11/12/16.
 */

public class ListLokasiBioskopAdapter extends RecyclerView.Adapter<ListLokasiBioskopAdapter.ListLokasiBioskopViewHolder> {

    private ArrayList<ModelDataJadwalBioskop> modelDataJadwalBioskopArrayList;
    private Context context;

    public ListLokasiBioskopAdapter(ArrayList<ModelDataJadwalBioskop> modelDataJadwalBioskopArrayList, Context context) {
        this.modelDataJadwalBioskopArrayList = modelDataJadwalBioskopArrayList;
        this.context = context;
    }

    @Override
    public ListLokasiBioskopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_lokasi_bioskop, parent, false);
        ListLokasiBioskopViewHolder listLokasiBioskopViewHolder = new ListLokasiBioskopViewHolder(view);
        return listLokasiBioskopViewHolder;
    }

    @Override
    public void onBindViewHolder(ListLokasiBioskopViewHolder holder, int position) {
        ModelDataJadwalBioskop modelDataJadwalBioskop = modelDataJadwalBioskopArrayList.get(position);
        holder.tvNamaBioskop.setText(modelDataJadwalBioskop.getNamaBioskop());
        holder.tvHargaTiketBioskop.setText(modelDataJadwalBioskop.getHarga() + ",-");
        String[] waktu = modelDataJadwalBioskop.getJam();
        for(int a = 0; a < waktu.length; a++) {
            LinearLayout linearLayoutWaktuItem = new LinearLayout(context);
            linearLayoutWaktuItem.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutWaktuItem.setGravity(Gravity.CENTER_VERTICAL);
            LinearLayout.LayoutParams layoutParamsLinearLayoutWaktuItem = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int dpValueMargin = 8;
            float d = context.getResources().getDisplayMetrics().density;
            int margin = (int)(dpValueMargin * d);
            layoutParamsLinearLayoutWaktuItem.setMargins(margin, margin, margin, margin);
            linearLayoutWaktuItem.setLayoutParams(layoutParamsLinearLayoutWaktuItem);

            ImageView imageViewIconWaktu = new ImageView(context);
            LinearLayout.LayoutParams layoutParamsImageIconWaktu = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int sizeValueDp = 28;
            int sizeValue = (int)(sizeValueDp * d);
            imageViewIconWaktu.setLayoutParams(layoutParamsImageIconWaktu);
            imageViewIconWaktu.getLayoutParams().width = sizeValue;
            imageViewIconWaktu.getLayoutParams().height = sizeValue;
            imageViewIconWaktu.setImageResource(R.drawable.ic_access_time_red_24dp);

            TextView textViewWaktuItem = new TextView(context);
            LinearLayout.LayoutParams layoutParamsWaktuItem = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int marginValueDp = 8;
            int marginValue = (int) (marginValueDp * d);
            layoutParamsWaktuItem.setMargins(marginValue, 0, 0, 0);
            textViewWaktuItem.setLayoutParams(layoutParamsWaktuItem);
            textViewWaktuItem.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            textViewWaktuItem.setText(waktu[a]);

            linearLayoutWaktuItem.addView(imageViewIconWaktu);
            linearLayoutWaktuItem.addView(textViewWaktuItem);
            holder.linearLayoutWaktu.addView(linearLayoutWaktuItem);
        }
    }

    @Override
    public int getItemCount() {
        return modelDataJadwalBioskopArrayList.size();
    }

    class ListLokasiBioskopViewHolder extends RecyclerView.ViewHolder {

        //  komponen
        TextView tvNamaBioskop;
        TextView tvHargaTiketBioskop;
        LinearLayout linearLayoutWaktu;

        public ListLokasiBioskopViewHolder(View itemView) {
            super(itemView);
            tvNamaBioskop = (TextView) itemView.findViewById(R.id.text_view_nama_bioskop_item_list_lokasi_bioskop);
            tvHargaTiketBioskop = (TextView) itemView.findViewById(R.id.text_view_harga_tiket_bioskop);
            linearLayoutWaktu = (LinearLayout) itemView.findViewById(R.id.linear_layout_waktu_item_list_lokasi_bioskop);
        }

    }

}
