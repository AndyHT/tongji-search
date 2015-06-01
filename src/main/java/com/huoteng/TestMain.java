package com.huoteng;

import com.huoteng.controller.SpiderController;
import com.huoteng.controller.ManageEntity;
import com.huoteng.entity.*;
import com.huoteng.lucene.SearchEngine;
import com.huoteng.lucene.SearchIndex;
import com.huoteng.spider.Article;
import com.huoteng.spider.NewsSpider;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.util.ArrayList;
import java.util.Date;

/**
 * 测试
 * Created by huoteng on 5/25/15.
 */
public class TestMain {
    public static void main(String[] args) {
        testDatabaseConnect();
    }

    public static void createIndex() {
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
            System.out.println("date:" + article.date);
            System.out.println("content:" + article.content);
            System.out.println();
            index.createIndex(article.url, article.title, article.content, article.date, directory);
        }

        engine.search("2013级本科生", directory);

    }

    /**
     * 测试HibernateController
     */
    public static void testDatabaseConnect() {

        

//        ManageEntity manageEntity = new ManageEntity();
//        manageEntity.init();
//        Users user = new Users();
//        user.setName("Tingran");
//        user.setPass("helloworld");
//        ManageEntity.insertUser(user);
//        System.out.println("插入User成功");

//        GetUrl url = new GetUrl();
//        url.setTitle("杀人啦");
//        url.setUrl("http://www.baidu.com");
//        url.setDate(new Date());
//        ManageEntity.insertGetUrl(url);
//        System.out.println("插入URL成功");
//
//        TargetUrl targetUrl = new TargetUrl();
//        targetUrl.setUrl("http://www.baidu.com");
//        ManageEntity.insertTargetUrl(targetUrl);
//        System.out.println("插入TargetURL成功");

//        manageEntity.destroy();
    }
}
