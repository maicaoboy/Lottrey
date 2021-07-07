package com.neu.pojo;

import java.util.Objects;

public class User {
    //用户名
    private String name;
    //密码
    private String password;
    //会员号
    private int code;

    //构造方法，第一个参数是用户名，第二个参数为密码，第三个参数为会员号
    public User(String name, String password, int code) {
        this.name = name;
        this.password = password;
        this.code = code;
    }

    //取得用户名
    public String getName() {
        return this.name;
    }

    //取得密码
    public String getPassword() {
        return this.password;
    }

    //取得会员号
    public int getCode() {
        return this.code;
    }


    //重写equals方法，仅比较用户名和密码，相等返回true，否则返回false
    @Override
    public boolean equals(Object o) {
        User user = (User) o;
        return this.name.equals(user.getName()) && this.password.equals(user.getPassword());
    }

}
