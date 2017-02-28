package com.example.harsh.sherlocked.extra;

/**
 * Created by Harsh on 25-12-2016.
 */

//import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import java.util.List;

import static com.example.harsh.sherlocked.R.id.mainHolder;

public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.SeasonViewHolder> {

    private List<Season> seasons = new ArrayList<>();
    private int rowLayout;
    private Context context;

     OnItemClickListener mItemClickListener;


    public  class SeasonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        CardView ssnLayout;
        TextView ssn;
        //TextView data;
        ImageView image;
        //String imgSrc;
       // TextView movieDescription;
        TextView rating;
        LinearLayout Holder;
      // private OnItemClickListener mItemClickListener;


        public SeasonViewHolder(View v) {
            super(v);
            ssnLayout = (CardView) v.findViewById(R.id.ssnCard);
            ssn = (TextView) v.findViewById(R.id.ssn);
            image =(ImageView) v.findViewById(R.id.ssnImage);
            Holder=(LinearLayout)v.findViewById(R.id.mainHolder);
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
        this.mItemClickListener =  mItemClickListener;
    }

    public SeasonAdapter(List<Season> seasons, int rowLayout, Context context) {
        this.seasons = seasons;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public SeasonAdapter.SeasonViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new SeasonViewHolder(view);
    }


    @Override
    public void onBindViewHolder(SeasonViewHolder holder, final int position) {



            holder.ssn.setText(("Season " + seasons.get(position).getSsn()).toString());
            // holder.imgSrc =(seasons.get(position).getPosterPath());

            // add the code for downloading the image and putting it in image

            ImageDownloader task = new ImageDownloader();
            Bitmap myImage;

            try {
                myImage = task.execute("http://image.tmdb.org/t/p/original" + (seasons.get(position).getPosterPath()).toString()).get();

                holder.image.setImageBitmap(myImage);

            } catch (Exception e) {

                e.printStackTrace();

            }


            // holder.image = setImage( R.drawable.shrlck2 );
            //holder.movieDescription.setText(seasons.get(position).getOverview());
            //holder.rating.setText(seasons.get(position).getVoteAverage().toString());

    }

    public void setImage(String scr){

    }

    @Override
    public int getItemCount() {
        return seasons.size() ;
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