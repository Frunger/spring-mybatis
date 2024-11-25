package cn.edu.gench.javaee.fengjingxuan2220680.service;

import cn.edu.gench.javaee.fengjingxuan2220680.po.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    List<User> searchUsersByName(String name);
    void updateUserId(int oldId, int newId);
    User getUserById(int id);

}
