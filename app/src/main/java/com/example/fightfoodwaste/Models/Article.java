package com.example.fightfoodwaste.Models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Article {

    public final static String ACTION_VALIDATE = "validate";
    public final static String ACTION_DECLINE = "decline";
    private String id;
    private String brands;
    private String productName;
    private String imageUrl;

    public Article(String json) {
        try {
            JSONObject tour = new JSONObject(json);

            this.setId(tour.getString("id"));
            this.setBrands(tour.getString("brands"));
            this.setProductName(tour.getString("product_name"));
            this.setImageUrl(tour.getString("image_url"));
        }
        catch (JSONException e) {
            Log.e("LOGIN", "Invalid JSON");
        }
    }


    public String getId() {
        return id;
    }

    public String getBrands() {
        return brands;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
