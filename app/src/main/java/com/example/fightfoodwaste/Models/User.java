package com.example.fightfoodwaste.Models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    private String id;
    private String name;
    private String lastname;
    private String mail;

    public User() {

    }

    public User(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject infoUser = jsonObject.getJSONObject("infoUser");

            this.setId(infoUser.getString("id"));
            this.setName(infoUser.getString("name"));
            this.setLastname(infoUser.getString("lastname"));
            this.setMail(infoUser.getString("mail"));
        }
        catch (JSONException e) {
            Log.e("LOGIN", "Invalid JSON");
        }
    }

    public void registerInSharedPreferences(Context context) {
        SharedPreferences settings = context.getSharedPreferences("user", Context.MODE_PRIVATE) ;
        SharedPreferences.Editor edit = settings.edit();
        edit.putString( "id", this.getId());
        edit.putString( "name", this.getName());
        edit.putString( "lastname", this.getLastname());
        edit.putString( "mail", this.getMail());
        edit.apply();
    }

    public void getSharedPreferences(Context context) {
        SharedPreferences settings = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        this.setId(settings.getString("id", ""));
        this.setName(settings.getString("name", ""));
        this.setLastname(settings.getString("lastname", ""));
        this.setMail(settings.getString("mail", ""));
    }

    public String getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setName(String name) {
        this.name = name;
    }
}
