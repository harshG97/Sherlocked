package com.example.harsh.sherlocked;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.harsh.sherlocked.extra.EpisodeAdapter;
import com.example.harsh.sherlocked.extra.HttpHandler;
import com.example.harsh.sherlocked.extra.dialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class EpiActivity extends AppCompatActivity {

    private String TAG = EpiActivity.class.getSimpleName();
    private String url;
    Context context;
    private ProgressDialog pDialog;
    EpisodeAdapter mAdapter;
    dialog epiDialog = new dialog();
    String ssn;
    private ListView lv;
    private RecyclerView mrecyclerView;
//getIntent().getStringExtra("SEASON")
    //"http://api.themoviedb.org/3/"
    // URL to get contacts JSON
   // private static String url = "http://api.themoviedb.org/3/" + "tv/19885/season/" + getIntent().getStringExtra("SEASON") ;

    ArrayList<HashMap<String, String>> episodeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode);
        ssn = getIntent().getStringExtra("SEASON");
        url = "http://api.themoviedb.org/3/" + "tv/19885/season/" + getIntent().getStringExtra("SEASON") + "?api_key=80ae88432cc909771e2db7faf48150b4" ;
        episodeList = new ArrayList<>();

        context = this;
         mrecyclerView = (RecyclerView) findViewById(R.id.episodelist);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //lv = (ListView) findViewById(R.id.list);

        new GetEpisodes().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetEpisodes extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(EpiActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray episodes = jsonObj.getJSONArray("episodes");

                    // looping through All Contacts
                    for (int i = 0; i < episodes.length(); i++) {
                        JSONObject c = episodes.getJSONObject(i);

                        String pic = c.getString("still_path");
                        int epi = c.getInt("episode_number");
                        String plot = c.getString("overview");
                        String name = c.getString("name");
                        double rating = c.getDouble("vote_average");
                        String date = c.getString("air_date");

                        // Phone node is JSON Object


                        // tmp hash map for single episode
                        HashMap<String, String> episode = new HashMap<>();

                        // adding each child node to HashMap key => value
                        episode.put("pic", pic);
                        episode.put("name", name);
                        episode.put("epi", Integer.toString(epi));
                        episode.put("plot", plot);
                        episode.put("rating", Double.toString(rating));
                        episode.put("date", date);

                        // adding episode to episode list
                        episodeList.add(episode);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }


            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            mAdapter = new EpisodeAdapter(episodeList, R.layout.episode_row, getApplicationContext());
            mrecyclerView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(onItemClickListener);

        }

    }
    EpisodeAdapter.OnItemClickListener onItemClickListener = new EpisodeAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            Toast.makeText(EpiActivity.this, "Clicked " + position, Toast.LENGTH_SHORT).show();
          //  HashMap<String,String> episode = new HashMap<>();


            epiDialog.showDialog(context, episodeList.get(position), ssn);

        }
    };
}