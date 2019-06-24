package com.example.fightfoodwaste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fightfoodwaste.Models.TourList;

public class TransporterMenuActivity extends AppCompatActivity {

    private Button collectButton;
    private Button distributionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporter_menu);

        this.collectButton= findViewById(R.id.collect);
        this.distributionButton = findViewById(R.id.distribution);

        collectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(TransporterMenuActivity.this, TourMenuActivity.class);
                myIntent.putExtra("TOUR_TYPE", Integer.toString(TourList.COLLECT));
                startActivity(myIntent);
            }
        });
        distributionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(TransporterMenuActivity.this, TourMenuActivity.class);
                myIntent.putExtra("TOUR_TYPE", Integer.toString(TourList.DISTRIBUTION));
                startActivity(myIntent);
            }
        });

    }
}
