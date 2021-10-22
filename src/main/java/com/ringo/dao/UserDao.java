package com.ringo.dao;

import com.ringo.vo.User;
import com.ringo.vo.UserQuery;

import java.util.List;

public interface UserDao {
    public User queryUserByName(String name);

    public User queryUserById(Integer id);

    public int save(User user);

    public int update(User user);

    //多条件查询有分页
    public List<User> selectByParameters(UserQuery userQuery);

    public  int delete(Integer id);
}
