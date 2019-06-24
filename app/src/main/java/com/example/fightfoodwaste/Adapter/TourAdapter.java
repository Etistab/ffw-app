package com.example.fightfoodwaste.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.fightfoodwaste.ArticleMenuActivity;
import com.example.fightfoodwaste.Models.Tour;
import com.example.fightfoodwaste.R;

import java.util.List;

public class TourAdapter extends ArrayAdapter<Tour> {

    public TourAdapter(Context context, List<Tour> tourList) {
        super(context, 0, tourList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tour_row,parent, false);
        }

        TourViewHolder viewHolder = (TourViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new TourViewHolder();
            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.date_start = convertView.findViewById(R.id.date_start);
            viewHolder.seeButton = convertView.findViewById(R.id.seeButton);
            convertView.setTag(viewHolder);
        }

        // getItem(position) get item [position] from List<Tour> tourList
        final Tour tour = getItem(position);

        // fill view
        viewHolder.name.setText(tour.getName());
        viewHolder.date_start.setText(tour.getDateStart());
        viewHolder.seeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getContext(), ArticleMenuActivity.class);
                myIntent.putExtra("TOUR_ID", tour.getId());
                myIntent.putExtra("TOUR_TYPE", tour.getTypeTour());
                myIntent.putExtra("TOUR_NAME", tour.getName());
                getContext().startActivity(myIntent);
            }
        });

        return convertView;
    }

    private class TourViewHolder{
        public TextView name;
        public TextView date_start;
        public Button seeButton;
    }
}