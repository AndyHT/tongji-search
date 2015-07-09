package com.huoteng.servlet;

import com.huoteng.controller.HibernateController;
import com.huoteng.json.Json;
import com.huoteng.lucene.IndexDirectory;
import com.huoteng.lucene.SearchIndex;
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
import java.util.Dictionary;
import java.util.List;

/**
 * 处理manage.html页面发来的请求
 * Created by huoteng on 5/26/15.
 */
public class ManageDataServlet extends HttpServlet {

    /**
     *
     * @param request request
     * @param response response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String order = request.getParameter("order");
        String content = request.getParameter("content");
        System.out.println("got order:" + order);
        System.out.println("got content:" + content);

        HibernateController hibernate = new HibernateController();
        String data = "";
        if (order.equals("add")) {
            //根据content增加TargetURL
            hibernate.addNewTargetURL(content);

        } else if (order.equals("deleteGot")) {
            //根据content删除GotURL
                int deletedNumber = hibernate.deleteGotURLbyURL(content);
                //将返回的List转为String
                data += deletedNumber;


        } else if (order.equals("deleteTarget")) {
            //根据content删除TargetURL
                int deletedNumber = hibernate.deleteTargetURLbyURL(content);
                data += deletedNumber;

        } else if (order.equals("update")) {
            //刷新页面数据,返回data

                List urls = hibernate.findAllGotURL();
                try {
                    data += Json.changeUrlListToJson(urls, true);
                    urls.clear();
                    urls = hibernate.findAllTargetURL();
                    String temp = Json.changeUrlListToJson(urls, false);
                    data = "[" + data + "," + temp + "]";
                } catch (JSONException e) {
                    e.printStackTrace();
                }

        } else if (order.equals("completed")) {
            //根据database中的content重新建立索引
                List urls = hibernate.findAllGotURL();

                IndexDirectory.createIndex(urls);
                data = "succeed";

        }
        response.addHeader("Content-Type", "text/javascript;charset=utf-8");
        response.addHeader("Cache-Control", "private");

        //完成后应答前端
        OutputStream out = response.getOutputStream();

        out.write(data.getBytes());
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
