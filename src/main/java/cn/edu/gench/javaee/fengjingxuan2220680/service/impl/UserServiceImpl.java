package cn.edu.gench.javaee.fengjingxuan2220680.service.impl;

import cn.edu.gench.javaee.fengjingxuan2220680.mapper.UserMapper;
import cn.edu.gench.javaee.fengjingxuan2220680.po.User;
import cn.edu.gench.javaee.fengjingxuan2220680.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    @Override
    public void addUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }

    @Override
    public User getUserById(int id) {
        return userMapper.selectUserById(id);
    }


    @Override
    public List<User> searchUsersByName(String name) {
        return userMapper.findByName(name);
    }

    /**
     * 修改用户主键 ID
     * @param oldId 原始 ID
     * @param newId 新的 ID
     */
    @Override
    @Transactional // 确保操作的原子性
    public void updateUserId(int oldId, int newId) {
        // 检查目标 ID 是否已存在
        User existingUser = userMapper.selectUserById(newId);
        if (existingUser != null) {
            throw new IllegalArgumentException("目标 ID " + newId + " 已存在，无法更新 ID");
        }

        // 查询原始用户信息
        User user = userMapper.selectUserById(oldId);
        if (user == null) {
            throw new IllegalArgumentException("用户 ID " + oldId + " 不存在，无法更新 ID");
        }

        // 删除旧记录
        userMapper.deleteUser(oldId);

        // 设置新 ID 并插入新记录
        user.setId(newId);
        userMapper.insertUser(user);

        // 确保新记录插入成功
        User updatedUser = userMapper.selectUserById(newId);
        if (updatedUser == null) {
            throw new RuntimeException("更新用户 ID 失败，回滚操作");
        }
    }

}
