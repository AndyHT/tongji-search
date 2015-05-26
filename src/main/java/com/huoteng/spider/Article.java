package com.huoteng.spider;

/**
 * Created by huoteng on 5/25/15.
 */
public class Article {

    public Article(String url, String title, String content) {
        this.url = url;
        this.title = title;
        this.content = content;
    }

    public String url;
    public String title;
    public String content;
    //    public Date date;

}
