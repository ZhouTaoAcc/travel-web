package com.kedacom.travel.dao;

import com.kedacom.travel.domain.User;

public interface UserDao {

    void regist(User user);

    User findByUsername(String username);

    User findByCode(String code);

    void updateStatus(User user);

    User findByUsernameAndPassWord(String username, String password);
}
