package com.huoteng.servlet;

import com.huoteng.lucene.IndexDirectory;
import com.huoteng.lucene.SearchEngine;
import com.huoteng.spider.Article;
import org.apache.lucene.store.Directory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("key");

        //根据keyword查询
        SearchEngine engine = new SearchEngine();
        Directory directory = IndexDirectory.getDirectory();
        ArrayList<Article> result;
        if (directory != null) {
            result = engine.search(keyword, directory);


            //调用QueryResponese将result转为json
            String resultJson = "";

            response.addHeader("Content-Type", "text/json;charset=utf-8");
            response.addHeader("Cache-Control", "private");

            OutputStream out = response.getOutputStream();
            out.write(resultJson.getBytes());
            out.close();
        }
    }
}
