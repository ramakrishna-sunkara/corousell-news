package com.carousell.news.di.module;

import androidx.lifecycle.ViewModelProvider;


import com.carousell.news.data.remote.ApiCallInterface;
import com.carousell.news.data.Repository;
import com.carousell.news.ui.articles.ViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ramakrishna Sunkara on 22-05-2019
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    Repository getRepository(ApiCallInterface apiCallInterface) {
        return new Repository(apiCallInterface);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getViewModelFactory(Repository myRepository) {
        return new ViewModelFactory(myRepository);
    }
}
