package com.huoteng.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by huoteng on 5/11/15.
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String password = request.getParameter("pass");
        /**
         * 访问数据库判断用户是否存在
         */
        if (user.equals("admin") && password.equals("admin")) {
            HttpSession loginSession = request.getSession();
            loginSession.setAttribute("username",user);
            response.sendRedirect(request.getContextPath() + "/manage.html");
        } else {
            response.sendRedirect(request.getContextPath() + "/loginfail.html");
        }
    }
}
