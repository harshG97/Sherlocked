package com.example.harsh.sherlocked;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.widget.Toast;

import com.example.harsh.sherlocked.extra.ApiClient;

import com.example.harsh.sherlocked.extra.ApiInterface2;
import com.example.harsh.sherlocked.extra.Episode;
import com.example.harsh.sherlocked.extra.EpisodeAdapter;
import com.example.harsh.sherlocked.extra.EpisodeResponse;


import java.util.ArrayList;
import java.util.List;

import android.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeActivity extends AppCompatActivity {

    private static final String TAG = EpiActivity.class.getSimpleName();
    //private ArrayList<Integer> SEASON;
    EpisodeAdapter mAdapter;

    String SEASON;
    List<Episode> episodes = new ArrayList<>();
    // final RecyclerView mrecyclerView = (RecyclerView) findViewById(R.id.seasonlist);




    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "80ae88432cc909771e2db7faf48150b4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode);

       // SEASON = getIntent().getStringExtra("SEASON");
       // int i = SEASON.get(0);
       // Log.i("season", SEASON);
        //Toast.makeText(getApplicationContext(), "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_LONG).show();

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_LONG).show();
            return;
        }

        // prepareMovieData(); Log.i("seasons", seasons.toString());
        final RecyclerView mrecyclerView = (RecyclerView) findViewById(R.id.episodelist);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // mAdapter = new SeasonAdapter(seasons, R.layout.season_row, getApplicationContext());
        // mrecyclerView.setAdapter(mAdapter);
        //Log.i("seasons", seasons.toString());
        // prepareMovieData();
        // SeasonAdapter mAdapter;

        ApiInterface2 apiService =
                ApiClient.getClient().create(ApiInterface2.class);

        Call<EpisodeResponse> call = apiService.getEpisodeList( API_KEY);
        call.enqueue(new Callback<EpisodeResponse>() {
            @Override
            public void onResponse(Call<EpisodeResponse> call, Response<EpisodeResponse> response) {
                int statusCode = response.code();
                episodes = response.body().getResults();
                Log.i("seasons", episodes.toString());
                // mAdapter.notifyDataSetChanged();
              //  mAdapter = new EpisodeAdapter(episodes, R.layout.episode_row, getApplicationContext());
                mrecyclerView.setAdapter(mAdapter);
                mAdapter.setOnItemClickListener(onItemClickListener);

            }

            @Override
            public void onFailure(Call<EpisodeResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });


    }
    EpisodeAdapter.OnItemClickListener onItemClickListener = new EpisodeAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            Toast.makeText(EpisodeActivity.this, "Clicked " + position, Toast.LENGTH_SHORT).show();
        }
    };





}

