package com.huoteng;

import com.huoteng.controller.SpiderController;
import com.huoteng.lucene.SearchEngine;
import com.huoteng.lucene.SearchIndex;
import com.huoteng.spider.Article;
import com.huoteng.spider.NewsSpider;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * 测试
 * Created by huoteng on 5/25/15.
 */
public class TestMain {
    public static void main(String[] args) {
        Directory directory = new RAMDirectory();

        NewsSpider spider = new NewsSpider();
        SearchEngine engine = new SearchEngine();
        SearchIndex index = new SearchIndex();

        SpiderController sController = new SpiderController();
        sController.startSpider(spider);

        ArrayList articles = spider.getArticles();

        for (Object object : articles) {
            Article article = (Article)object;
            System.out.println("url:" + article.url);
            System.out.println("title:" + article.title);
            System.out.println("content:" + article.content);
            System.out.println();
            index.createIndex(article.url, article.title, article.content, directory);
        }

        engine.search("本科生", directory);

    }

}
