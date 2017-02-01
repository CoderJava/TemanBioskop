package ysn.temanbioskop.fragment.list_lokasi_bioskop;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

import ysn.temanbioskop.R;
import ysn.temanbioskop.fragment.list_lokasi_bioskop.adapter.ListLokasiBioskopAdapter;
import ysn.temanbioskop.model.ModelDataJadwalBioskop;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListLokasiBioskopFragment extends Fragment {

    //  komponen
    private View view;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private ImageView imageViewCollapseToolbar;
    private RecyclerView recyclerViewListLokasiBioskop;

    //  variable
    private String TAG = "lokasiBioskopTAG";
    private int adapterPosition;
    private String judul;
    private Bitmap bitmapPoster;
    private ArrayList<? extends ModelDataJadwalBioskop> modelDataJadwalBioskopArrayList;

    public ListLokasiBioskopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list_lokasi_bioskop, container, false);
        Bundle bundle = getArguments();
        judul = bundle.getString("judul");
        adapterPosition = bundle.getInt("adapterPosition");
        byte[] byteArray = bundle.getByteArray("bitmap");
        bitmapPoster = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        modelDataJadwalBioskopArrayList = bundle.getParcelableArrayList("modelDataJadwalBioskopArrayList");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadKomponen(view);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadAdapter();
    }

    private void loadKomponen(View view) {
        collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar_layout_list_lokasi_bioskop);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar_lokasi_bioskop);
        imageViewCollapseToolbar = (ImageView) view.findViewById(R.id.image_view_collapse_toolbar);
        recyclerViewListLokasiBioskop = (RecyclerView) view.findViewById(R.id.recycler_view_list_lokasi_bioskop);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar_lokasi_bioskop);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewListLokasiBioskop.setHasFixedSize(true);
        recyclerViewListLokasiBioskop.setLayoutManager(linearLayoutManager);

        collapsingToolbarLayout.setTitle(judul);
        imageViewCollapseToolbar.setImageBitmap(bitmapPoster);
    }

    private void loadAdapter() {
        ListLokasiBioskopAdapter listLokasiBioskopAdapter = new ListLokasiBioskopAdapter((ArrayList<ModelDataJadwalBioskop>) modelDataJadwalBioskopArrayList, getContext());
        recyclerViewListLokasiBioskop.setAdapter(listLokasiBioskopAdapter);

        Log.d(TAG, "ListLokasiBioskopFragment");
        for(int a = 0; a < modelDataJadwalBioskopArrayList.size(); a++) {
            ModelDataJadwalBioskop modelDataJadwalBioskop = modelDataJadwalBioskopArrayList.get(a);
            String str = "Bioskop: " + modelDataJadwalBioskop.getNamaBioskop();
            str += ", harga: " + modelDataJadwalBioskop.getHarga();
            str += ", jam: " + Arrays.toString(modelDataJadwalBioskop.getJam());
            Log.d(TAG, "modelDataJadwalBioskop(" + a + "): " + str);
        }
    }

}
