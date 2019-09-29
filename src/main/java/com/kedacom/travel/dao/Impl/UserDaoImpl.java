package com.kedacom.travel.dao.Impl;

import com.kedacom.travel.dao.UserDao;
import com.kedacom.travel.domain.User;
import com.kedacom.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;


public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            String sql = "select * from tab_user where username= ?";
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        } catch (Exception e) {

        }
        return user;
    }
        //查询激活码
    @Override
    public User findByCode(String code) {
        User user = null;
        try {
            String sql = "select * from tab_user where code= ?";
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), code);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return user;
    }
//更新激活状态
    @Override
    public void updateStatus(User user) {
      String sql="update tab_user set status='Y' where uid=?";
      jdbcTemplate.update(sql,user.getUid());
    }
    //登录
    @Override
    public User findByUsernameAndPassWord(String username, String password) {
        User user = null;
        try {
            String sql = "select * from tab_user where username= ? and password=?";
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username,password);
        } catch (Exception e) {

        }
        return user;
    }

    //注册
    @Override
    public void regist(User user) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) " +
                "values(?,?,?,?,?,?,?,?,?)";
            jdbcTemplate.update(sql,
                    user.getUsername(),
                    user.getPassword(),
                    user.getName(),
                    user.getBirthday(),
                    user.getSex(),
                    user.getTelephone(),
                    user.getEmail(),
                    user.getStatus(),
                    user.getCode());

    }


}
