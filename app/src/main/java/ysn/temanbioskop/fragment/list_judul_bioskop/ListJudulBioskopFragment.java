package ysn.temanbioskop.fragment.list_judul_bioskop;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import ysn.temanbioskop.R;
import ysn.temanbioskop.activity.JadwalBioskopActivity;
import ysn.temanbioskop.fragment.list_judul_bioskop.adapter.ListJudulBioskopAdapter;
import ysn.temanbioskop.fragment.list_lokasi_bioskop.ListLokasiBioskopFragment;
import ysn.temanbioskop.model.ModelDataDetailJadwalBioskop;
import ysn.temanbioskop.model.ModelDataJadwalBioskop;
import ysn.temanbioskop.model.ModelItemCardViewListJudulBioskop;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListJudulBioskopFragment extends Fragment {

    //  komponen
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private View view;
    private RecyclerView recyclerViewListJudulBioskop;

    //  variable
    private String TAG = "ListJudulBioskopTAG";
    private String urlBasicPoster;
    private ArrayList<ModelDataJadwalBioskop> modelDataJadwalBioskopArrayList;
    private ArrayList<ModelDataDetailJadwalBioskop> modelDataDetailJadwalBioskopArrayList;
    private ArrayList<ModelItemCardViewListJudulBioskop> modelItemCardViewListJudulBioskopArrayList;

    public ListJudulBioskopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list_judul_bioskop, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadKomponen(view);
    }

    private void loadKomponen(View view) {
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, 1);
        recyclerViewListJudulBioskop = (RecyclerView) view.findViewById(R.id.recycler_view_list_judul_bioskop_fragment);

        /*((JadwalBioskopActivity) getActivity()).getSupportActionBar().setTitle("Film Bioskop Hari ini");*/
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_list_judul_bioskop_fragment);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setTitle("Film Bioskop Hari Ini");

        recyclerViewListJudulBioskop.setLayoutManager(staggeredGridLayoutManager);
        recyclerViewListJudulBioskop.addItemDecoration(new SpacesItemDecoration(16));

        Log.d(TAG, "loadKomponen");
        modelDataJadwalBioskopArrayList = (ArrayList<ModelDataJadwalBioskop>) getActivity().getIntent().getSerializableExtra("dataJadwalBioskop");
        modelDataDetailJadwalBioskopArrayList = (ArrayList<ModelDataDetailJadwalBioskop>) getActivity().getIntent().getSerializableExtra("dataDetailJadwalBioskop");
        modelItemCardViewListJudulBioskopArrayList = new ArrayList<>();
        Log.d(TAG, "modelDataDetailJadwalBioskopArrayList.length: " + modelDataDetailJadwalBioskopArrayList.size());
        for(int a = 0; a < modelDataDetailJadwalBioskopArrayList.size(); a++) {
            urlBasicPoster = "http://image.tmdb.org/t/p/w500";
            final String judul = modelDataDetailJadwalBioskopArrayList.get(a).getMovie();
            String poster = modelDataDetailJadwalBioskopArrayList.get(a).getPoster();
            Log.d(TAG, "judul: " + judul);
            Log.d(TAG, "poster: " + poster);
            Log.d(TAG, "poster_path: " + urlBasicPoster + "" + poster);

            if (!poster.equals("-")) {
                ModelItemCardViewListJudulBioskop modelItemCardViewListJudulBioskop = new ModelItemCardViewListJudulBioskop();
                modelItemCardViewListJudulBioskop.setJudul(judul);
                modelItemCardViewListJudulBioskop.setPoster(urlBasicPoster + "" + poster);
                modelItemCardViewListJudulBioskopArrayList.add(modelItemCardViewListJudulBioskop);
            } else {
                ModelItemCardViewListJudulBioskop modelItemCardViewListJudulBioskop = new ModelItemCardViewListJudulBioskop();
                modelItemCardViewListJudulBioskop.setJudul(judul);
                modelItemCardViewListJudulBioskop.setPoster("-");
                modelItemCardViewListJudulBioskopArrayList.add(modelItemCardViewListJudulBioskop);
            }
        }

        ListJudulBioskopAdapter.OnItemClickListener onItemClickListener = new ListJudulBioskopAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int adapterPosition, ArrayList<ModelDataDetailJadwalBioskop> modelDataDetailJadwalBioskopArrayList, Bitmap bitmap) {
                ListLokasiBioskopFragment listLokasiBioskopFragment = new ListLokasiBioskopFragment();
                String judul = modelDataDetailJadwalBioskopArrayList.get(adapterPosition).getMovie();
                ArrayList<ModelDataJadwalBioskop> modelDataJadwalBioskopArrayList = modelDataDetailJadwalBioskopArrayList.get(adapterPosition).getJadwal();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();

                Bundle bundle = new Bundle();
                bundle.putString("judul", judul);
                bundle.putInt("adapterPosition", adapterPosition);
                bundle.putByteArray("bitmap", byteArray);
                bundle.putSerializable("modelDataJadwalBioskopArrayList", modelDataJadwalBioskopArrayList);
                /*bundle.putParcelableArrayList("modelDataJadwalBioskopArrayList", (ArrayList<? extends Parcelable>) modelDataJadwalBioskopArrayList);*/
                listLokasiBioskopFragment.setArguments(bundle);

                listLokasiBioskopFragment.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_up, R.anim.zoom_exit, R.anim.zoom_enter, R.anim.slide_out_bottom)
                        .add(R.id.frame_layout_container_jadwal_bioskop, listLokasiBioskopFragment, ListLokasiBioskopFragment.class.getSimpleName())
                        .addToBackStack(null)
                        .commit();
            }
        };

        ListJudulBioskopAdapter listJudulBioskopAdapter = new ListJudulBioskopAdapter(modelItemCardViewListJudulBioskopArrayList, modelDataDetailJadwalBioskopArrayList, onItemClickListener, getContext());
        recyclerViewListJudulBioskop.setAdapter(listJudulBioskopAdapter);

    }
}
