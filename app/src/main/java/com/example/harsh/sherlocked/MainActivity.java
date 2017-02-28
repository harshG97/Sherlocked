package com.example.harsh.sherlocked;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.widget.Toast;

import com.example.harsh.sherlocked.extra.ApiClient;
import com.example.harsh.sherlocked.extra.ApiInterface;

import com.example.harsh.sherlocked.extra.Season;
import com.example.harsh.sherlocked.extra.SeasonAdapter;
import com.example.harsh.sherlocked.extra.SeasonResponse;

import java.util.ArrayList;
import java.util.List;

import android.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = EpiActivity.class.getSimpleName();
    SeasonAdapter mAdapter;

    List<Season> seasons = new ArrayList<>();
   // final RecyclerView mrecyclerView = (RecyclerView) findViewById(R.id.seasonlist);




    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "80ae88432cc909771e2db7faf48150b4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_LONG).show();
            return;
        }

       // prepareMovieData(); Log.i("seasons", seasons.toString());
        final RecyclerView mrecyclerView = (RecyclerView) findViewById(R.id.seasonlist);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
       // mAdapter = new SeasonAdapter(seasons, R.layout.season_row, getApplicationContext());
       // mrecyclerView.setAdapter(mAdapter);
        //Log.i("seasons", seasons.toString());
       // prepareMovieData();
        // SeasonAdapter mAdapter;

          ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<SeasonResponse> call = apiService.getSeasonList(API_KEY);
        call.enqueue(new Callback<SeasonResponse>() {
            @Override
            public void onResponse(Call<SeasonResponse> call, Response<SeasonResponse> response) {
                int statusCode = response.code();
                 seasons = response.body().modify(response.body().getResults());
                Log.i("seasons", seasons.toString());
               // mAdapter.notifyDataSetChanged();
                mAdapter = new SeasonAdapter(seasons, R.layout.season_row, getApplicationContext());
                mrecyclerView.setAdapter(mAdapter);
                mAdapter.setOnItemClickListener(onItemClickListener);

            }

            @Override
            public void onFailure(Call<SeasonResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }
    SeasonAdapter.OnItemClickListener onItemClickListener = new SeasonAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            Intent intent = new Intent(MainActivity.this, EpiActivity.class);
            ArrayList<Integer> pos = new ArrayList<>();
            pos.add(0,position);
            System.out.println(position);
            intent.putExtra("SEASON" , Integer.toString(position+1) );
            startActivity(intent);
            //Toast.makeText(EpiActivity.this, "Clicked " + position, Toast.LENGTH_SHORT).show();
        }
    };




    private void prepareMovieData() {
        Season ssn  = new Season("pp", "Action & Adventure", "1");
        seasons.add(ssn);

        ssn = new Season("Inside Out", "Animation, Kids & Family", "2");
        seasons.add(ssn);

        ssn = new Season("Star Wars: Episode VII - The Force Awakens", "Action", "3");
        seasons.add(ssn);

        ssn = new Season("Shaun the Sheep", "Animation", "4");
        seasons.add(ssn);

        ssn = new Season("The Martian", "Science Fiction & Fantasy", "5");
        seasons.add(ssn);

        ssn = new Season("Mission: Impossible Rogue Nation", "Action", "6");
        seasons.add(ssn);



       // mAdapter.notifyDataSetChanged();
    }
}

