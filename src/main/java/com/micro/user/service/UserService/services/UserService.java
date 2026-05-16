package com.micro.user.service.UserService.services;

import com.micro.user.service.UserService.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUser();
    User getUser(String userId);
    String deleteUser(String userId);
    User updateUser(String userId, User user);
    User patchUser(String userId, User partialUser);
}
