package com.example.fightfoodwaste.Models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ArticleList {

    public List<Article> list = new ArrayList<>();
    private String name;

    public ArticleList(String name) {
        setName(name);
    }

    public void fillArticleList(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray articles = jsonObject.getJSONArray("articles");

            for(int i = 0; i < articles.length(); i++) {
                list.add(new Article(articles.getJSONObject(i).toString()));
            }
        }
        catch (JSONException e) {
            Log.e("LOGIN", "Invalid JSON");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
