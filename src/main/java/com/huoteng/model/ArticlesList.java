package com.huoteng.model;

import java.util.ArrayList;

/**
 * 抓取到的结果
 * 单例模式
 * Created by huoteng on 7/27/15.
 */
public class ArticlesList {
    private ArticlesList() {}

    public ArrayList<Article> articles = new ArrayList<Article>();

    private static volatile ArticlesList INSTANCE = null;

    public static ArticlesList getInstance() {
        if (INSTANCE == null) {
            synchronized(ArticlesList.class) {
                if (INSTANCE == null ) {
                    INSTANCE = new ArticlesList();
                }
            }
        }
        return INSTANCE;
    }
}




