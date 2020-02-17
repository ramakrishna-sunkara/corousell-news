package com.carousell.news.data;

import com.carousell.news.data.model.Article;
import com.carousell.news.data.remote.ApiCallInterface;

import java.util.ArrayList;

import io.reactivex.Single;

/**
 * Created by Ramakrishna Sunkara on 22-05-2019
 */

public class Repository {

    private ApiCallInterface apiCallInterface;

    public Repository(ApiCallInterface apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }

    /*
     * method to call loadArticles api
     * */
    public Single<ArrayList<Article>> loadArticles() {
        return apiCallInterface.loadArticles();
    }
}
