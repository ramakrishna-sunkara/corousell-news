package com.carousell.news.ui.articles;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.carousell.news.RxImmediateSchedulerRule;
import com.carousell.news.data.Repository;
import com.carousell.news.data.model.Article;
import com.carousell.news.utils.SortType;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Objects;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ArticlesViewModelTest {

    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    Repository mRepository;

    ArticlesViewModel mArticlesViewModel;

    private TestScheduler mTestScheduler;

    @Before
    public void setUp() throws Exception {
        mTestScheduler = new TestScheduler();
        mArticlesViewModel = new ArticlesViewModel(mRepository);
    }

    @After
    public void tearDown() throws Exception {
         mTestScheduler = null;
         mArticlesViewModel = null;
         mRepository = null;
    }

    @Test
    public void testLoadArticlesSuccess() throws Exception{
        ArrayList<Article> articles = getDummyArticles();
        doReturn(Single.just(articles))
                .when(mRepository)
                .loadArticles();
        mArticlesViewModel.loadArticles();
        mTestScheduler.triggerActions();
        assert(Objects.requireNonNull(mArticlesViewModel.articlesResponse().getValue()).articles).equals(articles);
    }

    @Test
    public void testSortArticlesPopularSuccess() throws Exception{
        ArrayList<Article> articles = getDummyArticles();
        doReturn(Single.just(articles))
                .when(mRepository)
                .loadArticles();
        mArticlesViewModel.loadArticles();
        mArticlesViewModel.sortArticles(SortType.POPULAR);
        mTestScheduler.triggerActions();
        assert(Objects.requireNonNull(mArticlesViewModel.articlesResponse().getValue()).articles).equals(articles);
    }

    @Test
    public void testSortArticlesRecentSuccess() throws Exception{
        ArrayList<Article> articles = getDummyArticles();
        doReturn(Single.just(articles))
                .when(mRepository)
                .loadArticles();
        mArticlesViewModel.loadArticles();
        mArticlesViewModel.sortArticles(SortType.RECENT);
        mTestScheduler.triggerActions();
        assert(Objects.requireNonNull(mArticlesViewModel.articlesResponse().getValue()).articles).equals(articles);
    }

    private ArrayList<Article> getDummyArticles(){
        ArrayList<Article> articles = new ArrayList<>();
        Article article = new Article();
        article.setId("125");
        article.setBannerUrl("https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-hero-image_10june.png");
        article.setDescription("Carousell, the Singapore-based mobile listing service that operates across Southeast Asia, has pulled in an $85 million Series C fund as it seeks to strengthen its business among the region's competitive e-commerce landscape before expanding globally.");
        article.setRank(5);
        article.setTimeCreated(1532939458);
        articles.add(null);
        return articles;
    }

}