package ysn.temanbioskop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.wangyuwei.loadingview.LoadingView;
import ysn.temanbioskop.activity.AboutActivity;
import ysn.temanbioskop.activity.JadwalBioskopActivity;
import ysn.temanbioskop.blur.BlurBuilder;
import ysn.temanbioskop.model.ModelDataDetailJadwalBioskop;
import ysn.temanbioskop.model.ModelDataJadwalBioskop;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //  komponen
    private RelativeLayout relativeLayoutContainer;
    private ImageView imageViewBackground1, imageViewBackground2;
    private AutoCompleteTextView autoCompleteTextViewInputNamaKota;
    private AppCompatButton btnCari;
    private LoadingView loadingView;
    private RelativeLayout relativeLayoutLoadingContainer;

    //  objek
    private RequestQueue requestQueue;

    //  variable
    private String keyIBacor = "39ebb7dbb8461e0dd0f5bf48e5df7a59";
    private String TAG = "MainActivityTAG";
    private String urlDaftarKota;
    private String urlJadwalBioskop;
    private String urlDataTheMovieDb;
    private String urlPoster;
    private String idKota;
    private String namaKota;
    private boolean namaKotaValid = false;
    private String tanggal;
    private String kota;
    private ArrayList<ModelDataDetailJadwalBioskop> modelDataDetailJadwalBioskopArrayList;
    private ArrayList<ModelDataJadwalBioskop> modelDataJadwalBioskopArrayList;
    private String[] arrPosterFilm;
    private Bitmap[] bitmaps;
    private int indexPosterFilm = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("TemanBioskop");

        loadKomponen();
        dynamicBackground();
    }

    private void dynamicBackground() {
        String urlFilmBioskopPopuler = " https://api.themoviedb.org/3/movie/popular?api_key=fe71629f457c2466875babf7fbe5bb6c&language=en-US&page=1";
        requestQueue = Volley.newRequestQueue(this);
        arrPosterFilm = null;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlFilmBioskopPopuler, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    final JSONArray jsonArrayResult = response.getJSONArray("results");
                    arrPosterFilm = new String[jsonArrayResult.length()];
                    bitmaps = new Bitmap[10];
                    for(int a = 0; a < 10; a++) {
                        final int b = a;
                        JSONObject jsonObjectDetail = jsonArrayResult.getJSONObject(a);
                        final ImageView imageViewTemp = new ImageView(MainActivity.this);
                        arrPosterFilm[a] = jsonObjectDetail.getString("poster_path");
                        Log.d(TAG, "arrPosterFilm[" + a + "]: " + arrPosterFilm[a]);
                        Picasso
                                .with(MainActivity.this)
                                .load("http://image.tmdb.org/t/p/w500" + arrPosterFilm[a])
                                .into(imageViewTemp, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        Log.d(TAG, "onSuccess: " + b);
                                        bitmaps[b] = ((BitmapDrawable) imageViewTemp.getDrawable()).getBitmap();
                                        if (b == 9) {
                                            preparePlayBackgroundDynamic();
                                        }
                                    }

                                    @Override
                                    public void onError() {

                                    }
                                });
                    }
                } catch (JSONException jsone) {
                    jsone.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void preparePlayBackgroundDynamic() {
        new CountDownTimer(8000, 7000) {

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                repeat();
            }
        }.start();
    }

    private void repeat() {
        new CountDownTimer(8000, 7000) {

            @Override
            public void onTick(long l) {
                indexPosterFilm++;
                indexPosterFilm = (indexPosterFilm >= bitmaps.length) ? 0 : indexPosterFilm;
                playBackgroundDynamic(indexPosterFilm);

            }

            @Override
            public void onFinish() {
                new CountDownTimer(8000, 7000) {

                    @Override
                    public void onTick(long l) {
                        indexPosterFilm++;
                        indexPosterFilm = (indexPosterFilm >= bitmaps.length) ? 0 : indexPosterFilm;
                        playBackgroundDynamic(indexPosterFilm);
                    }

                    @Override
                    public void onFinish() {
                        repeat();
                    }
                }.start();
            }
        }.start();
    }


    private void playBackgroundDynamic(int index) {
        if (imageViewBackground1.getVisibility() == View.VISIBLE) {
            imageViewBackground2.setImageBitmap(bitmaps[index]);
            imageViewBackground2.setVisibility(View.VISIBLE);

            AlphaAnimation alphaAnimation1 = new AlphaAnimation(1, 0);
            alphaAnimation1.setDuration(2 * 1000);
            imageViewBackground1.setAnimation(alphaAnimation1);

            AlphaAnimation alphaAnimation2 = new AlphaAnimation(0, 1);
            alphaAnimation2.setDuration(2 * 1000);
            imageViewBackground2.setAnimation(alphaAnimation2);

            imageViewBackground1.setVisibility(View.INVISIBLE);
        } else {
            imageViewBackground1.setImageBitmap(bitmaps[index]);
            imageViewBackground1.setVisibility(View.VISIBLE);

            AlphaAnimation alphaAnimation1 = new AlphaAnimation(1, 0);
            alphaAnimation1.setDuration(2 * 1000);
            imageViewBackground2.setAnimation(alphaAnimation1);

            AlphaAnimation alphaAnimation2 = new AlphaAnimation(0, 1);
            alphaAnimation2.setDuration(2 * 1000);
            imageViewBackground1.setAnimation(alphaAnimation2);

            imageViewBackground2.setVisibility(View.INVISIBLE);
        }
    }

    private void loadKomponen() {
        relativeLayoutContainer = (RelativeLayout) findViewById(R.id.relative_layout_container);
        imageViewBackground1 = (ImageView) findViewById(R.id.image_view_background_1);
        imageViewBackground2 = (ImageView) findViewById(R.id.image_view_background_2);
        autoCompleteTextViewInputNamaKota = (AutoCompleteTextView) findViewById(R.id.edittext_input_nama_kota);
        btnCari = (AppCompatButton) findViewById(R.id.button_cari_jadwal_bioskop);
        relativeLayoutLoadingContainer = (RelativeLayout) findViewById(R.id.relative_layout_loading_container);
        loadingView = (LoadingView) findViewById(R.id.loading_view);

        Drawable drawable = getResources().getDrawable(R.drawable.image_header_main_activity_example);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap blurBuilder = BlurBuilder.blur(this, bitmap);
        relativeLayoutContainer.setBackground(new BitmapDrawable(getResources(), blurBuilder));

        btnCari.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        //  btnCari
        if (v == btnCari) {
            urlDaftarKota = "http://ibacor.com/api/jadwal-bioskop?k=" + keyIBacor;
            urlJadwalBioskop = "http://ibacor.com/api/jadwal-bioskop?";
            urlPoster = "http://image.tmdb.org/t/p/w500/";
            namaKotaValid = false;
            idKota = "";
            namaKota = "";
            final String namaKotaInput = autoCompleteTextViewInputNamaKota.getText().toString();
            if (namaKotaInput.isEmpty()) {
                showSnackBar(getResources().getString(R.string.str_nama_kota_is_empty));
                autoCompleteTextViewInputNamaKota.requestFocus();
            } else {
                startLoadingView();
                getDaftarKota(namaKotaInput);
            }
        }
    }

    private void getDaftarKota(final String namaKotaInput) {
        JsonObjectRequest jsonObjectRequestDaftarKota = new JsonObjectRequest(Request.Method.GET, urlDaftarKota, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String status;
                try {
                    status = response.getString("status");
                    Log.d(TAG, "Status(getDaftarKota): " + status);
                    if (status.equalsIgnoreCase("success")) {
                        JSONArray jsonArrayDataNamaKota = response.getJSONArray("data");
                        for(int a = 0; a < jsonArrayDataNamaKota.length(); a++) {
                            JSONObject jsonObjectDataDetailKota = jsonArrayDataNamaKota.getJSONObject(a);
                            if (namaKotaInput.equalsIgnoreCase(jsonObjectDataDetailKota.getString("kota"))) {
                                idKota = jsonObjectDataDetailKota.getString("id");
                                namaKota = namaKotaInput;
                                namaKotaValid = true;
                                break;
                            }
                        }
                        Log.d(TAG, "namaKotaValid: " + namaKotaValid);
                        if (!namaKotaValid) {
                            showSnackBar(getResources().getString(R.string.str_nama_kota_tidak_tersedia));
                            autoCompleteTextViewInputNamaKota.requestFocus();
                            stopLoadingView();
                        } else {
                            getJadwalBioskop();
                        }
                    } else {
                        showSnackBar(getResources().getString(R.string.str_gagal_ambil_data));
                        autoCompleteTextViewInputNamaKota.requestFocus();
                        stopLoadingView();
                    }
                } catch (JSONException jsone) {
                    jsone.printStackTrace();
                    showSnackBar(getResources().getString(R.string.str_gagal_ambil_data));
                    autoCompleteTextViewInputNamaKota.requestFocus();
                    stopLoadingView();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                showSnackBar(getResources().getString(R.string.str_gagal_ambil_data));
                stopLoadingView();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequestDaftarKota);
    }

    private void getJadwalBioskop() {
        modelDataDetailJadwalBioskopArrayList =  new ArrayList<>();
        kota = "";
        tanggal = "";
        urlJadwalBioskop += "id=" + idKota + "&";
        urlJadwalBioskop += "k=" + keyIBacor;
        JsonObjectRequest jsonObjectRequestDataJadwalBioskop = new JsonObjectRequest(Request.Method.GET, urlJadwalBioskop, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String status;
                try {
                    status = response.getString("status");
                    Log.d(TAG, "urlJadwalBioskop: " + urlJadwalBioskop);
                    Log.d(TAG, "Status2: " + status);
                    if (status.equalsIgnoreCase("success")) {
                        kota = response.getString("kota");
                        tanggal = response.getString("date");
                        JSONArray jsonArrayDataJadwalBioskop = response.getJSONArray("data");
                        for (int a = 0; a < jsonArrayDataJadwalBioskop.length(); a++) {
                            JSONObject jsonObjectDataDetailJadwalBioskop = jsonArrayDataJadwalBioskop.getJSONObject(a);
                            String movie = jsonObjectDataDetailJadwalBioskop.getString("movie");
                            String poster = "-";    //  getPosterFilm
                            String genre = jsonObjectDataDetailJadwalBioskop.getString("genre");
                            String duration = jsonObjectDataDetailJadwalBioskop.getString("duration");
                            JSONArray jsonArrayJadwal = jsonObjectDataDetailJadwalBioskop.getJSONArray("jadwal");
                            modelDataJadwalBioskopArrayList = new ArrayList<>();
                            for (int b = 0; b < jsonArrayJadwal.length(); b++) {
                                JSONObject jsonObjectJadwal = jsonArrayJadwal.getJSONObject(b);
                                String namaBioskop = jsonObjectJadwal.getString("bioskop");
                                JSONArray jsonArrayJam = jsonObjectJadwal.getJSONArray("jam");
                                String[] jam = new String[jsonArrayJam.length()];
                                for (int c = 0; c < jsonArrayJam.length(); c++) {
                                    jam[c] = jsonArrayJam.getString(c);
                                }
                                String harga = jsonObjectJadwal.getString("harga");

                                ModelDataJadwalBioskop modelDataJadwalBioskop = new ModelDataJadwalBioskop();
                                modelDataJadwalBioskop.setNamaBioskop(namaBioskop);
                                modelDataJadwalBioskop.setJam(jam);
                                modelDataJadwalBioskop.setHarga(harga);
                                modelDataJadwalBioskopArrayList.add(modelDataJadwalBioskop);
                            }

                            ModelDataDetailJadwalBioskop modelDataDetailJadwalBioskop = new ModelDataDetailJadwalBioskop();
                            modelDataDetailJadwalBioskop.setMovie(movie);
                            modelDataDetailJadwalBioskop.setPoster(poster);
                            modelDataDetailJadwalBioskop.setGenre(genre);
                            modelDataDetailJadwalBioskop.setDuration(duration);
                            modelDataDetailJadwalBioskop.setJadwal(modelDataJadwalBioskopArrayList);
                            modelDataDetailJadwalBioskopArrayList.add(modelDataDetailJadwalBioskop);
                        }
                    } else {
                        showSnackBar(getResources().getString(R.string.str_gagal_ambil_data));
                    }
                    getPosterFilm();
                } catch (JSONException jsone) {
                    jsone.printStackTrace();
                    showSnackBar(getResources().getString(R.string.str_gagal_ambil_data));
                    autoCompleteTextViewInputNamaKota.requestFocus();
                    stopLoadingView();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                showSnackBar(getResources().getString(R.string.str_gagal_ambil_data));
                stopLoadingView();
            }
        });
        jsonObjectRequestDataJadwalBioskop.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequestDataJadwalBioskop);
    }

    private void getPosterFilm() {
        for(int a = 0; a < modelDataDetailJadwalBioskopArrayList.size(); a++) {
            final int b = a;
            final String judulFilm = modelDataDetailJadwalBioskopArrayList.get(a).getMovie().replaceAll(" ", "+").replaceAll(":", "");
            Log.d(TAG, "judulFilm: " + judulFilm);
            urlDataTheMovieDb = "http://api.themoviedb.org/3/search/movie?api_key=fe71629f457c2466875babf7fbe5bb6c&query=";
            urlDataTheMovieDb += judulFilm;
            Log.d(TAG, "urlDataTheMovieDb: " + urlDataTheMovieDb);
            JsonObjectRequest jsonObjectRequestMovieDb = new JsonObjectRequest(Request.Method.GET, urlDataTheMovieDb, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArrayDataMovieDb = response.getJSONArray("results");
                        Log.d(TAG, "jsonArrayDataMovieDb: " + jsonArrayDataMovieDb.length() + " untuk judul: " + judulFilm);
                        String posterPath = "-";
                        if (jsonArrayDataMovieDb.length() > 0) {
                            JSONObject jsonObjectDataMovieDb = jsonArrayDataMovieDb.getJSONObject(0);
                            posterPath = jsonObjectDataMovieDb.getString("poster_path");
                            modelDataDetailJadwalBioskopArrayList.get(b).setPoster(posterPath);
                            Log.d(TAG, "masuk jsonArrayDataMovieDb > 1 : " + judulFilm);

                        }
                        Log.d(TAG, "posterPath: " + posterPath);
                    } catch (JSONException jsone) {
                        jsone.printStackTrace();
                        stopLoadingView();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    stopLoadingView();

                }
            });
            requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(jsonObjectRequestMovieDb);
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stopLoadingView();
                gotoJadwalBioskop();
            }
        }, 10000);
    }


    private void gotoJadwalBioskop() {
        Intent intentListJadwalBioskop = new Intent(this, JadwalBioskopActivity.class);
        intentListJadwalBioskop.putExtra("dataDetailJadwalBioskop", modelDataDetailJadwalBioskopArrayList);
        intentListJadwalBioskop.putExtra("dataJadwalBioskop", modelDataJadwalBioskopArrayList);
        startActivity(intentListJadwalBioskop);
    }

    private void showSnackBar(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .show();
    }

    private void startLoadingView() {
        autoCompleteTextViewInputNamaKota.setEnabled(false);
        btnCari.setEnabled(false);
        loadingView.start();
        relativeLayoutLoadingContainer.setVisibility(View.VISIBLE);
    }

    private void stopLoadingView() {
        loadingView.stop();
        relativeLayoutLoadingContainer.setVisibility(View.INVISIBLE);
        autoCompleteTextViewInputNamaKota.setEnabled(true);
        btnCari.setEnabled(true);
    }
}
