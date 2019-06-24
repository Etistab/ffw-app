package com.example.fightfoodwaste.Models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TourList {

    public final static int COLLECT = 1;
    public final static int DISTRIBUTION = 2;
    public List<Tour> list = new ArrayList<>();
    private String typeTour;

    public TourList(int idTypeTour) {
        this.setTypeTour(idTypeTour);
    }

    public void fillTourList(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray tours = jsonObject.getJSONArray(this.typeTour);

            for(int i = 0; i < tours.length(); i++) {
                list.add(new Tour(tours.getJSONObject(i).toString()));
            }
        }
        catch (JSONException e) {
            Log.e("LOGIN", "Invalid JSON");
        }
    }

    public void setTypeTour(int idTypeTour) {
        if(idTypeTour == COLLECT){
            this.typeTour = "collectes";
        }
        if(idTypeTour == DISTRIBUTION) {
            this.typeTour = "distributions";
        }
    }

    public String getTypeTour() {
        return typeTour;
    }
}
