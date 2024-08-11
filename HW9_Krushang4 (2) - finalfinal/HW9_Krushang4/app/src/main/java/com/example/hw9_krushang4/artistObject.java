package com.example.hw9_krushang4;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.Locale;

public class artistObject {
    //Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
    String artistName;
    String artistFollowers;
    String artistPopularity;
    Uri artistProfileUri;
    String artistSpotify;
    Uri artistAlbum1;
    Uri artistAlbum2;
    Uri artistAlbum3;
    //Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
    public static String formatNumber(int num) {
        if (num >= 1000000) {
            return String.format("%.1fM", num / 1000000.0);
        } else if (num >= 1000) {
            return String.format("%.1fK", num / 1000.0);
        } else {
            return String.valueOf(num);
        }
    }
    public artistObject(JSONObject object){
//Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
        try {
            this.artistName = object.getJSONObject("artist").getString("name");
        } catch (JSONException e) {
        }

        try {
            this.artistFollowers = formatNumber(object.getJSONObject("artist").getInt("followers")) + " Followers";
        } catch (JSONException e) {
        }

        try {
            this.artistPopularity = Integer.toString(object.getJSONObject("artist").getInt("popularity"));
        } catch (JSONException e) {
        }

        try {
            this.artistProfileUri = Uri.parse(object.getJSONObject("artist").getString("image"));
        } catch (JSONException e) {
        }


//Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
        try {
            
            this.artistSpotify = object.getJSONObject("artist").getString("spotifyUrl");
        } catch (JSONException e) {
        }
//Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
        try {
            JSONArray albumsArray = object.getJSONArray("albums");

            if (albumsArray.length() >= 1) {
                JSONObject albumObject = albumsArray.getJSONObject(0);
                this.artistAlbum1 = Uri.parse(albumObject.getString("url"));
            }

            if (albumsArray.length() >= 2) {
                JSONObject albumObject = albumsArray.getJSONObject(1);
                this.artistAlbum2 = Uri.parse(albumObject.getString("url"));
            }
//Took reference from chatgpt.org and https://www.youtube.com/@PracticalCoding
            if (albumsArray.length() >= 3) {
                JSONObject albumObject = albumsArray.getJSONObject(2);
                this.artistAlbum3 = Uri.parse(albumObject.getString("url"));
            }

        } catch (JSONException e) {
        }
    }
}
