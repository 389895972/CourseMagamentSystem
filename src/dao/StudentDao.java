package dao;

import org.junit.Test;
import page.First;
import utils.JDBCUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentDao implements Dao {
    Connection conn=null;
    PreparedStatement pstmt=null;
    int id=0;
    Scanner sc=new Scanner(System.in);
    First first=new First();
    public StudentDao() throws SQLException {
    }

    public void studentPage(){
        System.out.println("请选择操作");
        System.out.println("1.查看本人信息 2.修改信息 3.选课 4.查看自己选课情况 5.退出");
        int i=sc.nextInt();
        switch (i){
            case 1:
                query();break;
            case 2:
                update();break;
            case 3:
                select();break;
            case 4:
                querySelect();break;
            case 5:
                first.studentPage();break;
            default:
                System.out.println("输入错误");
        }

    }
    @Override
     public void logic() {
        try {
            conn= JDBCUtils.getConnection();
            System.out.println("请输入id");
            id=sc.nextInt();
            System.out.println("请输入密码");
            String password=sc.next();
            String sql="select * from student where id=? and password=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.setString(2,password);
            ResultSet res=pstmt.executeQuery();
            if(res.next()){
                //String name=res.getString("name");
                System.out.println("登录成功");
                JDBCUtils.close(res,pstmt,conn);
                studentPage();
            }else {
                System.out.println("登录失败");
                first.studentPage();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void query() {
        try {
            conn=JDBCUtils.getConnection();
            System.out.println("学生个人信息：");
            //System.out.println("id-名字-密码-系别-学分-籍贯-电话-email-性别");
            String sql="select * from student where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet res = pstmt.executeQuery();
            if(res.next()){
                int id=res.getInt("id");
                String name=res.getString("name");
                String password=res.getString("password");
                String department=res.getString("department");
                int  mark=res.getInt("mark");
                String home=res.getString("home");
                String tel=res.getString("tel");
                String email=res.getString("email");
                String sex=res.getString("sex");
                System.out.println("查询成功");
                System.out.print("|id:"+id+"|");
                System.out.print("姓名:"+name+"|");
                System.out.print("密码:"+password+"|");
                System.out.print("系别:"+department+"|");
                System.out.print("学分:"+mark+"|");
                System.out.print("籍贯:"+home+"|");
                System.out.print("电话:"+tel+"|");
                System.out.print("email:"+email+"|");
                System.out.println("性别:"+sex+"|");
                System.out.println("==========");
                studentPage();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update() {
        try {
            conn=JDBCUtils.getConnection();
            System.out.println("请选择需要更改的信息");
            System.out.println("1.密码 2.系别 3.籍贯 4.电话 5.Email");
            int i1=sc.nextInt();
            System.out.println("请输入更改后的值");
            switch (i1){
                case 1:
                    String password=sc.next();
                    String sql="update student set password=? where id=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(2,id);
                    pstmt.setString(1,password);
                    int i2 = pstmt.executeUpdate();
                    if(i2>0){
                        System.out.println("更新成功");
                        studentPage();
                    }else {
                        System.out.println("更新失败");
                    }
                    break;
                case 2:
                    String department=sc.next();
                    String sql1="update student set department=? where id=?";
                    pstmt = conn.prepareStatement(sql1);
                    pstmt.setInt(2,id);
                    pstmt.setString(1,department);

                    int i = pstmt.executeUpdate();
                    if(i>0){
                        System.out.println("更新成功");
                        studentPage();
                    }else {
                        System.out.println("更新失败");
                    }
                    break;
                case 3:
                    String home=sc.next();
                    String sql2="update student set home=? where id=?";
                    pstmt = conn.prepareStatement(sql2);
                    pstmt.setInt(2,id);
                    pstmt.setString(1,home);
                    int i3= pstmt.executeUpdate();
                    if(i3>0){
                        System.out.println("更新成功");
                        studentPage();
                    }else {
                        System.out.println("更新失败");
                    }
                    break;
                case 4:
                    String tel=sc.next();
                    String sql3="update student set tel=? where id=?";
                    pstmt = conn.prepareStatement(sql3);
                    pstmt.setInt(2,id);
                    pstmt.setString(1,tel);

                    int i4 = pstmt.executeUpdate();
                    if(i4>0){
                        System.out.println("更新成功");
                        studentPage();
                    }else {
                        System.out.println("更新失败");
                    }
                    break;
                case 5:
                    String email=sc.next();
                    String sql4="update student set email=? where id=?";
                    pstmt = conn.prepareStatement(sql4);
                    pstmt.setInt(2,id);
                    pstmt.setString(1,email);

                    int i5 = pstmt.executeUpdate();
                    if(i5>0){
                        System.out.println("更新成功");
                        studentPage();
                    }else {
                        System.out.println("更新失败");
                    }
                    break;
                default:
                    System.out.println("输入错误");
                    studentPage();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void regist() {

    }

    @Override
    public void delete() {


    }

    @Override
    public void add() {

    }
     @Test
    public void select(){
        try {
            conn=JDBCUtils.getConnection();
            String sql="select * from course";
            pstmt= conn.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery();
            System.out.println("课程信息表：");
             while(res.next()){
                 int course_id=res.getInt("id");
                 String name=res.getString("name");
                 int  mark=res.getInt("mark");
                 String department=res.getString("department");
                 String prepare=res.getString("prepare");
                 System.out.println("|id:"+course_id+"|course:"+name+"|mark:"+mark+"|department:"+department+"|prepare:"+prepare+"|");
             }
             //JDBCUtils.close(res,pstmt,conn);
            //conn=JDBCUtils.getConnection();
            System.out.println("请选择课程id");
            int m=sc.nextInt();
             String sql1="select * from course where id=?";
             PreparedStatement pstmt1= conn.prepareStatement(sql1);
             pstmt1.setInt(1,m);
            ResultSet res1 = pstmt1.executeQuery();
            while(res1.next()){
                int course_id=res1.getInt("id");
                int mark1=1;
                if(course_id==m){
                    String name1=res1.getString("name");
                    mark1=res1.getInt("mark");
                }
                String sql2="insert into enrol values(?,?,?,0);";
                pstmt1= conn.prepareStatement(sql2);
                pstmt1.setInt(1,id);
                pstmt1.setInt(2,course_id);
                pstmt1.setInt(3,mark1);
                int q = pstmt1.executeUpdate();
                if(q>0){
                    System.out.println("选课成功");
                    String sql3="select * from enrol where stu_id=?";
                    pstmt= conn.prepareStatement(sql3);
                    pstmt.setInt(1,id);
                    ResultSet res3 = pstmt.executeQuery();
                    System.out.println("课程信息表：");
                    while(res3.next()){
                        int stu_id1=res3.getInt("stu_id");
                        int course_id1=res3.getInt("course_id");
                        int  mark=res3.getInt("mark");
                        int  score=res3.getInt("score");
                        System.out.println("|学生id:"+stu_id1+"|课程id:"+course_id1+"|学分:"+mark+"|成绩:"+score+"|");
                    }
                    studentPage();
                }else{
                    System.out.println("选课失败");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
  public void querySelect(){
      try {
          conn=JDBCUtils.getConnection();
          String sql3="select * from enrol where stu_id=?";
          pstmt= conn.prepareStatement(sql3);
          pstmt.setInt(1,id);
          ResultSet res3 = pstmt.executeQuery();
          System.out.println("课程信息表：");
          while(res3.next()){
              int stu_id1=res3.getInt("stu_id");
              int course_id1=res3.getInt("course_id");
              int  mark=res3.getInt("mark");
              int  score=res3.getInt("score");
              System.out.println("|学生id:"+stu_id1+"|课程id:"+course_id1+"|学分:"+mark+"|成绩:"+score+"|");
          }
          studentPage();
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }

}
