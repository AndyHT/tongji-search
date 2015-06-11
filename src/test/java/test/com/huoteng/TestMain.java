package test.com.huoteng;

import com.huoteng.controller.SpiderController;
import com.huoteng.lucene.SearchEngine;
import com.huoteng.lucene.SearchIndex;
import com.huoteng.model.Article;
import com.huoteng.spider.NewsSpider;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.SimpleFSDirectory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 测试
 * Created by huoteng on 5/25/15.
 */
public class TestMain {
    public static void main(String[] args) {
        try {
            createIndex();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createIndex() throws IOException {
//        File indexFile = new File("/Users/huoteng/Documents/index/");
//
//        Directory directory = new SimpleFSDirectory(indexFile);
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

        engine.search("2015届", directory);

    }

}
