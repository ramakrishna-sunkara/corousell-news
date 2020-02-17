package com.carousell.news.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.carousell.news.utils.DateUtils;

import java.util.Date;

public class Article implements Comparable<Article> {


    private String id;
    private String title;
    private String description;

    @Expose
    @SerializedName("banner_url")
    private
    String bannerUrl;

    @Expose
    @SerializedName("time_created")
    private
    long timeCreated;

    private int rank;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getDisplayCreatedDate() {
        return DateUtils.getTimeAgo(timeCreated * 1000);
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public int compareTo(Article article2) {
        Date date1 = new Date(timeCreated * 1000);
        Date date2 = new Date(article2.timeCreated * 1000);
        return date2.compareTo(date1);
    }
}
