package com.carousell.news.ui.articles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.carousell.news.R;
import com.carousell.news.data.model.Article;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder> {

    private ArrayList<Article> articles;

    public ArticlesAdapter(ArrayList<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_article_list, parent, false);
        ArticlesViewHolder viewHolder = new ArticlesViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder holder, int position) {
        final Article article = articles.get(position);
        holder.txtTitle.setText(article.getTitle());
        holder.txtDescription.setText(article.getDescription());
        holder.txtDate.setText(article.getDisplayCreatedDate());
        Glide.with(holder.imgBanner.getContext())
                .load(article.getBannerUrl())
                //.apply(new RequestOptions().transform(new RoundedCorners(15)))
                //.apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(45, 0, RoundedCornersTransformation.CornerType.ALL)))
                .into(holder.imgBanner);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class ArticlesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtTitle)
        TextView txtTitle;

        @BindView(R.id.imgBanner)
        ImageView imgBanner;

        @BindView(R.id.txtDescription)
        TextView txtDescription;

        @BindView(R.id.txtDate)
        TextView txtDate;

        public ArticlesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}