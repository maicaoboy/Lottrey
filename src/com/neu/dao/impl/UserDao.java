package com.neu.dao.impl;

import com.neu.dao.IUserDao;
import com.neu.pojo.User;

import java.util.Arrays;

public class UserDao implements IUserDao {
    private static UserDao instance;

    public static UserDao getInstance() {
        return instance;
    }

    private UserDao() {
        instance = new UserDao();
    }

    //存放已注册用户的数组
    private User[] userList = new User[0];

    //用户数组getter
    public User[] getUserList() {
        return userList;
    }

    //用户数组getter
    public void addUser(User user) {
        userList = Arrays.copyOf(userList, this.userList.length + 1);
        userList[userList.length - 1] = user;
    }

    //检查生产的四位随机数会员号是否可用
    public boolean codeAvailable(int code) {
        boolean available = true;
        //遍历检查会员号是否已经使用过
        for(User user : userList) {
            if(user.getCode() == code){
                available = false;
                break;
            }
        }
        //返回查询结果
        return available;
    }

    //通过用户名查找用户，并返回用户实例
    public User findUserByName(String name) {
        User find = null;
        //遍历检查用户名
        for(User user : userList) {
            if(user.getName().equals(name)){
                find = user;
            }
        }
        //返回查找的用户
        return find;
    }

    //验证用户
    public boolean verify(User user) {
        boolean verify = false;
        //调用User重写的equals（）方法，验证登陆用户是否存在
        for(User user1 : userList) {
            if(user1.equals(user)) {
                verify = true;
                break;
            }
        }
        //返回检擦结果
        return verify;
    }
}
