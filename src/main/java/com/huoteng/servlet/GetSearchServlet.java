package com.huoteng.servlet;

import com.huoteng.json.Json;
import com.huoteng.lucene.IndexDirectory;
import com.huoteng.lucene.SearchEngine;
import com.huoteng.model.Article;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.json.JSONException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * 接收从网页端发来的查询请求
 * 将请求提交给lucene查询
 * 接收QueryResponse返回的查询结果返回给网页(json格式)
 * Created by huoteng on 5/24/15.
 */
public class GetSearchServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     *
     * @param request request
     * @param response response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("key");
        String field = request.getParameter("field");
        System.out.println("key:" + keyword);
        System.out.println("field:" + field);

        //根据feild查询


        //根据keyword查询
        SearchEngine engine = new SearchEngine();

        Directory directory = IndexDirectory.getDirectory();
        if (directory == null) {

            File indexFile = new File("/Users/huoteng/Documents/index/");

            if (indexFile.isDirectory()) {
                IndexDirectory.setIndexFile(indexFile);
                directory = new SimpleFSDirectory(IndexDirectory.getIndexFile());
                IndexDirectory.setDirectory(directory);
            }
            System.out.println("got directory");
        }

        ArrayList results;
        results = engine.search(keyword, directory);

        for (Object result : results) {
            Article article = (Article) result;

            if (article.content.length() > 100) {
                article.content = article.content.substring(0, 100);
            }
        }

        //将result转为json
        String jsonResult = null;
        try {
            jsonResult = Json.changeArticleListToJson(results);

            response.addHeader("Content-Type", "text/json;charset=utf-8");
            response.addHeader("Cache-Control", "private");

            OutputStream out = response.getOutputStream();
            out.write(jsonResult.getBytes());
            out.close();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
