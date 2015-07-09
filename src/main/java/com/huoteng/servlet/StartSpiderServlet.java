package com.huoteng.servlet;

import com.huoteng.controller.HibernateController;
import com.huoteng.controller.SpiderController;
import com.huoteng.entity.TargetUrl;
import com.huoteng.json.Json;
import com.huoteng.lucene.IndexDirectory;
import com.huoteng.lucene.SearchEngine;
import com.huoteng.lucene.SearchIndex;
import com.huoteng.model.Article;
import com.huoteng.spider.NewsSpider;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.json.JSONException;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 启动爬虫
 * Created by huoteng on 5/25/15.
 */
public class StartSpiderServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String order = request.getParameter("start");
        System.out.println("spider start:" + order);

        if (Boolean.valueOf(order)) {
            Directory directory = IndexDirectory.getDirectory();
            String indexFileName = "/Users/huoteng/Documents/index/";

            //删除Index文件
            File indexFile = new File(indexFileName);
            if (indexFile.exists()) {
                //删index
                IndexDirectory.delete(indexFileName);
            }
            //建index
            boolean createIndexFolderIsSuccess = indexFile.mkdir();
            if (createIndexFolderIsSuccess) {
                IndexDirectory.setIndexFile(indexFile);
                directory = new SimpleFSDirectory(IndexDirectory.getIndexFile());
                IndexDirectory.setDirectory(directory);


                //启动爬虫建立索引
                NewsSpider spider = new NewsSpider();
                SearchIndex index = new SearchIndex();

                SpiderController spiderController = new SpiderController();

                ArrayList<Article> articles = null;
                //从数据库里拿URL
                HibernateController hibernate = new HibernateController();
                System.out.println("开始数据库");
                List targetUrls = hibernate.findAllTargetURL();

                for (Object aUrl : targetUrls) {
                    spiderController.startSpider(spider, ((TargetUrl) aUrl).getUrl());
                    System.out.println("Spider started");

                    if (articles == null) {
                        articles = spider.getArticles();
                    } else {
                        articles.addAll(spider.getArticles());
                    }
                    System.out.println("got articles");
                }

                if (articles != null) {
                    //删除数据库里的记录
                    hibernate.deleteAllGotUrl();
                    System.out.println("delete article in db");


                    //创建index
                    for (Object object : articles) {
                        Article article = (Article)object;
                        System.out.println("url:" + article.url);
                        System.out.println("title:" + article.title);
                        System.out.println("date:" + article.date);
                        System.out.println();

                        index.createIndex(article.url, article.title, article.content, article.date, directory);
                    }

                        //储存记录
                    hibernate.addGotURL(articles);
                    System.out.println("store articles in db");

                    response.addHeader("Content-Type", "text/javascript;charset=utf-8");
                    response.addHeader("Cache-Control", "private");

                    //完成后应答前端,前端发送刷新list请求
                    OutputStream out = response.getOutputStream();

                    String resp = null;
                    try {
                        resp = Json.changeArticleListToJson(articles);
                        resp = "[" + resp + "]";
                        out.write(resp.getBytes());
                        out.close();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Articles is null");
                }
            }
        }

    }
}
