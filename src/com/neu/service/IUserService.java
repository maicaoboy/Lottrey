package com.neu.service;

import com.neu.pojo.User;

public interface IUserService {
    //注册
    public boolean register();

    //登录
    public boolean login();

    ////抽奖 return: 5个数的数组，中奖结果
    Object[] lottery(User loginUser);

    //currentUser（当前登录用户）的setter
    public void setCurrentUser(User currentUser);

    //currentUser（当前登录用户）getter
    public User getCurrentUser();
}
