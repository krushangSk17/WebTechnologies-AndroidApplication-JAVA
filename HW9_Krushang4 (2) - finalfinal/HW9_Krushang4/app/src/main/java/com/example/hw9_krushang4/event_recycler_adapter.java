package com.example.hw9_krushang4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

//took reference from youtube video
//https://www.youtube.com/watch?v=7GPUpvcU1FE

public class event_recycler_adapter extends RecyclerView.Adapter<event_recycler_adapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<eventObject> eventArray;

    Picasso picasso = Picasso.get();
    //Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
    public event_recycler_adapter(Context context,ArrayList<eventObject> eventArray,RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.eventArray = eventArray;
        this.recyclerViewInterface = recyclerViewInterface;
    }
    private void updateFavButton(ImageView favButton,Boolean isFavourite){
        if(isFavourite){
            favButton.setImageResource(R.drawable.heart_filled);
        }
        else{favButton.setImageResource(R.drawable.heart_outline);
        }
    }
    //Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
    public void refreshData(ArrayList<eventObject> eventObjectArrayList){
        this.eventArray = eventObjectArrayList;
        notifyDataSetChanged();
    }
    //Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
    @NonNull
    @Override
    public event_recycler_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_recyclerview,parent,false);
        return new event_recycler_adapter.MyViewHolder(view,recyclerViewInterface);
    }
    //Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
    @Override
    public void onBindViewHolder(@NonNull event_recycler_adapter.MyViewHolder holder, int position) {
        try{
            holder.eventName.setText(eventArray.get(position).eventName);
            holder.eventVenueName.setText(eventArray.get(position).eventVenue);
            holder.eventGenre.setText(eventArray.get(position).eventOneGenre);
            holder.eventDate.setText(eventArray.get(position).eventDate);
            holder.eventTime.setText(eventArray.get(position).eventTime);

//Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
            updateFavButton(holder.fav, eventArray.get(position).getIsFav(context));

            picasso.load(eventArray.get(position).eventUri).into(holder.eventImage);
            holder.eventName.setSelected(true);
            holder.eventName.setSingleLine(true);
            holder.eventVenueName.setSelected(true);
            holder.eventVenueName.setSingleLine(true);
        }catch (Exception e){

        }
    }
    //Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
    @Override
    public int getItemCount() {
        return eventArray.size();
    }
    //Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView eventImage;
        TextView eventName;
        TextView eventVenueName;
        TextView eventGenre;
        TextView eventDate;
        TextView eventTime;

        ImageView fav;

        //Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
        public MyViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            eventImage = itemView.findViewById(R.id.eventImageId);
            eventName = itemView.findViewById(R.id.eventNameId);
            eventVenueName = itemView.findViewById(R.id.eventVenueNameId);
            eventGenre = itemView.findViewById(R.id.eventGenreId);
            eventDate = itemView.findViewById(R.id.eventDateId);
            eventTime = itemView.findViewById(R.id.eventTimeId);
            fav = itemView.findViewById(R.id.eventLikeId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.itemOnClick(pos);
                        }
                    }
                }
            });

            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface!=null){
                        int pos = getAdapterPosition();
                        if(pos!=RecyclerView.NO_POSITION){
                            recyclerViewInterface.addFavourite(pos,v);
                        }
                    }
                }
            });}}
}