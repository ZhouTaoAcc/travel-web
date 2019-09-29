package com.kedacom.travel.web.servlet;

import com.kedacom.travel.service.Impl.UserServiceImpl;
import com.kedacom.travel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");//获取activeUserServlet?code
        if(code!=null){
            UserService userService=new UserServiceImpl();
            boolean flag=userService.active(code);
            String msg=null;
            if(flag){
                msg="激活成功，<a href='login.html'>请登录</a>";
            }else{
                msg="激活失败，联系管理员！";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }
}
