package com.example.hw9_krushang4;

import org.json.JSONException;
import org.json.JSONObject;

public class venueObject {

    String venueName;
    String venueAddress;
    String venueCity;
    String venueState;
    String venueContact;
    String venueOpenHours;
    String venueGeneral;
    String venueChild;

    String venueLat;
    String venueLong;

    public venueObject(JSONObject object){
        JSONObject venue = null;
        try {
            venue = object.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(venue != null){

            try {
                this.venueName = venue.getString("name");
            }catch (Exception e){
            }

            try {
                this.venueCity = venue.getJSONObject("city").getString("name");
            }catch (Exception e){
            }

            try {
                this.venueState = venue.getJSONObject("state").getString("name");
            }catch (Exception e){
            }

            try {
                this.venueAddress = venue.getJSONObject("address").getString("line1");
            }catch (Exception e){
            }

            try {
                this.venueContact = venue.getJSONObject("boxOfficeInfo").getString("phoneNumberDetail");
            }catch (Exception e){
            }

            try {
                this.venueOpenHours = venue.getJSONObject("boxOfficeInfo").getString("openHoursDetail");
            }catch (Exception e){
            }

            try {
                this.venueGeneral = venue.getJSONObject("generalInfo").getString("generalRule");
            }catch (Exception e){
            }

            try {
                this.venueChild = venue.getJSONObject("generalInfo").getString("childRule");
            }catch (Exception e){
            }

            try {
                this.venueLat = venue.getJSONObject("location").getString("latitude");
            }catch (Exception e){

            }

            try {
                this.venueLong = venue.getJSONObject("location").getString("longitude");
            }catch (Exception e){

            }
        }

    }
}
