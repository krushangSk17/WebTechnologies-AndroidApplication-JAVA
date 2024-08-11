package com.example.hw9_krushang4;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link search_view#newInstance} factory method to
 * create an instance of this fragment.
 */
public class search_view extends Fragment implements RecyclerViewInterface{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    event_recycler_adapter adapter;
    static Fragment current;
    View view;
    public search_view() {
        current = this;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment search_view.
     */
    // TODO: Rename and change types and number of parameters
    public static search_view newInstance(String param1, String param2) {
        search_view fragment = new search_view();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ArrayList<eventObject> eventObjectArrayList = new ArrayList<eventObject>();

    @Override
    public void onResume() {
        super.onResume();

        if(adapter != null){
            RecyclerView table = view.findViewById(R.id.eventRecyclerView);
            adapter = new event_recycler_adapter(getContext(),eventObjectArrayList,this);
            table.setAdapter(adapter);
            table.setLayoutManager(new LinearLayoutManager(getContext()));

//            if(eventObjectArrayList.isEmpty() || eventObjectArrayList == null){
//                view.findViewById(R.id.eventRecyclerView).setVisibility(View.GONE);
//                view.findViewById(R.id.searchviewcard).setVisibility(View.VISIBLE);
//            }else {
//                view.findViewById(R.id.eventRecyclerView).setVisibility(View.VISIBLE);
//                view.findViewById(R.id.searchviewcard).setVisibility(View.GONE);
//            }
        }
    }

    static public void refresh(){
        current.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String data = getArguments().getString("data");
        System.out.println("passed Arguments: " + data);
        view = inflater.inflate(R.layout.fragment_search_view, container, false);
        ProgressBar progressBar = view.findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);
        RecyclerView recyclerView = view.findViewById(R.id.eventRecyclerView);
        recyclerView.setVisibility(View.GONE);

        // use a Handler to delay the hiding of the progress bar
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // hide the progress bar and show the recycler view
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }, 500);

        // fetch the data
        // ...
        JSONArray dataArray = new JSONArray();
        try{
            dataArray = new JSONArray(data);
            System.out.println("array is");
            System.out.println(dataArray);
        }catch (JSONException e){
            e.printStackTrace();
        }

        for(int i=0;i<dataArray.length();i++){
            try {
                eventObject tempEventObj = new eventObject(dataArray.getJSONObject(i));
                eventObjectArrayList.add(tempEventObj);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        if(eventObjectArrayList.isEmpty() || eventObjectArrayList == null || eventObjectArrayList.size() == 0){
            view.findViewById(R.id.eventRecyclerView).setVisibility(View.GONE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // hide the progress bar and show the recycler view
                    view.findViewById(R.id.searchviewcard).setVisibility(View.VISIBLE);
                }
            }, 500);
        }else {
            view.findViewById(R.id.searchviewcard).setVisibility(View.GONE);
        }

        RecyclerView viewAdapter = view.findViewById(R.id.eventRecyclerView);
        adapter = new event_recycler_adapter(getContext(),eventObjectArrayList,this);
        viewAdapter.setAdapter(adapter);
        viewAdapter.setLayoutManager(new LinearLayoutManager(getContext()));

        return search_view.this.view;
    }


    @Override
    public void itemOnClick(int position) {
        Intent intent = new Intent(getContext(), EventMainActivity2.class);

        intent.putExtra("eventObjIndex", position);
        if(position>=0 && position <= eventObjectArrayList.size()){
            String eventObject = eventObjectArrayList.get(position).MainEventTMObject;
            intent.putExtra("eventObject",eventObject);
        }
        startActivity(intent);
    }

    public void addFavourite(int position, View view) {

        ImageView favoriteButton = (ImageView) view;

        String dataObject = eventObjectArrayList.get(position).MainEventTMObject;

        Gson gson = new Gson();
        eventObject current_event = eventObjectArrayList.get(position);


        JSONObject ob = null;
        try {
            ob = new JSONObject(dataObject);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        Boolean isFavorites = current_event.getIsFav(getContext());

        // Get SharedPreferences instance
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Favorites", MODE_PRIVATE);

        // Save the JSON string representation of the data object

        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (isFavorites) {
            // The button is currently selected (heart filled)
            favoriteButton.setImageResource(R.drawable.heart_outline);
            current_event.isFav = false;

            // Add your unfavorite logic here
            Snackbar.make(view, current_event.eventName + " - removed from favorites", Snackbar.LENGTH_SHORT).show();
            editor.remove(current_event.eventId);
            editor.apply();
            refresh();
            Tab2_favorites.refresh();


        } else {
            // The button is currently deselected (heart outline)
            favoriteButton.setImageResource(R.drawable.heart_filled);
            current_event.isFav = true;

            // Add your favorite logic here
            Snackbar.make(view,current_event.eventName + " - added to favorites", Snackbar.LENGTH_SHORT).show();
            editor.putString(current_event.eventId, current_event.MainEventTMObject);
            System.out.println("editor === "+editor);
            editor.apply();
            refresh();
            Tab2_favorites.refresh();
        }

}


}