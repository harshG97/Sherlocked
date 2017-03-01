package com.example.harsh.sherlocked.extra;

/**
 * Created by Harsh on 30-12-2016.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.harsh.sherlocked.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder> {

    private ArrayList<HashMap<String, String>> episodes = new ArrayList<>();
    private int rowLayout;
    private Context context;

    OnItemClickListener mItemClickListener;


    public class EpisodeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView epiLayout;
        TextView epi;
       // TextView date;
        ImageView image;
        //String imgSrc;
        // TextView movieDescription;
        TextView rating;
        LinearLayout Holder;
        // private OnItemClickListener mItemClickListener;


        public EpisodeViewHolder(View v) {
            super(v);
            epiLayout = (CardView) v.findViewById(R.id.epiCard);
            epi = (TextView) v.findViewById(R.id.epi);
            image = (ImageView) v.findViewById(R.id.epiImage);
           // date = (TextView) v.findViewById(R.id.date);
            rating =(TextView) v.findViewById(R.id.rating);
            Holder = (LinearLayout) v.findViewById(R.id.mainEpiHolder);
            Holder.setOnClickListener(this);
            // String imgSrc;
            // data = (TextView) v.findViewById(R.id.subtitle);
            //  movieDescription = (TextView) v.findViewById(R.id.description);
            // rating = (TextView) v.findViewById(R.id.rating);
        }


        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getPosition());
            }

        }
        // Holder.setOnClickListener(this);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public EpisodeAdapter(ArrayList<HashMap<String, String>> episodes, int rowLayout, Context context) {
        this.episodes = episodes;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public EpisodeAdapter.EpisodeViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new EpisodeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(EpisodeViewHolder holder, final int position) {


        holder.epi.setText(  episodes.get(position).get("epi")+". " + episodes.get(position).get("name")  );

        holder.rating.setText(episodes.get(position).get("rating"));

        // holder.imgSrc =(episodes.get(position).getPosterPath());

        // add the code for downloading the image and putting it in image

        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;

        try {
            myImage = task.execute("http://image.tmdb.org/t/p/original" + (episodes.get(position).get("pic"))).get();

            holder.image.setImageBitmap(myImage);

        } catch (Exception e) {

            e.printStackTrace();

        }


        // holder.image = setImage( R.drawable.shrlck2 );
        //holder.movieDescription.setText(episodes.get(position).getOverview());
        //holder.rating.setText(episodes.get(position).getVoteAverage().toString());

    }

    public void setImage(String scr) {

    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }


    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... urls) {

            try {

                URL url = new URL(urls[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream inputStream = connection.getInputStream();

                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);

                return myBitmap;


            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }

            return null;

        }


    }
}
