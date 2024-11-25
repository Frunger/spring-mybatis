package cn.edu.gench.javaee.fengjingxuan2220680.test;

import cn.edu.gench.javaee.fengjingxuan2220680.po.User;
import cn.edu.gench.javaee.fengjingxuan2220680.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;

public class DemoMain1 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        UserService userService = context.getBean(UserService.class);

        // 插入新用户
        User newUser = new User();
        newUser.setUsername("sheep");
        newUser.setSex("F");
        newUser.setBirthday(java.sql.Date.valueOf("1999-01-01"));
        newUser.setAddress("Shanghai");
        userService.addUser(newUser);

        // 查询所有用户
        userService.getAllUsers().forEach(user -> {
            System.out.println("ID: " + user.getId());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Sex: " + user.getSex());
            System.out.println("Birthday: " + new SimpleDateFormat("yyyy-MM-dd").format(user.getBirthday()));
            System.out.println("Address: " + user.getAddress());
            System.out.println("--------------------------------");
        });

        // 根据 ID 查询用户
        int userId = 1; // 假设要查询的用户 ID 为 1
        User userById = userService.getUserById(userId);
        if (userById != null) {
            System.out.println("根据 ID 查询到的用户信息:");
            System.out.println("ID: " + userById.getId());
            System.out.println("Username: " + userById.getUsername());
            System.out.println("Sex: " + userById.getSex());
            System.out.println("Birthday: " + new SimpleDateFormat("yyyy-MM-dd").format(userById.getBirthday()));
            System.out.println("Address: " + userById.getAddress());
            System.out.println("--------------------------------");
        } else {
            System.out.println("未找到 ID 为 " + userId + " 的用户。");
        }

        // 模糊查询
        System.out.println("模糊查询结果:");
        userService.searchUsersByName("sheep").forEach(user -> {
            System.out.println(user.getUsername());
        });
    }
}
