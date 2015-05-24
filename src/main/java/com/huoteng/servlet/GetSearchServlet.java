package com.huoteng.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 接收从网页端发来的查询请求
 * 将请求提交给lucene查询
 * 接收QueryResponse返回的查询结果返回给网页(json格式)
 * Created by huoteng on 5/24/15.
 */
public class GetSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
