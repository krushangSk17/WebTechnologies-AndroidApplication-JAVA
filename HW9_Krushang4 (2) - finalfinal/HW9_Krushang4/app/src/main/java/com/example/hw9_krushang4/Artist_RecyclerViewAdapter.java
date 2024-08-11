package com.example.hw9_krushang4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
//Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
public class Artist_RecyclerViewAdapter extends RecyclerView.Adapter<Artist_RecyclerViewAdapter.MyViewHolder> {
    //Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
    Context context;
    ArrayList<artistObject> artistObjectArrayList;
    //Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
    public Artist_RecyclerViewAdapter(Context context, ArrayList<artistObject> artistObjectArrayList){
        this.context = context;
        this.artistObjectArrayList = artistObjectArrayList;
    }

    @NonNull
    @Override
    public Artist_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_recyclerview,parent,false);
        return new Artist_RecyclerViewAdapter.MyViewHolder(view);
    }
    //Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
    @Override
    public void onBindViewHolder(@NonNull Artist_RecyclerViewAdapter.MyViewHolder holder, int position) {
//Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
        holder.artistName.setText(artistObjectArrayList.get(position).artistName);
        holder.artistPopularity.setText(artistObjectArrayList.get(position).artistPopularity);
        holder.progressBar.setProgress(Integer.parseInt(artistObjectArrayList.get(position).artistPopularity));
        holder.artistFollowers.setText(artistObjectArrayList.get(position).artistFollowers);
        holder.artistSpotify.setText(artistObjectArrayList.get(position).artistSpotify);
//Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
        Picasso.get()
                .load(artistObjectArrayList.get(position).artistProfileUri)
                .into(holder.profileUri);

        Picasso.get()
                .load(artistObjectArrayList.get(position).artistAlbum1)
                .into(holder.album1);

        Picasso.get()
                .load(artistObjectArrayList.get(position).artistAlbum2)
                .into(holder.album2);

        Picasso.get()
                .load(artistObjectArrayList.get(position).artistAlbum3)
                .into(holder.album3);


    }

    @Override
    public int getItemCount() {
        return artistObjectArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView profileUri;
        ImageView album1;
        ImageView album2;
        ImageView album3;

        TextView artistName;
        TextView artistFollowers;
        TextView artistPopularity;
        TextView artistSpotify;

        CircularProgressIndicator progressBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            profileUri = itemView.findViewById(R.id.profileUri);
            album1 = itemView.findViewById(R.id.album1);
            album2 = itemView.findViewById(R.id.album2);
            album3 = itemView.findViewById(R.id.album3);

            artistName = itemView.findViewById(R.id.artistName);
            artistFollowers = itemView.findViewById(R.id.artistFollowers);
            artistSpotify = itemView.findViewById(R.id.artistSpotify);
            artistPopularity = itemView.findViewById(R.id.progressText);
            progressBar = itemView.findViewById(R.id.progressBarPopular);


        }
    }
}
