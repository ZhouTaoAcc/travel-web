package com.kedacom.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kedacom.travel.domain.ResultInfo;
import com.kedacom.travel.domain.User;
import com.kedacom.travel.service.Impl.UserServiceImpl;
import com.kedacom.travel.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService userService=new UserServiceImpl();
        User user1=userService.login(user);
//        System.out.println(user);
//        System.out.println(user1);
        ResultInfo info=new ResultInfo();
        if(user1==null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");

        }
        if(user1!=null&&"N".equals(user1.getStatus())){
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请登录邮箱激活！");
        }
        if(user1!=null&&"Y".equals(user1.getStatus())){
            info.setFlag(true);
        request.getSession().setAttribute("user_session",user1);
        }
        //响应数据
        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   this.doPost(request,response);
    }
}
