package ysn.temanbioskop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import ysn.temanbioskop.R;
import ysn.temanbioskop.fragment.list_judul_bioskop.ListJudulBioskopFragment;

public class JadwalBioskopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_bioskop);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_list_judul_bioskop_fragment);
        setSupportActionBar(toolbar);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_container_jadwal_bioskop, new ListJudulBioskopFragment(), ListJudulBioskopFragment.class.getSimpleName())
                .commit();
    }
}
