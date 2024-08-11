//took reference from chatGPT
package com.example.hw9_krushang4;
//took reference from chatGPT
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//took reference from chatGPT
//took reference from chatGPT
public class eventObject{
    //took reference from chatGPT
    String eventName;
    String eventDate;
    String eventTime;
    JSONObject eventClassificationsobj;
    Uri eventSeatmap;
    String eventVenue;
    Uri eventUri;
    String eventOneGenre;
    String eventGenres;
    Boolean eventMusicFlag;
    JSONArray artistArrobj;
    String eventArtists;
    String eventPriceRange;
    //took reference from chatGPT
    Uri eventTicketMasterUri;

    String eventStatusCode;

    String MainEventTMObject;

    Boolean isFav;

    String eventId;

    public Boolean getIsFav(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Favorites",Context.MODE_PRIVATE);
        if(sharedPreferences.contains(eventId)){
            isFav = true;
            return true;

        }else {
            isFav = false;
            return false;
        }
    }

    public eventObject(JSONObject event_obj){
        this.eventMusicFlag = false;
        this.MainEventTMObject = event_obj.toString();

        try{
            this.eventId = event_obj.getString("id");
        }catch (Exception e){

        }


        try {
            this.eventName = event_obj.getString("name");
        } catch (Exception e) {

        }
//took reference from chatGPT
        try {
            this.eventDate = event_obj.getJSONObject("dates").getJSONObject("start").getString("localDate");
        } catch (Exception e) {

        }

        try {
            this.eventStatusCode = event_obj.getJSONObject("dates").getJSONObject("status").getString("code");
        } catch (Exception e) {

        }

        try {
            //took reference from chatGPT
            String inputDate = event_obj.getJSONObject("dates").getJSONObject("start").getString("localTime");
            DateFormat inputFormat = new SimpleDateFormat("HH:mm:ss");
            DateFormat outputFormat = new SimpleDateFormat("h:mm a");
//took reference from chatGPT
            Date date = inputFormat.parse(inputDate);
            String outputDate = outputFormat.format(date);

            System.out.println("Input date: " + inputDate);
            System.out.println("Output date: " + outputDate);
//took reference from chatGPT
            this.eventTime = outputDate;

        } catch (Exception e) {

        }

        try {
            this.eventClassificationsobj = event_obj.getJSONArray("classifications").getJSONObject(0);
//took reference from chatGPT
            JSONObject json = this.eventClassificationsobj;
            String[] fields = {"segment", "genre", "subGenre", "type", "subType"};
            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
//took reference from chatGPT
            for (String field : fields) {
                if (json.has(field)) {
                    JSONObject obj = json.getJSONObject(field);
                    if (obj.has("name") && !"Undefined".equals(obj.getString("name"))) {
                        if (!isFirst) {
                            sb.append(" | ");
                        }
                        String tempStringName = obj.getString("name");

                        if(tempStringName.equals("Music")){
                            this.eventMusicFlag = true;
                        }
                        sb.append(tempStringName);
                        isFirst = false;
                    }
                }
            }
            this.eventGenres = sb.toString();
            this.eventOneGenre = json.getJSONObject("segment").getString("name");
        } catch (Exception e) {
//took reference from chatGPT
        }


//took reference from chatGPT
        try {
            this.eventSeatmap = Uri.parse(event_obj.getJSONObject("seatmap").getString("staticUrl"));
        } catch (Exception e) {
//took reference from chatGPT
        }

        try {
            this.eventVenue = event_obj.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getString("name");
        } catch (Exception e) {
//took reference from chatGPT
        }

        try {
            this.eventUri = Uri.parse(event_obj.getJSONArray("images").getJSONObject(0).getString("url"));
        } catch (Exception e) {
//took reference from chatGPT
        }

        try {
            this.eventTicketMasterUri =  Uri.parse(event_obj.getString("url"));
        } catch (Exception e) {
//took reference from chatGPT
        }

        try {
            this.artistArrobj = event_obj.getJSONObject("_embedded").getJSONArray("attractions");
            JSONArray jsonArray = this.artistArrobj;
//took reference from chatGPT
            String output = "";
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.optString("name");
                if (name != null && !name.equalsIgnoreCase("undefined")) {
                    output += name + " | ";
                }
            }
//took reference from chatGPT
            output = output.substring(0, output.length() - 3);
//took reference from chatGPT
            this.eventArtists = output;

        } catch (JSONException e) {
        }

        try {
            JSONArray jsonArray = event_obj.getJSONArray("priceRanges");
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String currency = jsonObject.getString("currency");
            int min = jsonObject.getInt("min");
            int max = jsonObject.getInt("max");
            String output = String.format("%d - %d (%s)", min, max, currency);
//took reference from chatGPT
            this.eventPriceRange = output;

        } catch (JSONException e) {

        }


    }
}
