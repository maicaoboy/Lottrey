package com.neu.view;

import com.neu.service.IUserService;
import com.neu.service.impl.UserService;
import java.util.Scanner;

public class SystemUI {
    public static void main(String[] args) {
        //业务实例
        IUserService service = UserService.getInstance();
        Scanner scan = new Scanner(System.in);

        //中奖信息
        Object[] lottery = new Object[2];
        //用户选择序号
        int select = 0;
        while(true) {
            //输出主操作界面
            mainInterface();
            select = scan.nextInt();
            switch(select) {
                case 1:
                    service.register();
                    break;
                case 2:
                    service.login();
                    break;
                case 3:
                    if(service.getCurrentUser() == null){
                        System.out.println("请先进行登录.");
                        break;
                    }
                    lottery = service.lottery(service.getCurrentUser());
                    System.out.print("您的会员卡号为：" + service.getCurrentUser().getCode() + ",");
                    SystemUI.display(lottery);
                    service.setCurrentUser(null);
                    break;
            }
            System.out.print("继续吗？(y/n):");
            if(scan.next().charAt(0) != 'y'){
                service.setCurrentUser(null);
                System.out.println("系统退出，谢谢使用。");
                break;
            }
        }
    }

    //抽奖提示信息输出，Object[0]为五个随机数的数组，Object[1]为是否中奖的布尔值
    public static void display(Object[] lottery) {
        //判断是否中奖
        if((boolean) lottery[1]){
            System.out.println("恭喜中奖。");
        }else{
            System.out.println("抱歉未中奖。");
        }
        //输出随机数
        System.out.println("以下为本期中奖号码");
        for(int a : (int[])lottery[0]){
            System.out.print(a + " ");
        }
        System.out.println("");
    }

    //参数为提示，返回值获得输入
    public static String getString(String reminder) {
        //保存输入的变量
        String get = null;
        Scanner scan = new Scanner(System.in);
        System.out.print(reminder + ":");
        return scan.next();
    }

    //注册成功提示，参数为用户名,密码和会员号
    public static void registerReminder(String name, String password, int code) {
        System.out.println("注册成功，请记好您的会员卡号.");
        System.out.println("用户名: " + name);
        System.out.println("密码： " + password);
        System.out.println("会员卡号： " + code);
    }

    //主界面显示菜单
    public static void mainInterface() {
        System.out.println("******欢迎进入奖客富翁系统******");
        System.out.println("      1.注册      ");
        System.out.println("      2.登录      ");
        System.out.println("      3.抽奖      ");
        System.out.println("****************************");
        System.out.print("请选择菜单：");
    }

    //是否继续的提示（参数）及输入（返回值）
    public static char continueOrNot(String reminder) {
        char get = 'n';
        Scanner scan = new Scanner(System.in);
        System.out.print(reminder + ":");
        get = scan.next().charAt(0);
        return get;
    }

}
