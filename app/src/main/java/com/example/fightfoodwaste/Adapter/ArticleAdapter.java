package com.example.fightfoodwaste.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fightfoodwaste.ArticleMenuActivity;
import com.example.fightfoodwaste.Models.Article;
import com.example.fightfoodwaste.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(Context context, List<Article> articleList) {
        super(context, 0, articleList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.article_row,parent, false);
        }

        ArticleAdapter.ArticleViewHolder viewHolder = (ArticleAdapter.ArticleViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ArticleAdapter.ArticleViewHolder();
            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.brands = convertView.findViewById(R.id.brands);
            viewHolder.validateButton = convertView.findViewById(R.id.validateButton);
            viewHolder.declineButton = convertView.findViewById(R.id.declineButton);
            convertView.setTag(viewHolder);
        }

        // getItem(position) get item [position] from List<Tour> tourList
        final Article article = getItem(position);

        // fill view
        Picasso.get().load(article.getImageUrl()).into(viewHolder.image);
        viewHolder.name.setText(article.getProductName());
        viewHolder.brands.setText(article.getBrands());
        viewHolder.validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getContext() instanceof ArticleMenuActivity) {
                    ((ArticleMenuActivity)getContext()).articleAction(article.getId(), Article.ACTION_VALIDATE);
                }
            }
        });
        viewHolder.declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ArticleMenuActivity)getContext()).articleAction(article.getId(), Article.ACTION_DECLINE);
            }
        });

        return convertView;
    }

    private class ArticleViewHolder{
        public ImageView image;
        public TextView name;
        public TextView brands;
        public Button validateButton;
        public Button declineButton;
    }
}
