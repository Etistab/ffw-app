package com.example.fightfoodwaste;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fightfoodwaste.Models.User;
import com.example.fightfoodwaste.Utils.*;

import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private EditText emailField;
    private Button connectionButton;
    private Button quitButton;
    private HttpClient httpClientFFW = new HttpClient("https://api.ffw-mission.org");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.emailField = findViewById(R.id.email);
        this.connectionButton = findViewById(R.id.connection);
        this.quitButton = findViewById(R.id.quit);

        connectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailText = checkEditText(emailField);

                if(!emailText.isEmpty()) {

                    httpClientFFW.fetch(new HttpClient.Listeners() {
                        @Override
                        public void onPostExecute(String result) {
                            if(checkHttpCode(result)) {
                                logIn(result);
                            }
                        }
                    }, "/transport/"+ base64Encode(emailText)); //natane-b@hotmail.fr
                }
            }
        });
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showQuitConfirmation();
            }
        });
    }

    protected boolean checkHttpCode(String codeString) {
        if(isInteger(codeString)) {
            int code = Integer.parseInt(codeString);
            if(code == 400) {
                this.emailField.setError("L'email n'existe pas");
            }
            return false;
        }
        if(codeString.isEmpty()) {
            this.emailField.setError("Erreur");
            return false;
        }
        return true;
    }

    protected void logIn(String infoUserString) {
        User user = new User(infoUserString);
        user.registerInSharedPreferences(this);

        Intent myIntent = new Intent(MainActivity.this, TransporterMenuActivity.class);
        startActivity(myIntent);
    }

    protected void showQuitConfirmation(){
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Quitter FFW ?")
                .setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish(); // Quitte l'app
                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("BUTTON", "Non");
                    }
                })
                .setCancelable(false)
                .show();
    }

    protected String checkEditText(EditText editText) {
        String text = editText.getText().toString();

        if(text.isEmpty()){
            editText.setError("Obligatoire");
        }
        else {
            editText.setText(null);
        }

        return text;
    }

    protected static String base64Encode(String text) {
        byte[] data = text.getBytes(StandardCharsets.UTF_8);
        return Base64.encodeToString(data, Base64.DEFAULT);
    }

    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
}
