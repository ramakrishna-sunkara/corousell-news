package com.carousell.news.data.remote;

import com.carousell.news.data.model.Article;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by Ramakrishna Sunkara on 22-05-2019
 */


public interface ApiCallInterface {

    @GET(ApiEndPoint.ARTICLES)
    Single<ArrayList<Article>> loadArticles();

}
