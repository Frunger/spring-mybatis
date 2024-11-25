package cn.edu.gench.javaee.fengjingxuan2220680.mapper;

import cn.edu.gench.javaee.fengjingxuan2220680.po.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    // 查询所有用户
    @Select("SELECT id, username, birthday, sex, address FROM users")
    List<User> selectAll();

    // 插入用户
    @Insert("INSERT INTO users (id, username, birthday, sex, address) " +
            "VALUES (#{id}, #{username}, #{birthday}, #{sex}, #{address})")
    void insertUser(User user);


    // 更新用户
    @Update("UPDATE users SET username = #{username}, birthday = #{birthday}, sex = #{sex}, address = #{address} " +
            "WHERE id = #{id}")
    void updateUser(User user);

    // 删除用户
    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteUser(int id);

    // 根据用户名模糊查询
    @Select("SELECT id, username, birthday, sex, address FROM users WHERE username LIKE CONCAT('%', #{username}, '%')")
    List<User> findByName(String username);

    @Select("SELECT id, username, birthday, sex, address FROM users WHERE id = #{id}")
    User selectUserById(Integer id);

}
