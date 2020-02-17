package com.carousell.news;

import android.app.Application;
import android.content.Context;

import com.carousell.news.di.component.AppComponent;
import com.carousell.news.di.component.DaggerAppComponent;
import com.carousell.news.di.module.AppModule;
import com.carousell.news.di.module.ServiceModule;


/**
 * Created by Ramakrishna Sunkara on 22-05-2019
 */

public class MyApplication extends Application {
    AppComponent appComponent;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appComponent = DaggerAppComponent.builder().serviceModule(new ServiceModule(context)).appModule(new AppModule()).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
