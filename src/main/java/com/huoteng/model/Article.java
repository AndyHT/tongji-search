package com.huoteng.model;

import java.util.Date;

/**
 * Created by huoteng on 5/25/15.
 */
public class Article {

    public Article(String url, String title, String content, Date date) {
        this.url = url;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public String url;
    public String title;
    public String content;
    public Date date;

}
