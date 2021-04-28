package com.forbitbd.diploma;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.material.button.MaterialButton;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyListener {

    private EditText roll;
    private MaterialButton btn,btndev;
    private AutoCompleteTextView semester, year;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btndev = findViewById(R.id.developer);
        btndev.setOnClickListener(this);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5474497946967422/7341540413");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.d("YYYYYYY", "onAdLoaded Called");
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

        roll = findViewById(R.id.roll);
        semester = findViewById(R.id.exm_semester);
        String[] exm_semester = getResources().getStringArray(R.array.exm_semester);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, exm_semester);
        semester.setText(arrayAdapter.getItem(0).toString(), false);
        semester.setAdapter(arrayAdapter);

        year = findViewById(R.id.exm_year);
        String[] exm_year = getResources().getStringArray(R.array.exm_year);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,exm_year);
        year.setText(yearAdapter.getItem(0).toString(), false);
        year.setAdapter(yearAdapter);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn){
            String broll = roll.getText().toString();
            if (broll.equals("")) {
                roll.setError("Enter Your Board Roll");
                roll.requestFocus();
            }
            String sem = semester.getText().toString();
            String byear = year.getText().toString();
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
                    .authority("project.riajtech.com")
                    .appendPath("app.php")
                    .appendQueryParameter("roll", broll)
                    .appendQueryParameter("sem", sem)
                    .appendQueryParameter("year", byear);
            String myUrl = builder.build().toString();
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(myUrl)
                    .build();

            Log.d("lllll", "onClick: " + request);
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String mydata = response.body().string();
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mydata.equals("")) {
                                } else {
                                    Dialoge_fragment dialog_fragment = new Dialoge_fragment();
                                    dialog_fragment.setCancelable(false);
                                    Bundle data = new Bundle();
                                    data.putString("data", mydata);
                                    dialog_fragment.setArguments(data);
                                    dialog_fragment.show(getSupportFragmentManager(), "GHGJHGJHGHJ");
                                }
                            }
                        });
                    }
                }
            });
        } else if (id == R.id.developer){
            Intent intent = new Intent(MainActivity.this,ActivityDev.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    public void onRewardButtonClick() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}
