package dao;

import org.junit.Test;
import page.First;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TeacherDao implements Dao{
    Connection conn=null;
    PreparedStatement pstmt=null;
    StudentDao studentDao=new StudentDao();
    First first=new First();
    int id=0;
    Scanner sc=new Scanner(System.in);
    public TeacherDao() throws SQLException {
    }
    public void teacherPage(){
        System.out.println("欢迎教师请选择操作");
        System.out.println("1.学生管理 2.教师管理 3.退出");
        int i=sc.nextInt();
        switch (i){
            case 1:
                teacherStuPage();break;
            case 2:
                teacherTeaPage();break;
            case 3:
                first.teacherPage();break;
            default:
                System.out.println("输入错误,请重新输入");
                teacherPage();
        }
    }
    public void teacherStuPage(){
        System.out.println("请选择操作");
        System.out.println("1.查看学生信息 2.退出");
        int i=sc.nextInt();
        switch (i){
            case 1:
                queryAllStu();
                break;
            case 2:
                teacherPage();
                break;
            default:
                System.out.println("输入错误");
                teacherPage();
        }
    }
    public void queryAllStu(){
        try {
            conn=JDBCUtils.getConnection();
            System.out.println("全部学生个人信息：");
            String sql="select * from student";
            pstmt = conn.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery();
            while (res.next()){
                int id=res.getInt("id");
                String name=res.getString("name");
                String password=res.getString("password");
                String department=res.getString("department");
                int  mark=res.getInt("mark");
                String home=res.getString("home");
                String tel=res.getString("tel");
                String email=res.getString("email");
                String sex=res.getString("sex");

                System.out.print("|id:"+id+"|");
                System.out.print("姓名:"+name+"|");
                System.out.print("密码:"+password+"|");
                System.out.print("系别:"+department+"|");
                System.out.print("学分:"+mark+"|");
                System.out.print("籍贯:"+home+"|");
                System.out.print("电话:"+tel+"|");
                System.out.print("email:"+email+"|");
                System.out.println("性别:"+sex+"|");
            }
            System.out.println("========");
            teacherPage();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void teacherTeaPage(){
        System.out.println("请选择操作");
        System.out.println("1.查看本人信息 2.修改信息 3.教课 4.登记成绩  5.退出");
        int i=sc.nextInt();
        switch (i){
            case 1:
                query();break;
            case 2:
                update();break;
            case 3:
                teach();break;
            case 4:
                record();break;
            case 5:
                teacherPage();break;
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
            String sql="select * from teacher where id=? and password=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.setString(2,password);
            ResultSet res=pstmt.executeQuery();
            if(res.next()){
                //String name=res.getString("name");
                System.out.println("登录成功");
                JDBCUtils.close(res,pstmt,conn);
                teacherPage();
            }else {
                System.out.println("登录失败");
                first.teacherPage();
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
    public void update() {
        try {
            conn=JDBCUtils.getConnection();
            System.out.println("请选择需要更改的信息");
            System.out.println("1.密码 2.专业 3.退出");
            int i1=sc.nextInt();

            switch (i1){
                case 1:
                    System.out.println("请输入更改后的值");
                    String password=sc.next();
                    String sql="update techer set password=? where id=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(2,id);
                    pstmt.setString(1,password);
                    int i2 = pstmt.executeUpdate();
                    if(i2>0){
                        System.out.println("更新成功");
                        teacherPage();
                    }else {
                        System.out.println("更新失败");
                    }
                    break;
                case 2:
                    System.out.println("请输入更改后的值");
                    String career=sc.next();
                    String sql1="update teacher set career=? where id=?";
                    pstmt = conn.prepareStatement(sql1);
                    pstmt.setInt(2,id);
                    pstmt.setString(1,career);
                    int i = pstmt.executeUpdate();
                    if(i>0){
                        System.out.println("更新成功");
                        teacherTeaPage();
                    }else {
                        System.out.println("更新失败");
                    }
                    break;
                case 3:
                    teacherTeaPage();break;
                default:
                    System.out.println("输入错误");
                    update();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void query() {
        try {
            conn=JDBCUtils.getConnection();
            System.out.println("教师个人信息：");
            //System.out.println("id-名字-密码-系别-学分-籍贯-电话-email-性别");
            String sql="select * from teacher where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet res = pstmt.executeQuery();
            if(res.next()){
                int id=res.getInt("id");
                String name=res.getString("name");
                String password=res.getString("password");
                String career=res.getString("career");
                System.out.print("|id:"+id+"|");
                System.out.print("姓名:"+name+"|");
                System.out.print("密码:"+password+"|");
                System.out.println("专业:"+career+"|");
                System.out.println("=======");
                teacherPage();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void add() {

    }
@Test
    public void teach(){
        try {
            conn=JDBCUtils.getConnection();
            //select * from student,course,enrol,teacher where teacher.cou_id=course.id=enrol.course_id and enrol.stu_id=student.id;
            String sql="select student.id,student.name from teacher,student,course,enrol where teacher.id=? and teacher.cou_id=course.id and enrol.course_id=course.id and enrol.stu_id=student.id;";
           //String sql="select student.id,student.name from teacher,student,course,enrol where  teacher.cou_id=course.id=enrol.course_id=? and enrol.stu_id=student.id;";
           //String sql="select student.id,student.name from teacher,student,course,enrol where teacher.cou_id=course.id=enrol.course_id and enrol.stu_id=student.id;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet res9 = pstmt.executeQuery();
            while(res9.next()){
                int id=res9.getInt("id");
                String name=res9.getString("name");
                System.out.println("id:"+id+"|姓名："+name);
            }
            //teacherPage();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void record() {
        try {
            teach();
            System.out.println("请输入学生id：");
            int n=sc.nextInt();
            System.out.println("请输入成绩：");
            int s=sc.nextInt();
            conn=JDBCUtils.getConnection();
            //String sql="update enrol set score=? where enrol.course_id=course.id and course.id=teacher.cou_id and teacher.id=? and enrol.id=?";
            String sql="update enrol set score=? where  course_id = (select cou_id from teacher where id=?) and stu_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,s);
            pstmt.setInt(2,id);
            pstmt.setInt(3,n);
//            pstmt.setInt(1,55);
//            pstmt.setInt(2,2);
//            pstmt.setInt(3,4);
            int j= pstmt.executeUpdate();
            if(j>0){
                System.out.println("录入成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
