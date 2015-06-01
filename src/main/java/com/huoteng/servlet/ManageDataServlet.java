package com.huoteng.servlet;

import com.huoteng.controller.HibernateController;
import com.huoteng.json.Json;

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
        String data = "";
        if (order.equals("add")) {
            //根据content增加TargetURL
            if (HibernateController.init() && HibernateController.begin()) {
                isSuccess = HibernateController.addNewTargetURL(content);
            }
        } else if (order.equals("deleteGot")) {
            //根据content删除GotURL
            if (HibernateController.init() && HibernateController.begin()) {
                List gotURL = HibernateController.deleteGotURLbyID(content);
                //将返回的List转为String
                data = Json.changeUrlListToJson(gotURL);

                isSuccess = true;
            }

        } else if (order.equals("deleteTarget")) {
            //根据content删除TargetURL
            if (HibernateController.init() && HibernateController.begin()) {
                List targetURl = HibernateController.deleteTargetURLbyID(content);
                //将返回的List转为String
                data = Json.changeUrlListToJson(targetURl);

                isSuccess = true;
            }
        } else if (order.equals("update")) {
            //刷新页面数据,返回data
            if (HibernateController.init() && HibernateController.begin()) {

                List urls = HibernateController.findAllGotURL();
                urls.addAll(HibernateController.findAllTargetURL());
                data = Json.changeUrlListToJson(urls);

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
