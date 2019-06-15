package page;

import dao.AdminDao;
import dao.StudentDao;
import dao.TeacherDao;
import entity.Teacher;

import java.sql.SQLException;
import java.util.Scanner;

public class First {
    static  Scanner sc=new Scanner(System.in);
    public static void main(String[] args) throws Exception {
         show();
    }
    public static void show(){
        System.out.println("欢迎使用课绩管理系统");
        System.out.println("请选择身份：1.学生 2.教师 3.管理员");
        int i=sc.nextInt();
        switch (i){
            case 1:
                studentPage();break;
            case 2:
                teacherPage();break;
            case 3:
                adminPage();break;
            default:
                System.out.println("输入错误,请重新输入");
                show();
        }
    }
    public static void studentPage(){
        try {
            StudentDao studentDao =new StudentDao();
            System.out.println("欢迎使用课绩管理系统");
            System.out.println("请选择操作：1.学生登录  2.退出");
            int i=sc.nextInt();
            switch (i){
                case 1:
                    studentDao.logic();break;

                case 2:
                    show();break;
                    default:
                        System.out.println("输入错误，请重新输入");
                        studentPage();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void teacherPage(){
        try {
            TeacherDao teacherDao =new TeacherDao();
            System.out.println("欢迎使用课绩管理系统");
            System.out.println("请选择操作：1.教师登录 2.教师注册 3.退出");
            int i=sc.nextInt();
            switch (i){
                case 1:
                    teacherDao.logic();break;
                case 2:
                    teacherDao.regist();break;
                case 3:
                    show();break;
                default:
                    System.out.println("输入错误,请重新输入");
                    teacherPage();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public static void adminPage(){
        AdminDao adminDao=new AdminDao();
        System.out.println("欢迎使用课绩管理系统");
        System.out.println("请输入管理员密码");
        String password=sc.next();
        if(password.equals("123456")){
            System.out.println("超级管理员登录成功");
            System.out.println("请选择操作：1.管理员登录 2.管理员注册 3.退出 ");
            int i=sc.nextInt();
            switch (i){
                case 1:
                    adminDao.logic();break;
                case 2:
                    adminDao.regist();break;
                case 3:
                    show();
                default:
                    System.out.println("输入错误,请重新输入");
                    adminPage();
            }
        }else{
            System.out.println("管理员登录失败");
            adminPage();
        }
    }

}
