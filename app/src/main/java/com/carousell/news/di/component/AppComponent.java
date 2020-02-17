package com.carousell.news.di.component;

import com.carousell.news.di.module.AppModule;
import com.carousell.news.di.module.ServiceModule;
import com.carousell.news.ui.articles.ArticlesActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ramakrishna Sunkara on 22-05-2019
 */

@Component(modules = {ServiceModule.class, AppModule.class})
@Singleton
public interface AppComponent {

    void doInjection(ArticlesActivity articlesActivity);

}
