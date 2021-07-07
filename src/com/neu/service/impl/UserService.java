package com.neu.service.impl;

import com.neu.dao.IUserDao;
import com.neu.dao.impl.UserDao;
import com.neu.pojo.User;
import com.neu.service.IUserService;
import com.neu.view.SystemUI;

public class UserService implements IUserService {
    private static UserService instance;


    public static UserService getInstance() {
        return instance;
    }


    //用户数组
    private IUserDao userList;
    //用户选择记录
    private char select;
    //当前登录用户
    private User currentUser;

    //currentUser（当前登录用户）的setter
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    //currentUser（当前登录用户）getter
    public User getCurrentUser() {
        return currentUser;
    }

    //构造方法
    private UserService() {
        userList = UserDao.getInstance();
        select = 'n';
        currentUser = null;
        instance = new UserService();
    }

    //登录业务
    public boolean register() {
        System.out.println("[奖客富翁系统->注册]");
        //提示及选择是否继续
        select = SystemUI.continueOrNot("是否继续[y/n]：");
        //选择y进入注册
        if(select == 'y') {
            String name;
            String password;
            int code;
            //使用循环设计保证唯一会员号
            do {
                code = (int) (Math.random() * 9000 + 1000);
            } while (!userList.codeAvailable(code));
            //提示引导用户输入用户名及密码注册
            System.out.println("请填写个人注册信息：");
            name = SystemUI.getString("1.请输入用户名");
            password = SystemUI.getString("2.请输入密码");
            //将注册用户加入用户数组
            userList.addUser(new User(name, password, code));
            //输出注册信息
            SystemUI.registerReminder(name,password,code);
            return true;
        }else {
            return false;
        }
    }

    //登陆业务
    public boolean login() {
        //登录成功标记login，用户名name，密码password
        boolean login = false;
        String name;
        String password;
        //仅有三次尝试登录机会
        for(int i = 0; i < 3; i++ ) {
            System.out.println("[奖客富翁系统->登录]");
            //引导用户输入用户名和密码登录
            name = SystemUI.getString("1.请输入用户名");
            password = SystemUI.getString("2.请输入密码");
            if(login = userList.verify(new User(name, password, 1234))) {
                //标记等级成功的用户
                currentUser = userList.findUserByName(name);
                System.out.println("欢迎您：" + name);
                break;
            }
        }
        return login;
    }

    //抽奖业务
    public Object[] lottery(User loginUser) {
        //num为幸运号码，lucky为中奖标记，Object[]为返回信息数组，包含num和lucky
        int[] num = new int[5];
        boolean lucky = false;
        Object[] objects = new Object[2];
        //生产五个随机数
        for(int i = 0; i < 5; i++ ) {
            num[i] = (int) (Math.random() * 9000 + 1000);
            if(loginUser.getCode() == num[i]) {
                lucky = true;
            }
        }
        //返回值赋值及返回
        objects[0] = num;
        objects[1] = lucky;
        return objects;
    }

}
