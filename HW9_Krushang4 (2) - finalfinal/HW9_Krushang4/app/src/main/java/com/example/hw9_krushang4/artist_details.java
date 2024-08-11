package com.example.hw9_krushang4;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link artist_details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class artist_details extends Fragment {
    //Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public artist_details() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment artist_details.
     */
    // TODO: Rename and change types and number of parameters
    public static artist_details newInstance(String param1, String param2) {
        artist_details fragment = new artist_details();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    //Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    //Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artist_details, container, false);

        Bundle bundle = getArguments();
        System.out.println("bundle is coming 1");
        System.out.println(bundle);

        ArrayList<artistObject> artistObjectArrayList = new ArrayList<>();

        if (bundle != null) {
            System.out.println("bundle is coming 2");
            String jsonStr = bundle.getString("jsonObject");
            String artistNames = bundle.getString("artistNames");
            String[] artists = artistNames.split("\\|");


            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(jsonStr);

            } catch (Exception e) {
            }

            if(jsonObject!=null){
                for (String artist : artists) {
                    try {
                        JSONObject artistObj = jsonObject.getJSONObject(artist.trim());
                        artistObject myArtistObj = new artistObject(artistObj);
                        artistObjectArrayList.add(myArtistObj);

                    } catch (JSONException e) {
                    }
                }
//Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
                //call recycler view here.
                RecyclerView recyclerView = view.findViewById(R.id.recyclerContainerArtists);

                Artist_RecyclerViewAdapter adapter = new Artist_RecyclerViewAdapter(getContext(),artistObjectArrayList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


                view.findViewById(R.id.artistMusiccard).setVisibility(View.GONE);

            }

        }

            return view;
    }
}