package com.carousell.news.ui.articles;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.carousell.news.data.Repository;

import javax.inject.Inject;

/**
 * Created by Ramakrishna Sunkara on 22-05-2019
 */

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;

    @Inject
    public ViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ArticlesViewModel.class)) {
            return (T) new ArticlesViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
