package com.example.hw9_krushang4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class EventMainActivity2<ShareLinkContent, ShareDialog> extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    private ShareDialog shareDialog;

    public void Toggle(View view) {
        TextView textView = (TextView) view;
        int maxLinesExpanded = 100;
        int maxLinesCollapsed = 3;

        if (textView.getMaxLines() == maxLinesCollapsed) {
            textView.setMaxLines(maxLinesExpanded);
        } else {
            textView.setMaxLines(maxLinesCollapsed);
        }
    }

    public void firstTab(View view){

        System.out.println("1");
        ImageView icon = findViewById(R.id.imageMicicon);
        ImageView icon1 = findViewById(R.id.imageView8);
        ImageView icon3 = findViewById(R.id.imageView10);
        icon1.setColorFilter(ContextCompat.getColor(EventMainActivity2.this, R.color.green), PorterDuff.Mode.SRC_IN);
        icon.setColorFilter(ContextCompat.getColor(EventMainActivity2.this, R.color.white), PorterDuff.Mode.SRC_IN);
        icon3.setColorFilter(ContextCompat.getColor(EventMainActivity2.this, R.color.white), PorterDuff.Mode.SRC_IN);
    }

    public void secondTab(View view){

        System.out.println("2");
        ImageView icon = findViewById(R.id.imageMicicon);
        ImageView icon1 = findViewById(R.id.imageView8);
        ImageView icon3 = findViewById(R.id.imageView10);
        icon1.setColorFilter(ContextCompat.getColor(EventMainActivity2.this, R.color.white), PorterDuff.Mode.SRC_IN);
        icon.setColorFilter(ContextCompat.getColor(EventMainActivity2.this, R.color.green), PorterDuff.Mode.SRC_IN);
        icon3.setColorFilter(ContextCompat.getColor(EventMainActivity2.this, R.color.white), PorterDuff.Mode.SRC_IN);
    }

    public void thirdTab(View view){
        System.out.println("3");
        ImageView icon = findViewById(R.id.imageMicicon);
        ImageView icon1 = findViewById(R.id.imageView8);
        ImageView icon3 = findViewById(R.id.imageView10);
        icon1.setColorFilter(ContextCompat.getColor(EventMainActivity2.this, R.color.white), PorterDuff.Mode.SRC_IN);
        icon.setColorFilter(ContextCompat.getColor(EventMainActivity2.this, R.color.white), PorterDuff.Mode.SRC_IN);
        icon3.setColorFilter(ContextCompat.getColor(EventMainActivity2.this, R.color.green), PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_main2);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPagerContainer);

        Fragment[] fragments = new Fragment[] { new event_details(), new artist_details(),new venue_details() };

        MainActivity.MyAdapter adapter = new MainActivity.MyAdapter(this, fragments);
        viewPager.setAdapter(adapter);

        ImageView icon = findViewById(R.id.imageMicicon);
        ImageView icon1 = findViewById(R.id.imageView8);
        ImageView icon3 = findViewById(R.id.imageView10);
        ImageView tweet = findViewById(R.id.tweet);
        ImageView face = findViewById(R.id.face);
        FacebookSdk.setApplicationId("1297719784153389");
        FacebookSdk.setClientToken("2d2c089954f3073f3b15ca0ce11e5cc0");
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this.getApplication());

//        ShareLinkContent content;
//        content = new ShareLinkContent.Builder()
//                .setContentUrl(Uri.parse(yourUrl))
//                .setQuote(yourQuote)
//                .setShareHashtag(new ShareHashtag.Builder()
//                        .setHashtag(yourHashtag)
//                        .build())
//                .build();
        // get references to TabLayout and ViewPager2
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager2 = findViewById(R.id.viewPagerContainer);

// hide them
        tabLayout.setVisibility(View.GONE);
        viewPager2.setVisibility(View.GONE);
        findViewById(R.id.progressBarMainAactivity2).setVisibility(View.VISIBLE);
        findViewById(R.id.imageMicicon).setVisibility(View.GONE);
        findViewById(R.id.imageView8).setVisibility(View.GONE);
        findViewById(R.id.imageView10).setVisibility(View.GONE);

// set a delayed task to show them after 2 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.progressBarMainAactivity2).setVisibility(View.GONE);
                tabLayout.setVisibility(View.VISIBLE);
                viewPager2.setVisibility(View.VISIBLE);
                findViewById(R.id.imageMicicon).setVisibility(View.VISIBLE);
                findViewById(R.id.imageView8).setVisibility(View.VISIBLE);
                findViewById(R.id.imageView10).setVisibility(View.VISIBLE);
            }
        }, 700);

        TabLayoutMediator.TabConfigurationStrategy tabConfigurationStrategy = new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("       Event");
                        break;
                    case 1:
                        tab.setText("       Artist");
                        break;
                    case 2:
                        System.out.println("33");
                        tab.setText("        Venue");
                        break;
                }
            }
        };
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, tabConfigurationStrategy);
        tabLayoutMediator.attach();

        icon.setColorFilter(ContextCompat.getColor(this, R.color.green), PorterDuff.Mode.SRC_IN);
        icon1.setColorFilter(null);
        icon3.setColorFilter(null);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        icon1.setColorFilter(ContextCompat.getColor(EventMainActivity2.this, R.color.green), PorterDuff.Mode.SRC_IN);
                        icon.setColorFilter(ContextCompat.getColor(EventMainActivity2.this, R.color.white), PorterDuff.Mode.SRC_IN);
                        icon3.setColorFilter(ContextCompat.getColor(EventMainActivity2.this, R.color.white), PorterDuff.Mode.SRC_IN);
                        break;
                    case 1:
                        icon1.setColorFilter(ContextCompat.getColor(EventMainActivity2.this, R.color.white), PorterDuff.Mode.SRC_IN);
                        icon.setColorFilter(ContextCompat.getColor(EventMainActivity2.this, R.color.green), PorterDuff.Mode.SRC_IN);
                        icon3.setColorFilter(ContextCompat.getColor(EventMainActivity2.this, R.color.white), PorterDuff.Mode.SRC_IN);
                        break;
                    case 2:
                        icon1.setColorFilter(ContextCompat.getColor(EventMainActivity2.this, R.color.white), PorterDuff.Mode.SRC_IN);
                        icon.setColorFilter(ContextCompat.getColor(EventMainActivity2.this, R.color.white), PorterDuff.Mode.SRC_IN);
                        icon3.setColorFilter(ContextCompat.getColor(EventMainActivity2.this, R.color.green), PorterDuff.Mode.SRC_IN);
                        break;
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        ImageView imageView = findViewById(R.id.imageView3tab);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        int index = getIntent().getIntExtra("eventObjIndex", -1);

        if (index != -1) {

            TextView titleName = findViewById(R.id.eventNameTitle2);

            String selectedSTR = getIntent().getStringExtra("eventObject");
            JSONObject selectedObject = null;
            eventObject eventOBJ = null;
            try {
                selectedObject = new JSONObject(selectedSTR);
                eventOBJ = new eventObject(selectedObject);
            } catch (JSONException e) {
            }





            String eventName = eventOBJ.eventName;
            eventObject finalEventOBJ = eventOBJ;

            tweet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = finalEventOBJ.eventUri.toString(); // Replace with your desired URL

                    try {
                        // Create a Twitter intent with the URL
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://tweet?text=" + Uri.encode(url)));

                        // Check if the Twitter app is installed on the device
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            // Open the Twitter app
                            startActivity(intent);
                        } else {
                            // If the Twitter app is not installed, open the URL in a browser
                            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/intent/tweet?text=" + Uri.encode(url)));
                            startActivity(intent);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                });
            ImageView favButton = findViewById(R.id.heart);
            updateFavButton(favButton,eventOBJ.getIsFav(this));

            favButton.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    addFavourite(finalEventOBJ,v);
                }
            });



            face.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String facebookURL = "https://www.facebook.com/sharer/sharer.php?u="+finalEventOBJ.eventUri.toString()+"&amp;src=sdkpreparse";
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(facebookURL));
                    startActivity(intent);

                }
            });

            event_details eventFrag = new event_details();
            Bundle bundle = new Bundle();
            bundle.putString("selectedSTR",selectedSTR);
            eventFrag.setArguments(bundle);

            fragments[0] = eventFrag; // replace the existing event_details fragment with the new one that has the bundle data

            try{
                titleName.setText(eventName);
                titleName.setSelected(true);
            }catch (Exception e){
            }

            String venueName = eventOBJ.eventVenue;
            if(venueName !=null){
                RequestQueue queue = Volley.newRequestQueue(this);
                String url = "https://krushang-neso-node.wl.r.appspot.com/fetchVenue?venueName="+venueName;

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                try{
//                                    JSONObject jsonObject = new JSONObject(response);
                                    Bundle bundle3 = new Bundle();
                                    bundle3.putString("venueJSONSTR",response);
                                    venue_details venueFrag = new venue_details();

                                    if(venueFrag != null){
                                        venueFrag.setArguments(bundle3);
                                        fragments[2] = venueFrag;
                                    }

                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);


            }

            try {
                TextView artistSpotifytag = findViewById(R.id.artistSpotifytag);
                artistSpotifytag.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                artistSpotifytag.getPaint().setAntiAlias(true);
                artistSpotifytag.setTextColor(Color.GREEN);
            }catch (Exception e){

            }

            Boolean isMusic = eventOBJ.eventMusicFlag;
            String artistsCombined = eventOBJ.eventArtists;







            try {
                if (isMusic) {

                    System.out.println("MUSIC FOUND SO IN IF BLOCK");
                    String artistNames = artistsCombined; // artist names in "A | B | C" format
                    String[] artists = artistNames.split("\\|"); // split the artist names by '|'
                    JSONObject json = new JSONObject(); // to store the responses from all the requests

                    RequestQueue queue = Volley.newRequestQueue(this); // initialize a Volley request queue


                    for (String artist : artists) {
                        String url = "https://krushang-neso-node.wl.r.appspot.com/fetchArtist?artistName=" + artist.trim(); // construct the API URL with the artist name
                        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response); // parse the JSON response
                                    json.put(artist.trim(), jsonObject); // add the parsed JSON object to the JSON object using artist name as key

                                    if (json.length() == artists.length) {
                                        // all the responses have been received, send the JSON object to the second fragment
                                        Bundle bundle2 = new Bundle();
                                        bundle2.putString("artistNames", artistNames);
                                        bundle2.putString("jsonObject", json.toString());
                                        artist_details artistFrag = new artist_details();

                                        if (artistFrag != null) {
                                            artistFrag.setArguments(bundle2);
                                            fragments[1] = artistFrag;

                                            System.out.println("new bundle2 is set");
                                            System.out.println(bundle2);

                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        });
                        queue.add(request); // add the request to the Volley request queue
                    }
                }else{

                }
            }catch (Exception e){

            }


        } else {
            // handle the case where the index is not found

        }

        adapter.notifyDataSetChanged();

    }
    private void updateFavButton(ImageView favButton,Boolean isFavourite){
        if(isFavourite){
            favButton.setImageResource(R.drawable.heart_filled);
        }
        else{favButton.setImageResource(R.drawable.heart_outline);
        }
    }
    public void addFavourite(eventObject event, View view) {

        ImageView favoriteButton = (ImageView) view;



        Gson gson = new Gson();
        eventObject current_event = event;
        String dataObject = current_event.MainEventTMObject;


        JSONObject ob = null;
        try {
            ob = new JSONObject(dataObject);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        Boolean isFavorites = current_event.getIsFav(this);

        // Get SharedPreferences instance
        SharedPreferences sharedPreferences = getSharedPreferences("Favorites", MODE_PRIVATE);

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



        } else {
            // The button is currently deselected (heart outline)
            favoriteButton.setImageResource(R.drawable.heart_filled);
            current_event.isFav = true;

            // Add your favorite logic here
            Snackbar.make(view,current_event.eventName + " - added to favorites", Snackbar.LENGTH_SHORT).show();
            editor.putString(current_event.eventId, current_event.MainEventTMObject);
            System.out.println("editor === "+editor);
            editor.apply();

        }

    }




}