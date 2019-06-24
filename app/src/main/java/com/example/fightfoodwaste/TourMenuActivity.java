package com.example.fightfoodwaste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fightfoodwaste.Adapter.TourAdapter;
import com.example.fightfoodwaste.Models.TourList;
import com.example.fightfoodwaste.Models.User;
import com.example.fightfoodwaste.Utils.HttpClient;

public class TourMenuActivity extends AppCompatActivity {

    private HttpClient httpClientFFW = new HttpClient("https://api.ffw-mission.org");
    private TourList tourList;
    private User user = new User();
    private TextView titleTourList;
    private ListView listView;
    private int tourType = TourList.COLLECT;
    private String httpTourType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_menu);

        // Get tour type
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            tourType = Integer.parseInt(extras.getString("TOUR_TYPE"));
        }

        tourList = new TourList(tourType);
        user.getSharedPreferences(this);
        titleTourList = findViewById(R.id.listTitle);
        listView = findViewById(R.id.list);

        httpTourType = "collecte";
        if(tourType == TourList.DISTRIBUTION) {
            httpTourType = "distribution";
        }

        refreshTourList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshTourList();
    }

    protected void refreshTourList(){
        httpClientFFW.fetch(new HttpClient.Listeners() {
            @Override
            public void onPostExecute(String result) {
                printTourList(result);
            }
        }, "/transport/"+ user.getId() +"/"+ httpTourType);
    }

    protected void printTourList(String json) {
        // Set title
        titleTourList.setText("Collectes");
        if(this.tourType == TourList.DISTRIBUTION) {
            titleTourList.setText("Distributions");
        }

        // Fill list data
        tourList.list.clear();
        tourList.fillTourList(json);

        // Print List
        TourAdapter adapter = new TourAdapter(TourMenuActivity.this, this.tourList.list);
        listView.setAdapter(adapter);
    }
}
