package com.huoteng.servlet;

import com.huoteng.controller.SpiderController;
import com.huoteng.lucene.IndexDirectory;
import com.huoteng.lucene.SearchIndex;
import com.huoteng.spider.Article;
import com.huoteng.spider.NewsSpider;
import org.apache.lucene.store.Directory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 启动爬虫
 * Created by huoteng on 5/25/15.
 */
public class StartSpiderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isStart = request.getParameter("start");
        if (Boolean.valueOf(isStart)) {
            Directory directory = IndexDirectory.getDirectory();
            if (directory != null) {

                //启动爬虫建立索引
                NewsSpider spider = new NewsSpider();
                SearchIndex index = new SearchIndex();

                SpiderController spiderController = new SpiderController();
                spiderController.startSpider(spider);

                ArrayList articles = spider.getArticles();

                for (Object object : articles) {
                    Article article = (Article)object;
                    index.createIndex(article.url, article.title, directory);
                }

                //完成后应答前端

            } else {

                //爬虫启动失败
                //应答前端

            }
        }
    }
}
