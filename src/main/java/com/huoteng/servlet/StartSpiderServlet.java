package com.huoteng.servlet;

import com.huoteng.controller.SpiderController;
import com.huoteng.lucene.IndexDirectory;
import com.huoteng.lucene.SearchIndex;
import com.huoteng.spider.Article;
import com.huoteng.spider.NewsSpider;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

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
        if (Boolean.valueOf(order)) {
            Directory directory = IndexDirectory.getDirectory();
            if (directory == null) {

                File indexFile = new File("/User/huoteng/Documents/index/");
                
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
            spiderController.startSpider(spider);

            ArrayList articles = spider.getArticles();

            boolean isSuccess = false;
            for (Object object : articles) {
                Article article = (Article)object;
                isSuccess = index.createIndex(article.url, article.title, article.content, directory);
            }

            response.addHeader("Content-Type", "text/javascript;charset=utf-8");
            response.addHeader("Cache-Control", "private");

            //完成后应答前端,前端发送刷新list请求
            OutputStream out = response.getOutputStream();

            //需要json格式
            String resp = Boolean.toString(isSuccess) + "," + order;

            out.write(resp.getBytes());
            out.close();

        }
    }
}
