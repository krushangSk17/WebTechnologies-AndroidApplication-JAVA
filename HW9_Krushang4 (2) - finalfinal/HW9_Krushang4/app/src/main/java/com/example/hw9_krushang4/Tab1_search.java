package com.example.hw9_krushang4;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.*;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.android.material.snackbar.Snackbar;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab1_search#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab1_search extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner spinner;
    public Map<String, String> categorySegmentId;
    //took reference from chatGPT
    public Tab1_search() {
        categorySegmentId = new HashMap<>();
        categorySegmentId.put("All", "");
        categorySegmentId.put("Music", "KZFzniwnSyZfZ7v7nJ");
        categorySegmentId.put("Sports", "KZFzniwnSyZfZ7v7nE");
        categorySegmentId.put("Arts & Theatre", "KZFzniwnSyZfZ7v7na");
        categorySegmentId.put("Film", "KZFzniwnSyZfZ7v7nn");
        categorySegmentId.put("Miscellaneous", "KZFzniwnSyZfZ7v7n1");
    }
//took reference from chatGPT
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab1_search.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab1_search newInstance(String param1, String param2) {
        Tab1_search fragment = new Tab1_search();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    //took reference from chatGPT
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void searchPy(String keywordurl,String categoryurl,String distanceurl,String lat,String lng,View view){
        String url = "https://krushang-neso-node.wl.r.appspot.com/search?";
        url += "keyword=" + keywordurl +
                "&segmentId=" + categoryurl +
                "&radius=" + distanceurl +
                "&lat=" + lat +
                "&lng=" + lng;

        System.out.println("URL: "+url);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("in","in bundle");


                        System.out.println("Response is: "+ response);
                        search_view fragment = new search_view();


                        view.findViewById(R.id.cardView).setVisibility(View.GONE);


                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frameLayout, fragment);
                        fragmentTransaction.commit();


                        Bundle bundle = new Bundle();
                        String eventsJson = "";
                        JSONObject jsonResponse = null;
                        try {
                            jsonResponse = new JSONObject(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            if (jsonResponse != null && jsonResponse.has("_embedded")) {
                                JSONObject embedded = jsonResponse.getJSONObject("_embedded");
                                if (embedded != null && embedded.has("events")) {
                                    JSONArray events = embedded.getJSONArray("events");
                                    eventsJson = events.toString();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        bundle.putString("data",eventsJson);
                        fragment.setArguments(bundle);
                        System.out.println("bundle"+bundle);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
                error.printStackTrace();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
    //took reference from chatGPT
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tab1_search, container, false);

        // Initialize the spinner and set its adapter
        spinner = rootView.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(), R.array.spinner_options, R.layout.spinner_item);

        //        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //        aa hatayu to bhenchod black thai gayu

        spinner.setAdapter(adapter);

//took reference from chatGPT
        // Set a listener to handle spinner item selection
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = parent.getItemAtPosition(position).toString();
                // Handle selected option
            }
            //took reference from chatGPT
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle no selection
            }
        });
//took reference from chatGPT
        Switch switch1 = rootView.findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switch1.setThumbTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                    switch1.setTrackTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                } else {
                    switch1.setThumbTintList(ColorStateList.valueOf(getResources().getColor(R.color.grey)));
                    switch1.setTrackTintList(ColorStateList.valueOf(getResources().getColor(R.color.grey)));
                }
            }
        });

        AutoCompleteTextView searchInput = rootView.findViewById(R.id.search_input);
        String suggestApi = "https://krushang-neso-node.wl.r.appspot.com/suggest?keyword=";
        RequestQueue queue = Volley.newRequestQueue(requireContext());
//took reference from chatGPT
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called to notify you that the text is about to change
            }
            ProgressBar progressBar = rootView.findViewById(R.id.progressBarPopular);
            //took reference from chatGPT
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This method is called to notify you that the text has changed
                String query = s.toString().trim(); // Get the current query from the EditText
                if (query.length() >= 1) { // Check if the query is at least 3 characters long
                    // Show the loading spinner
                    progressBar.setVisibility(View.VISIBLE);

                    // Call the API endpoint to get the autocomplete suggestions
                    // You can use Retrofit, OkHttp, Volley, or any other library to make the request
                    // Pass the query as a parameter to the API call
                    // Once you receive the response, parse the JSON data and update the UI

                    //took reference from chatGPT
                    String url = suggestApi + query;
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Process the response
                                    progressBar.setVisibility(View.GONE); // Hide the loading spinner
                                    System.out.println("respose is here: "+ response);
                                    try {
                                        JSONObject jsonResponse = new JSONObject(response);
                                        JSONObject jsonEmbedded = jsonResponse.getJSONObject("_embedded");
                                        JSONArray attractionObjects = jsonEmbedded.getJSONArray("attractions");

                                        ArrayList<String> suggestedEventArray = new ArrayList<>();
                                        for (int i = 0; i < attractionObjects.length(); i++) {
                                            JSONObject attractionObject = attractionObjects.getJSONObject(i);
                                            String eventName = attractionObject.getString("name").trim();
                                            suggestedEventArray.add(eventName);
                                        }
//took reference from chatGPT
                                        // Create an ArrayAdapter to populate the AutoCompleteTextView dropdown
                                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.dropdown_item_layout, suggestedEventArray) {
                                            @Override
                                            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                                View view = super.getDropDownView(position, convertView, parent);
                                                view.setBackgroundColor(getResources().getColor(android.R.color.black));
                                                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                                                textView.setTextColor(getResources().getColor(android.R.color.white));
                                                return view;
                                            }
                                        };
                                        searchInput.setAdapter(adapter);


                                    } catch (JSONException e) {
                                        // Handle the exception appropriately
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        //took reference from chatGPT
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Handle error
                            progressBar.setVisibility(View.GONE); // Hide the loading spinner
                            System.out.println("Error: " + error.getMessage());
                        }
                    });

                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);
                } else {
                    // Hide the loading spinner if the query is less than 3 characters
                    progressBar.setVisibility(View.GONE);
                }
            }
//took reference from chatGPT

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called to notify you that the text has changed and the EditText's content has been updated
            }
        });

        Switch autoDetectSwitch = rootView.findViewById(R.id.switch1);
        EditText keywordEditText = rootView.findViewById(R.id.editTextText3);

        Button myButton = rootView.findViewById(R.id.button2);

//took reference from chatGPT
        autoDetectSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) myButton.getLayoutParams();
                float scale = getResources().getDisplayMetrics().density;
                if (isChecked) {
                    switch1.setThumbTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                    switch1.setTrackTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                    keywordEditText.setVisibility(View.GONE); // hide the EditText field
                    keywordEditText.setText(""); // set the default value

                    params.topMargin = (int) (30 * scale + 0.5f);
                } else {
                    switch1.setThumbTintList(ColorStateList.valueOf(getResources().getColor(R.color.grey)));
                    switch1.setTrackTintList(ColorStateList.valueOf(getResources().getColor(R.color.grey)));
                    keywordEditText.setVisibility(View.VISIBLE); // show the EditText field
                    keywordEditText.setText(""); // clear the default value
                    params.topMargin = (int) (76 * scale + 0.5f);
                }
                myButton.setLayoutParams(params);
            }
        });
//took reference from chatGPT
        Button clearButton = rootView.findViewById(R.id.button2);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                AutoCompleteTextView searchInput = rootView.findViewById(R.id.search_input);
                searchInput.setText("");

                EditText distanceEditText = rootView.findViewById(R.id.editTextNumber);
                distanceEditText.setText("10");

                Spinner categorySpinner = rootView.findViewById(R.id.spinner);
                categorySpinner.setSelection(0);

                Switch autoDetectSwitch = rootView.findViewById(R.id.switch1);
                autoDetectSwitch.setChecked(false);

                EditText locationEditText = rootView.findViewById(R.id.editTextText3);
                locationEditText.setText("");
            }
        });
//took reference from chatGPT
        Button searchButton = rootView.findViewById(R.id.button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AutoCompleteTextView searchInput = rootView.findViewById(R.id.search_input);
                EditText distanceEditText = rootView.findViewById(R.id.editTextNumber);
                EditText locationEditText = rootView.findViewById(R.id.editTextText3);
                Switch autoDetectSwitch = rootView.findViewById(R.id.switch1);

                String keyword = searchInput.getText().toString();
                String location = locationEditText.getText().toString();
                String distance = distanceEditText.getText().toString();
                boolean isswitched = autoDetectSwitch.isChecked();

                if (TextUtils.isEmpty(keyword) || TextUtils.isEmpty(distance) || (TextUtils.isEmpty(location) && !isswitched)) {
                    Snackbar.make(view, "Please fill all fields", Snackbar.LENGTH_SHORT).show();

                    return;
                }



                AutoCompleteTextView keywordbox = rootView.findViewById(R.id.search_input);
                String keywordtext = keywordbox.getText().toString();
                String keywordurl = keywordtext.replace(" ", "+");

                EditText distancebox = rootView.findViewById(R.id.editTextNumber);
                String distanceurl = distancebox.getText().toString();

                Spinner categorybox = rootView.findViewById(R.id.spinner);
                String categoryraw = categorybox.getSelectedItem().toString();
                String categoryurl = categorySegmentId.get(categoryraw);

                // Proceed with other functionality
                // ...
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                if(isswitched){

                    String urlip = "https://ipinfo.io/?token=3d465085bcd64c";
//took reference from chatGPT
                    Gson gson = new Gson();
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, urlip,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    JsonObject ip_info = gson.fromJson(response,JsonObject.class);
                                    String location = ip_info.get("loc").getAsString();
                                    String[] parts = location.split(",");
                                    String lat = parts[0].trim();
                                    String lng = parts[1].trim();

                                    System.out.println("location from ip: "+lat+","+lng);
                                    //Log.d("ipinfo", keywordurl + " " + category + " " + distanceurl + " " + locationCheck + " " + location + " " + lat + " " + lng);
                                    searchPy(keywordurl, categoryurl, distanceurl, lat, lng,rootView);
                                    //
                                }
                            }, new Response.ErrorListener() {

                        //took reference from chatGPT
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    queue.add(stringRequest);
                }else{

                    //took reference from chatGPT
                    String str = location.trim();
                    String plusReplaced = str.replace(" ", "+");
                    String geokey = "&key=AIzaSyD1U4ukw23WPfyTUEQU-tvhhCOPW11aguQ";
                    String geo = "https://maps.googleapis.com/maps/api/geocode/json?address=";
                    String urlgeo = geo + plusReplaced + geokey;

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, urlgeo,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        JSONObject location = jsonObject.getJSONArray("results")
                                                .getJSONObject(0)
                                                .getJSONObject("geometry")
                                                .getJSONObject("location");
                                        String lat = location.getString("lat");
                                        String lng = location.getString("lng");

                                        System.out.println("location from geocode: "+lat+","+lng);
                                      searchPy(keywordurl, categoryurl, distanceurl, lat, lng,rootView);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        //took reference from chatGPT
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    queue.add(stringRequest);
                }
            }
        });

        Button button = rootView.findViewById(R.id.button);
        button.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.green));
        return rootView;
    }
}
