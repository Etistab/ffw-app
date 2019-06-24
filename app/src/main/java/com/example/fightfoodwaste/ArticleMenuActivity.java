package com.example.fightfoodwaste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fightfoodwaste.Adapter.ArticleAdapter;
import com.example.fightfoodwaste.Adapter.TourAdapter;
import com.example.fightfoodwaste.Models.ArticleList;
import com.example.fightfoodwaste.Models.TourList;
import com.example.fightfoodwaste.Models.User;
import com.example.fightfoodwaste.Utils.HttpClient;

public class ArticleMenuActivity extends AppCompatActivity {

    private HttpClient httpClientFFW = new HttpClient("https://api.ffw-mission.org");
    private ArticleList articleList;
    private User user = new User();
    private TextView titleArticleList;
    private ListView listView;
    private int tourType = TourList.COLLECT;
    private int tourId = 0;
    private String tourName = "";
    private String httpTourType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_menu);

        // Get tour type and id
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            tourId = Integer.parseInt(extras.getString("TOUR_ID"));
            tourType = Integer.parseInt(extras.getString("TOUR_TYPE"));
            tourName = extras.getString("TOUR_NAME");
        }

        articleList = new ArticleList(tourName);
        user.getSharedPreferences(this);
        titleArticleList = findViewById(R.id.listTitle);
        listView = findViewById(R.id.list);

        httpTourType = "collecte";
        if(tourType == TourList.DISTRIBUTION) {
            httpTourType = "distribution";
        }

        refreshArticleList();
    }

    protected void refreshArticleList() {
        httpClientFFW.fetch(new HttpClient.Listeners() {
            @Override
            public void onPostExecute(String result) {
                printArticleList(result);
            }
        }, "/transport/"+ user.getId() +"/"+ httpTourType +"/"+ tourId);
    }

    protected void printArticleList(String json) {
        // Fill list data
        articleList.list.clear();
        articleList.fillArticleList(json);

        // Set title
        titleArticleList.setText(articleList.getName());

        // Print List
        ArticleAdapter adapter = new ArticleAdapter(ArticleMenuActivity.this, articleList.list);
        listView.setAdapter(adapter);
    }

    public void articleAction(String id, String action) {
        httpClientFFW.fetch(new HttpClient.Listeners() {
            @Override
            public void onPostExecute(String result) {
                refreshArticleList();
            }
        }, "/transport/"+ user.getId() +"/"+ httpTourType +"/"+ tourId +"/article/"+ id +"/"+ action, "PUT");
    }
}
