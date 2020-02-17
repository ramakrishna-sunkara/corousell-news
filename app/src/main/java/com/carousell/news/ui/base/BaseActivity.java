package com.carousell.news.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carousell.news.MyApplication;
import com.carousell.news.di.component.AppComponent;
import com.carousell.news.utils.NetworkUtils;

import butterknife.ButterKnife;

/**
 * Created by Ramakriishna Sunkara on 23/05/19
 */

public abstract class BaseActivity extends AppCompatActivity {


    private ProgressDialog mProgressDialog;

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    public AppComponent getComponent(){
        return  ((MyApplication) getApplication()).getAppComponent();
    }

}

