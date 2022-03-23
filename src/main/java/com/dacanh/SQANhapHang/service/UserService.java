package com.dacanh.SQANhapHang.service;

import com.dacanh.SQANhapHang.model.Role;
import com.dacanh.SQANhapHang.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
