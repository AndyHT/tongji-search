package com.huoteng.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Dictionary;

/**
 * 处理manage.html页面发来的请求
 * Created by huoteng on 5/26/15.
 */
public class ManageDataServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String order = request.getParameter("order");
        String content = request.getParameter("content");

        boolean isSuccess = false;
        String data = "";
        if (order.equals("add")) {
            //根据content写入DB

            isSuccess = true;
        } else if (order.equals("delete")) {
            //根据content删除DB

            isSuccess = true;
        } else if (order.equals("update")) {
            //刷新页面数据,返回data

            isSuccess = true;
        } else {
            //illegal order
        }

        response.addHeader("Content-Type", "text/javascript;charset=utf-8");
        response.addHeader("Cache-Control", "private");

        //完成后应答前端
        OutputStream out = response.getOutputStream();

        //需要改成json格式
        String resp = Boolean.toString(isSuccess) + "," + order + "," + data;
        out.write(resp.getBytes());
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
