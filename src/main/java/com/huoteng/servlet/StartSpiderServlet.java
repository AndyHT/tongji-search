package com.huoteng.servlet;

import com.huoteng.controller.HibernateController;
import com.huoteng.controller.SpiderController;
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
            if (directory == null) {

                File indexFile = new File("/Users/huoteng/Documents/index/");

                if (indexFile.isDirectory()) {
                    IndexDirectory.setIndexFile(indexFile);
                    directory = new SimpleFSDirectory(IndexDirectory.getIndexFile());
                    IndexDirectory.setDirectory(directory);
                }

            }

            //启动爬虫建立索引
            NewsSpider spider = new NewsSpider();
            SearchIndex index = new SearchIndex();

            SpiderController spiderController = new SpiderController();

            //从数据库里那URL
            HibernateController hibernate = new HibernateController();
            String url = "http://sse.tongji.edu.cn/InfoCenter/Lastest_Notice.aspx";
            if (hibernate.begin()) {
                List urls = hibernate.findAllTargetURL();
                for (Object temp : urls) {
//                    temp = (String)temp;

                }
            }
            spiderController.startSpider(spider, url);
            System.out.println("Spider started");

            ArrayList articles = spider.getArticles();
            System.out.println("got articles");

            //存数据库
            HibernateController hibernat = new HibernateController();
            if (hibernat.begin()) {
                hibernat.addGotURL(articles);
            }
            //创建index
            for (Object object : articles) {
                Article article = (Article)object;
                System.out.println("url:" + article.url);
                System.out.println("title:" + article.title);
                System.out.println("date:" + article.date);
                System.out.println();

                index.createIndex(article.url, article.title, article.content, article.date, directory);
            }

            response.addHeader("Content-Type", "text/javascript;charset=utf-8");
            response.addHeader("Cache-Control", "private");

            //完成后应答前端,前端发送刷新list请求
            OutputStream out = response.getOutputStream();

            String resp = null;
            try {
                resp = Json.changeArticleListToJson(articles);
                out.write(resp.getBytes());
                out.close();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
