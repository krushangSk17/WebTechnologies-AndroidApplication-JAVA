package com.example.hw9_krushang4;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab2_favorites#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab2_favorites extends Fragment implements RecyclerViewInterface {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
        View view;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    static Fragment current;

    event_recycler_adapter adapter;

    public Tab2_favorites() {

        current = this;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(adapter!=null){
            ArrayList<eventObject> favList = getCurrentList();
            adapter.refreshData(favList);

            if(favList.isEmpty() || favList == null){

//                view.findViewById(R.id.favouriteCycler).setVisibility(View.GONE);
                view.findViewById(R.id.favoritecard).setVisibility(View.VISIBLE);
            }else {

//                view.findViewById(R.id.favouriteCycler).setVisibility(View.VISIBLE);
                view.findViewById(R.id.favoritecard).setVisibility(View.GONE);
            }

        }



    }

    public static void refresh(){
        current.onResume();
    }

    ArrayList<eventObject> getCurrentList(){

        ArrayList favList = new ArrayList<eventObject>();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Favorites", MODE_PRIVATE);

        Map<String, ?> allEntries = sharedPreferences.getAll();
        Gson gson = new Gson();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {

            String value = entry.getValue().toString();

            JSONObject fav_obj = null;
            try {
                fav_obj = new JSONObject(value);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            eventObject fav = new eventObject(fav_obj);

            favList.add(fav);
        }
        return favList;
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab2_favorites.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab2_favorites newInstance(String param1, String param2) {
        Tab2_favorites fragment = new Tab2_favorites();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void itemOnClick(int position) {
        Intent intent = new Intent(getContext(), EventMainActivity2.class);
        ArrayList<eventObject> eventObjectArrayList =getCurrentList();
        intent.putExtra("eventObjIndex", position);
        if(position>=0 && position <= eventObjectArrayList.size()){
            String eventObject = eventObjectArrayList.get(position).MainEventTMObject;
            intent.putExtra("eventObject",eventObject);
        }
        startActivity(intent);
    }

    public void addFavourite(int position, View view) {

        ImageView favoriteButton = (ImageView) view;
        ArrayList<eventObject> eventObjectArrayList =getCurrentList();
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
            search_view.refresh();


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
            search_view.refresh();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab2_favorites, container, false);
//
        ArrayList favList = getCurrentList();
        RecyclerView table = view.findViewById(R.id.favouriteCycler);
        adapter = new event_recycler_adapter(getContext(),favList,this);

        table.setAdapter(adapter);
        table.setLayoutManager(new LinearLayoutManager(getContext()));



        // Inflate the layout for this fragment
        return view;
}


}