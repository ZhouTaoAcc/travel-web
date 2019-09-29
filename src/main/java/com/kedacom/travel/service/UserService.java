package com.kedacom.travel.service;

import com.kedacom.travel.domain.User;

public interface UserService {
    boolean regist(User user);

    boolean active(String code);

    User login(User user);
}
