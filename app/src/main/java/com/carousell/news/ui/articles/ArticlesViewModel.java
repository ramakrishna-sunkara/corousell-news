package com.carousell.news.ui.articles;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.carousell.news.data.Repository;
import com.carousell.news.data.model.Article;
import com.carousell.news.data.model.ArticlesResponse;
import com.carousell.news.utils.SortType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ramakrishna Sunkara on 22-05-2019
 */

public class ArticlesViewModel extends ViewModel {

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ArticlesResponse> articlesResponseLiveData = new MutableLiveData<>();
    private Repository repository;

    public ArticlesViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArticlesResponse> articlesResponse() {
        return articlesResponseLiveData;
    }

    /*
     * method to call loadArticles
     * */
    public void loadArticles() {
        disposables.add(repository.loadArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((disposable) -> articlesResponseLiveData.setValue(ArticlesResponse.loading()))
                .subscribe(articles -> {
                            Collections.sort(articles);
                            articlesResponseLiveData.setValue(ArticlesResponse.success(articles));
                        },
                        throwable -> {
                            articlesResponseLiveData.setValue(ArticlesResponse.error(throwable));
                        }));
    }

    /*
     * method to call loadArticles
     * */
    public void sortArticles(SortType sortType) {
        if (null == Objects.requireNonNull(articlesResponseLiveData.getValue()).articles) {
            return;
        }
        disposables.add(processArticlesSort(sortType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((disposable) -> articlesResponseLiveData.setValue(ArticlesResponse.loading()))
                .subscribe(
                        articles -> articlesResponseLiveData.setValue(ArticlesResponse.success(articles)),
                        throwable -> articlesResponseLiveData.setValue(ArticlesResponse.error(throwable))
                ));
    }

    private Observable<ArrayList<Article>> processArticlesSort(SortType sortType) {
        if (sortType == SortType.POPULAR) {
            return Observable.just(doArticlesSortByPopular());
        } else {
            return Observable.just(doArticlesSortByRecent());
        }
    }

    private ArrayList<Article> doArticlesSortByPopular() {
        ArrayList<Article> mArticles = Objects.requireNonNull(articlesResponseLiveData.getValue()).articles;
        Comparator<Article> compareByPopular = (Article article1, Article article2) ->
                article1.getRank() - article2.getRank();
        Collections.sort(mArticles, compareByPopular);
        return mArticles;
    }

    private ArrayList<Article> doArticlesSortByRecent() {
        ArrayList<Article> mArticles = Objects.requireNonNull(articlesResponseLiveData.getValue()).articles;
        Comparator<Article> compareByRecent = (Article article1, Article article2) -> {
            Date date1 = new Date(article1.getTimeCreated() * 1000);
            Date date2 = new Date(article2.getTimeCreated() * 1000);
            return date2.compareTo(date1);
        };
        Collections.sort(mArticles, compareByRecent);
        return mArticles;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
