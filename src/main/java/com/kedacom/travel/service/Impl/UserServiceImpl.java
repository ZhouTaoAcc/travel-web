package com.kedacom.travel.service.Impl;

import com.kedacom.travel.dao.Impl.UserDaoImpl;
import com.kedacom.travel.dao.UserDao;
import com.kedacom.travel.domain.User;
import com.kedacom.travel.service.UserService;
import com.kedacom.travel.util.MailUtils;
import com.kedacom.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    @Override
    public boolean regist(User user) {
        User user1 = userDao.findByUsername(user.getUsername());
        if(user1!=null){//用户存在
            return  false;
        }
        //设置激活码和激活状态
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        userDao.regist(user);
        //注册成功发送邮件
        String context="<a href='http://localhost/travel_web/activeUserServlet?code="+user.getCode()+"'>点击激活</a>";
        MailUtils.sendMail(user.getEmail(),context,"激活邮件");
        return true;
    }

    @Override
    public boolean active(String code) {
       User user=userDao.findByCode(code);
       if(user==null){
           return false;
       }
       userDao.updateStatus(user);
       return true;
    }

    @Override
    public User login(User user) {
      User user1= userDao.findByUsernameAndPassWord(user.getUsername(),user.getPassword());
        return user1;
    }


}
