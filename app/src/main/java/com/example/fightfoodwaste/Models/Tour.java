package com.example.fightfoodwaste.Models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Tour {

    private String id;
    private String name;
    private String dateStart;
    private String isValidate;
    private String isDeliver;
    private String transporter;
    private String typeTour;

    public Tour(String json) {
        try {
            JSONObject tour = new JSONObject(json);

            this.setId(tour.getString("id"));
            this.setName(tour.getString("name"));
            this.setDateStart(tour.getString("date_start"));
            this.setIsValidate(tour.getString("isValidate"));
            this.setIsDeliver(tour.getString("is_deliver"));
            this.setTransporter(tour.getString("id_USER_TRANSPORTER"));
            this.setTypeTour(tour.getString("id_TYPE_TOUR"));
        }
        catch (JSONException e) {
            Log.e("LOGIN", "Invalid JSON");
        }
    }

    public String getName() {
        return name;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getId() {
        return id;
    }

    public String getIsDeliver() {
        return isDeliver;
    }

    public String getIsValidate() {
        return isValidate;
    }

    public String getTransporter() {
        return transporter;
    }

    public String getTypeTour() {
        return typeTour;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public void setIsDeliver(String isDeliver) {
        this.isDeliver = isDeliver;
    }

    public void setIsValidate(String isValidate) {
        this.isValidate = isValidate;
    }

    public void setTransporter(String transporter) {
        this.transporter = transporter;
    }

    public void setTypeTour(String typeTour) {
        this.typeTour = typeTour;
    }
}
