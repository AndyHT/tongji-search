package com.huoteng.servlet;

import com.huoteng.controller.HibernateController;
import com.huoteng.json.Json;
import org.json.JSONException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        boolean isSuccess = false;
        HibernateController hibernate = new HibernateController();
        String data = "";
        if (order.equals("add")) {
            //根据content增加TargetURL
            if (hibernate.begin()) {
                hibernate.addNewTargetURL(content);
                isSuccess = true;
            }
        } else if (order.equals("deleteGot")) {
            //根据content删除GotURL
            if (hibernate.begin()) {
                int deletedNumber = hibernate.deleteGotURLbyID(Integer.parseInt(content));
                //将返回的List转为String
                data += deletedNumber;

                isSuccess = true;
            }

        } else if (order.equals("deleteTarget")) {
            //根据content删除TargetURL
            if (hibernate.begin()) {
                int deletedNumber = hibernate.deleteTargetURLbyID(Integer.parseInt(content));
                data += deletedNumber;

                isSuccess = true;
            }
        } else if (order.equals("update")) {
            //刷新页面数据,返回data
            if (hibernate.begin()) {

                List urls = hibernate.findAllGotURL();
                try {
                    data += Json.changeUrlListToJson(urls, true);
                    urls.clear();
                    urls = hibernate.findAllTargetURL();
                    data += Json.changeUrlListToJson(urls, false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                isSuccess = true;
            }
        }
        response.addHeader("Content-Type", "text/javascript;charset=utf-8");
        response.addHeader("Cache-Control", "private");

        //完成后应答前端
        OutputStream out = response.getOutputStream();

        //需要把resp改成json格式
        String resp = Boolean.toString(isSuccess) + "," + order + "," + data;

        out.write(resp.getBytes());
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
