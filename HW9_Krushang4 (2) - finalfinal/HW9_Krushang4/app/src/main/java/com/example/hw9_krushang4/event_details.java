package com.example.hw9_krushang4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link event_details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class event_details extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
    public event_details() {
        // Required empty public constructor
    }
//Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment event_details.
     */
    // TODO: Rename and change types and number of parameters
    public static event_details newInstance(String param1, String param2) {
        event_details fragment = new event_details();
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


        View view = inflater.inflate(R.layout.fragment_event_details, container, false);
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        System.out.println(bundle);
//Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
        if (bundle != null) {
            String selectedSTR = bundle.getString("selectedSTR");

            JSONObject selectedObject = null;
            eventObject eventOBJ = null;
            try {
                selectedObject = new JSONObject(selectedSTR);
                eventOBJ = new eventObject(selectedObject);
            } catch (JSONException e) {
            }
//Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
            LinearLayout tit1 = view.findViewById(R.id.horizontal1);
            TextView ans1 = view.findViewById(R.id.ans1);

            LinearLayout tit2 = view.findViewById(R.id.horizontal2);
            TextView ans2 = view.findViewById(R.id.ans2);

            LinearLayout tit3 = view.findViewById(R.id.horizontal3);
            TextView ans3 = view.findViewById(R.id.ans3);

            LinearLayout tit4 = view.findViewById(R.id.horizontal4);
            TextView ans4 = view.findViewById(R.id.ans4);

            LinearLayout tit5 = view.findViewById(R.id.horizontal5);
            TextView ans5 = view.findViewById(R.id.ans5);

            LinearLayout tit6 = view.findViewById(R.id.horizontal6);
            TextView ans6 = view.findViewById(R.id.ans6);

            LinearLayout tit7 = view.findViewById(R.id.horizontal7);
            TextView ans7 = view.findViewById(R.id.ans7);

            LinearLayout tit8 = view.findViewById(R.id.horizontal8);
            TextView ans8 = view.findViewById(R.id.ans8);

//Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding

            String artistName = eventOBJ.eventArtists;
            String venueName = eventOBJ.eventVenue;
            String eventDate = eventOBJ.eventDate;
            String eventTime = eventOBJ.eventTime;
            String eventGenres = eventOBJ.eventGenres;
            String eventPrice = eventOBJ.eventPriceRange;
            String eventStatusCode = eventOBJ.eventStatusCode;
            Uri eventTicketMasterUri = eventOBJ.eventTicketMasterUri;
            Uri seatMapUri = eventOBJ.eventSeatmap;

            System.out.println(artistName+" %% "+venueName+" %% "+eventDate+" %% "+eventTime+" %% "+eventGenres+" %% "+eventPrice+" %% "+eventStatusCode);
//            USC Trojans Football | San Jose State Spartans Football %% Los Angeles Memorial Coliseum %% 2023-08-26 %% null %% Sports | Football | College %% null %% onsale
            //Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
            String Temp = artistName;
            if( Temp!= null && Temp != "Undefined" && Temp != "undefined"){
                ans1.setText(Temp);
                ans1.setSelected(true);
            }else{
                tit1.setVisibility(View.GONE);
            }

            Temp = venueName;
            if( Temp!= null && Temp != "Undefined" && Temp != "undefined"){
                ans2.setText(Temp);
                ans2.setSelected(true);
            }else{
                tit2.setVisibility(View.GONE);
            }

            Temp = eventDate;
            if( Temp!= null && Temp != "Undefined" && Temp != "undefined"){
                ans3.setText(Temp);
                ans3.setSelected(true);
            }else{
                tit3.setVisibility(View.GONE);
            }

            Temp = eventTime;
            if( Temp!= null && Temp != "Undefined" && Temp != "undefined"){
                ans4.setText(Temp);
                ans4.setSelected(true);
            }else{
                tit4.setVisibility(View.GONE);
            }

            Temp = eventGenres;
            if( Temp!= null && Temp != "Undefined" && Temp != "undefined"){
                ans5.setText(Temp);
                ans5.setSelected(true);
            }else{
                tit5.setVisibility(View.GONE);
            }
            //Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
            Temp = eventPrice;
            if( Temp!= null && Temp != "Undefined" && Temp != "undefined"){
                ans6.setText(Temp);
                ans6.setSelected(true);
            }else{
                tit6.setVisibility(View.GONE);
            }
            //Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
            CardView salecard = view.findViewById(R.id.salecard);
            Temp = eventStatusCode;
            if( Temp!= null){
                ans7.setText(Temp);
                ans7.setSelected(true);

                if(Temp.equals("offsale")){
                    salecard.setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.red));
                } else if (Temp.equals("onsale")) {
                    salecard.setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.green));
                }else if (Temp.equals("cancelled")) {
                    salecard.setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.black));
                }else {
                    salecard.setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.orange));
                }


            }else{
                tit7.setVisibility(View.GONE);
            }

            //Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding

            Uri Temp1 = eventTicketMasterUri;
            if( Temp1!= null){
                tit8.setVisibility(View.VISIBLE);
                ans8.setText(Temp1.toString());
                ans8.setSelected(true);

                TextView textView = (TextView) view.findViewById(R.id.ans8);
                SpannableString content = new SpannableString(Temp1.toString());
                content.setSpan(new UnderlineSpan(), 0, content.length(), 2);
                textView.setText(content);
                textView.setTextColor(getResources().getColor(R.color.green)); // set text color
                textView.setLinkTextColor(getResources().getColor(R.color.green)); // set link color

                ans8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse(ans8.getText().toString());
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
            }else{
                 tit8.setVisibility(View.GONE);
            }


            ImageView imageView = view.findViewById(R.id.imageView4);

// Load the image into the ImageView using Glide library
            Picasso.get().load(seatMapUri).into(imageView);
        }



        return view;
    }
}