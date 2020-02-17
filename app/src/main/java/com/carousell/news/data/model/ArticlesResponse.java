package com.carousell.news.data.model;

import com.carousell.news.data.remote.ApiStatus;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

import static com.carousell.news.data.remote.ApiStatus.ERROR;
import static com.carousell.news.data.remote.ApiStatus.LOADING;
import static com.carousell.news.data.remote.ApiStatus.SUCCESS;

/**
 * Created by Ramakrishna Sunkara on 22-05-2019
 */

public class ArticlesResponse {

    public final ApiStatus status;

    @Nullable
    public final ArrayList<Article> articles;

    @Nullable
    public final Throwable error;

    private ArticlesResponse(ApiStatus status, @Nullable ArrayList<Article> articles, @Nullable Throwable error) {
        this.status = status;
        this.articles = articles;
        this.error = error;
    }

    public static ArticlesResponse loading() {
        return new ArticlesResponse(LOADING, null, null);
    }

    public static ArticlesResponse success(@NonNull ArrayList<Article> articles) {
        return new ArticlesResponse(SUCCESS, articles, null);
    }

    public static ArticlesResponse error(@NonNull Throwable error) {
        return new ArticlesResponse(ERROR, null, error);
    }

}
