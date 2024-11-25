package cn.edu.gench.javaee.fengjingxuan2220680.test;

import cn.edu.gench.javaee.fengjingxuan2220680.mapper.UserMapper;
import cn.edu.gench.javaee.fengjingxuan2220680.po.User;
import cn.edu.gench.javaee.fengjingxuan2220680.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;

public class DemoMain2 {
    public static void main(String[] args) {
        // 加载 Spring 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        UserMapper userMapper = context.getBean(UserMapper.class);

        // 修改用户 ID
        System.out.println("----- 修改用户ID -----");
        UserService userService = context.getBean(UserService.class); // 获取 UserService 实例

        int oldId = 9;
        int newId = 2;

        try {
            userService.updateUserId(oldId, newId);
            System.out.println("用户 ID 已从 " + oldId + " 更新为 " + newId);
        } catch (Exception e) {
            System.err.println("更新失败：" + e.getMessage());
        }


        // 更新用户信息
        System.out.println("----- 更新用户信息 -----");
        User updateUser = new User();
        updateUser.setId(1); // 假设要更新的用户 ID 为 1
        updateUser.setUsername("emma");
        updateUser.setBirthday(java.sql.Date.valueOf("1995-05-20"));
        updateUser.setSex("M");
        updateUser.setAddress("ShangHai");
        userMapper.updateUser(updateUser);
        System.out.println("用户更新成功: ID = " + updateUser.getId());

        // 删除用户
        System.out.println("----- 删除用户 -----");
        int deleteUserId = 7; // 假设要删除的用户 ID 为 2
        userMapper.deleteUser(deleteUserId);
        System.out.println("用户删除成功: ID = " + deleteUserId);


    }
}
