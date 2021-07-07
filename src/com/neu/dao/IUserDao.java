package com.neu.dao;

import com.neu.pojo.User;

public interface IUserDao {
    //添加用户
    public void addUser(User user);

    //用户数组getter
    public User[] getUserList();

    //检查会员号是否存在，返回boolean值
    public boolean codeAvailable(int code);

    //通过用户名查找用户，并返回用户实例
    public User findUserByName(String name);

    //验证用户
    public boolean verify(User user);
}
