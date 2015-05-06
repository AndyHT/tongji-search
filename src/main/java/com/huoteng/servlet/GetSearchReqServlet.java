package com.huoteng.servlet;

import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.ServletException;

/**
 * get search request from index.html
 * Created by huoteng on 5/6/15.
 */
public class GetSearchReqServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        //貌似不需要doPost Method
    }

    /**
     * 调用Lucene，根据request查询index
     * @param request request
     * @param response response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
