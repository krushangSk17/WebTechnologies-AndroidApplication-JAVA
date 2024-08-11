package com.example.hw9_krushang4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link venue_details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class venue_details extends Fragment implements OnMapReadyCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private double lat = 34.2;
    private double lng =-118.4;
    private Marker pinMarker;

    private MapView mV;
    private GoogleMap gM;

    public venue_details() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment venue_details.
     */
    // TODO: Rename and change types and number of parameters
    public static venue_details newInstance(String param1, String param2) {
        venue_details fragment = new venue_details();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_venue_details, container, false);

        Bundle bundle = getArguments();
        System.out.println("venue bundle is coming 1");
        System.out.println(bundle);


        if (bundle != null) {

            System.out.println("venue bundle is coming 1");
            String jsonStr = bundle.getString("venueJSONSTR");
            JSONObject jsonObject = null;
            venueObject object =null;
            try {
                jsonObject = new JSONObject(jsonStr);
                object = new venueObject(jsonObject);
            } catch (Exception e) {
            }

            LinearLayout tit1 = view.findViewById(R.id.venhorizontal1);
            TextView ans1 = view.findViewById(R.id.venans1);

            LinearLayout tit2 = view.findViewById(R.id.venhorizontal2);
            TextView ans2 = view.findViewById(R.id.venans2);

            LinearLayout tit3 = view.findViewById(R.id.venhorizontal3);
            TextView ans3 = view.findViewById(R.id.venans3);

            LinearLayout tit4 = view.findViewById(R.id.venhorizontal4);
            TextView ans4 = view.findViewById(R.id.venans4);

            String venueName = object.venueName;
            String venueAddress = object.venueAddress;
            String venueCity = object.venueCity + ", "+ object.venueState;
            String venueContact = object.venueContact;
            try {
                lat = Double.parseDouble(object.venueLat);
                lng = Double.parseDouble(object.venueLong);
            }catch (Exception e){

            }
            updateGoogleMap();

            String Temp = venueName;
            if( Temp!= null && Temp != "Undefined" && Temp != "undefined"){
                ans1.setText(Temp);
                ans1.setSelected(true);
            }else{
                tit1.setVisibility(View.GONE);
            }

            Temp = venueAddress;
            if( Temp!= null && Temp != "Undefined" && Temp != "undefined"){
                ans2.setText(Temp);
                ans2.setSelected(true);
            }else{
                tit2.setVisibility(View.GONE);
            }

            Temp = venueCity;
            if( Temp!= null && Temp != "Undefined" && Temp != "undefined"){
                ans3.setText(Temp);
                ans3.setSelected(true);
            }else{
                tit3.setVisibility(View.GONE);
            }

            Temp = venueContact;
            if( Temp!= null && Temp != "Undefined" && Temp != "undefined"){
                ans4.setText(Temp);
                ans4.setSelected(true);
            }else{
                tit4.setVisibility(View.GONE);
            }

            TextView tit21 = view.findViewById(R.id.ven2tit1);
            TextView ans21 = view.findViewById(R.id.ven2ans1);

            TextView tit22 = view.findViewById(R.id.ven2tit2);
            TextView ans22 = view.findViewById(R.id.ven2ans2);

            TextView tit23 = view.findViewById(R.id.ven2tit3);
            TextView ans23 = view.findViewById(R.id.ven2ans3);

            TextView ven2ans38 = view.findViewById(R.id.ven2ans38);

            String venueOpenHours = object.venueOpenHours;
            String venueGeneral = object.venueGeneral;
            String venueChild = object.venueChild;


            Temp = venueOpenHours;
            if( Temp!= null && Temp != "Undefined" && Temp != "undefined"){
                ans21.setText(Temp);
                ven2ans38.setVisibility(View.GONE);
            }else{
                tit21.setVisibility(View.GONE);
                ans21.setVisibility(View.GONE);
            }


            Temp = venueGeneral;
            if( Temp!= null && Temp != "Undefined" && Temp != "undefined"){
                ans22.setText(Temp);
                ven2ans38.setVisibility(View.GONE);
            }else{
                tit22.setVisibility(View.GONE);
                ans22.setVisibility(View.GONE);
            }

            Temp = venueChild;
            if( Temp!= null && Temp != "Undefined" && Temp != "undefined"){
                ans23.setText(Temp);
                ven2ans38.setVisibility(View.GONE);
            }else{
                tit23.setVisibility(View.GONE);
                ans23.setVisibility(View.GONE);
            }
            mV = view.findViewById(R.id.mapView);
            mV.onCreate(savedInstanceState);
            mV.getMapAsync(this);

        }
        return view;
    }

    private void updateGoogleMap() {
        if (gM != null) {
            LatLng newLatLng = new LatLng(lat, lng);

            if (pinMarker != null) {
                // Remove the existing marker
                pinMarker.remove();
            }

            // Add a marker to the new location
            MarkerOptions markerOptions = new MarkerOptions().position(newLatLng).title("Dropped Pin");
            pinMarker = gM.addMarker(markerOptions);

            // Move and animate the camera to the new marker
            gM.animateCamera(CameraUpdateFactory.newLatLngZoom(newLatLng, 12f));
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        mV.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mV.onPause();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        gM = map;

        // Call the method to add a marker at the dynamically input latitude and longitude
//        addMarkerAtLocation(37.7749, -122.4194);  // Example coordinates (San Francisco)
        updateGoogleMap();
}


}