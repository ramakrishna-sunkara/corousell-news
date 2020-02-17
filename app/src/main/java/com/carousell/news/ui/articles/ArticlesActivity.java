package com.carousell.news.ui.articles;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.carousell.news.R;
import com.carousell.news.data.model.Article;
import com.carousell.news.data.model.ArticlesResponse;
import com.carousell.news.ui.base.BaseActivity;
import com.carousell.news.utils.SortType;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ramakrishna Sunkara on 22-05-2019
 */
public class ArticlesActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Inject
    ViewModelFactory viewModelFactory;

    ArticlesViewModel viewModel;

    @BindView(R.id.rvArticles)
    RecyclerView rvArticles;

    @BindView(R.id.toolbarApp)
    Toolbar toolbarApp;

    @BindView(R.id.imgOptions)
    ImageView imgOptions;

    @BindView(R.id.srlRefresh)
    SwipeRefreshLayout srlRefresh;

    ArticlesAdapter articlesAdapter;
    ArrayList<Article> articles;

    @Override
    public int getLayoutId() {
        return R.layout.activity_articles;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_articles);
        getComponent().doInjection(this);

        setSupportActionBar(toolbarApp);

        articles = new ArrayList<>();
        articlesAdapter = new ArticlesAdapter(articles);
        rvArticles.setAdapter(articlesAdapter);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticlesViewModel.class);

        viewModel.articlesResponse().observe(this, this::consumeArticlesResponse);

        srlRefresh.setOnRefreshListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadArticles();
    }

    @OnClick(R.id.imgOptions)
    public void onImgOptionsClicked() {
        showMenu(imgOptions);
    }

    private void loadArticles() {
        if (!isNetworkConnected()) {
            Toast.makeText(ArticlesActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
        } else {
            viewModel.loadArticles();
        }
    }

    /*
     * method to handle renderArticlesResponse Response
     * */
    private void consumeArticlesResponse(ArticlesResponse articlesResponse) {

        switch (articlesResponse.status) {

            case LOADING:
                srlRefresh.setRefreshing(true);
                break;

            case SUCCESS:
                srlRefresh.setRefreshing(false);
                renderArticlesResponse(articlesResponse.articles);
                break;

            case ERROR:
                srlRefresh.setRefreshing(false);
                break;

            default:
                break;
        }
    }

    /*
     * method to handle renderArticlesResponse Success Response
     * */
    private void renderArticlesResponse(ArrayList<Article> articles) {
        if (null != articles && articles.size() > 0) {
            articlesAdapter = new ArticlesAdapter(articles);
            rvArticles.setAdapter(articlesAdapter);
        } else {
            Toast.makeText(getApplicationContext(), "Empty Articles...", Toast.LENGTH_SHORT).show();
        }
    }

    public void showMenu(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.recent:
                    viewModel.sortArticles(SortType.RECENT);
                    return true;
                case R.id.popular:
                    viewModel.sortArticles(SortType.POPULAR);
                    return true;
                default:
                    return ArticlesActivity.super.onMenuItemSelected(item.getItemId(), item);
            }
        });
        // to implement on click event on items of menu
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.sort_menu, popup.getMenu());
        popup.show();
    }

    @Override
    public void onRefresh() {
        loadArticles();
    }
}
