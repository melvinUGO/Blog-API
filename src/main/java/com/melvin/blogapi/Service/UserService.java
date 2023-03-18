package com.melvin.blogapi.Service;

import com.melvin.blogapi.Model.User;

public interface UserService {
    void register(User user);

    User findUser(Integer id);
}
