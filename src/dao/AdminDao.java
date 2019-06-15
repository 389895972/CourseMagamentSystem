package dao;

import page.First;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminDao implements Dao {
    StudentDao studentDao;
    TeacherDao teacherDao;
    Connection conn;
    {
        try {
            conn = JDBCUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            studentDao = new StudentDao();
            teacherDao=new TeacherDao();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    PreparedStatement pstmt=null;
    int id=0;
    Scanner sc=new Scanner(System.in);

    public void adminPage(){
        System.out.println("欢迎管理员请选择操作");
        System.out.println("1.学生管理 2.教师管理 3.管理员管理");
        int i=sc.nextInt();
        switch (i){
            case 1:
                adminStuPage();break;
            case 2:
                adminTeaPage();break;
            case 3:
                adminAdmDao();break;
            default:
                System.out.println("输入错误");
        }
    }
    public void adminStuPage(){
        System.out.println("请选择操作");
        System.out.println("1.增加学生信息 2.删除学生信息 3.修改学生信息 4.查看学生信息 5.退出");
        int i=sc.nextInt();
        switch (i){
            case 1:
                registStu();break;
            case 2:
                deleteStu();break;
            case 3:
                updateStu();break;
            case 4:
                queryAllStu();break;
            case 5:
                adminPage();break;
            default:
                System.out.println("输入错误");
                adminStuPage();
        }
    }
    public void adminTeaPage(){
        System.out.println("请选择操作");
        System.out.println("1.增加教师信息 2.删除教师信息 3.修改教师信息 4.查看教师信息 5.退出");
        int i=sc.nextInt();
        switch (i){
            case 1:
                registTea();break;
            case 2:
                deleteTea();break;
            case 3:
               updateTea();break;
            case 4:
                queryAllTea();break;
            default:
                System.out.println("输入错误");
                adminTeaPage();
        }
    }
    public void adminAdmDao(){
        System.out.println("请选择操作");
        System.out.println("1.增加管理员信息 2.删除管理员信息 3.修改管理员信息 4.查看管理员信息");
        int i=sc.nextInt();
        switch (i){
            case 1:
                regist();break;
            case 2:
                delete();break;
            case 3:
                update();break;
            case 4:
                query();break;
            default:
                System.out.println("输入错误");
                adminAdmDao();
        }
    }
    public void registStu(){
         try {
            conn= JDBCUtils.getConnection();
            System.out.println("请输入id");
            int id=sc.nextInt();
            String sql2="select id from student where id=?";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setInt(1,id);
            ResultSet res=pstmt.executeQuery();
            if(res.next()){
                System.out.println("id已存在，请重新输入");
               // JDBCUtils.close(res,pstmt,conn);
                regist();
            }else{
                System.out.println("请输入姓名：");
                String name=sc.next();
                System.out.println("请输入密码：");
                String password=sc.next();
                System.out.println("请输入系别：");
                String department=sc.next();
                System.out.println("请输入籍贯：");
                String home=sc.next();
                System.out.println("请输入电话：");
                String tel=sc.next();
                System.out.println("请输入Email：");
                String email=sc.next();
                System.out.println("请输入性别：");
                String sex=sc.next();
                String sql1="insert into student (id,name,password,department,home,tel,email,sex) values(?,?,?,?,?,?,?,?);";
                PreparedStatement pstmt2= conn.prepareStatement(sql1);
                pstmt2.setInt(1,id);
                pstmt2.setString(2,name);
                pstmt2.setString(3,password);
                pstmt2.setString(4,department);
                pstmt2.setString(5,home);
                pstmt2.setString(6,tel);
                pstmt2.setString(7,email);
                pstmt2.setString(8,sex);
                int i = pstmt2.executeUpdate();
                if(i>0){
                    System.out.println("注册成功");
                    adminStuPage();
                }else{
                    System.out.println("注册失败，请重新注册");
                    JDBCUtils.close(null,pstmt2,conn);
                    registStu();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteStu(){
                try {
                    show();
            conn=JDBCUtils.getConnection();
            System.out.println("请输入要删除的id");
            int id=sc.nextInt();
            String sql="delete from student where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            int i = pstmt.executeUpdate();
            if(i>0){
                System.out.println("删除成功");
                show();
                adminStuPage();
            }else {
                System.out.println("删除失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void updateStu(){
        try {
            show();
            conn=JDBCUtils.getConnection();
            System.out.println("请选择需要更改的信息");
            System.out.println("请输入需要修改的id");
            int id=sc.nextInt();
            System.out.println("1.密码 2.系别 3.学分 4.籍贯 5.电话 6.Email");
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
                        show();
                        adminStuPage();
                    }else {
                        System.out.println("更新失败");
                    }
                    break;
                case 2:
                    //System.out.println("请输入更改后的值");
                    String department=sc.next();
                    String sql1="update student set department=? where id=?";
                    pstmt = conn.prepareStatement(sql1);
                    pstmt.setInt(2,id);
                    pstmt.setString(1,department);

                    int i = pstmt.executeUpdate();
                    if(i>0){
                        System.out.println("更新成功");
                        show();
                        adminStuPage();
                    }else {
                        System.out.println("更新失败");
                    }
                    break;
                case 3:
                    //System.out.println("请输入更改后的值");
                    String mark=sc.next();
                    String sql9="update student set mark=? where id=?";
                    pstmt = conn.prepareStatement(sql9);
                    pstmt.setInt(2,id);
                    pstmt.setString(1,mark);
                    int i5= pstmt.executeUpdate();
                    if(i5>0){
                        System.out.println("更新成功");
                        show();
                        adminStuPage();
                    }else {
                        System.out.println("更新失败");
                    }
                    break;
                case 4:
                    //System.out.println("请输入更改后的值");
                    String home=sc.next();
                    String sql2="update student set home=? where id=?";
                    pstmt = conn.prepareStatement(sql2);
                    pstmt.setInt(2,id);
                    pstmt.setString(1,home);
                    int i3= pstmt.executeUpdate();
                    if(i3>0){
                        System.out.println("更新成功");
                        show();
                        adminStuPage();
                    }else {
                        System.out.println("更新失败");
                    }
                    break;

                case 5:
                    //System.out.println("请输入更改后的值");
                    String tel=sc.next();
                    String sql3="update student set tel=? where id=?";
                    pstmt = conn.prepareStatement(sql3);
                    pstmt.setInt(2,id);
                    pstmt.setString(1,tel);
                    int i4 = pstmt.executeUpdate();
                    if(i4>0){
                        System.out.println("更新成功");
                        show();
                        adminStuPage();
                    }else {
                        System.out.println("更新失败");
                    }
                    break;
                case 6:
                    // System.out.println("请输入更改后的值");
                    String email=sc.next();
                    String sql4="update student set email=? where id=?";
                    pstmt = conn.prepareStatement(sql4);
                    pstmt.setInt(2,id);
                    pstmt.setString(1,email);

                    int i6 = pstmt.executeUpdate();
                    if(i6>0){
                        System.out.println("更新成功");
                        show();
                        adminStuPage();
                    }else {
                        System.out.println("更新失败");
                    }
                    break;
                default:
                    System.out.println("输入错误");
                    adminStuPage();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public  void queryAllStu(){
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
            adminStuPage();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void registTea(){
        try {
            conn= JDBCUtils.getConnection();
            System.out.println("请输入id");
            int id=sc.nextInt();
            String sql2="select id from teacher where id=?";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setInt(1,id);
            ResultSet res=pstmt.executeQuery();
            if(res.next()){
                System.out.println("id已存在，请重新输入");
                registTea();
            }else{
                System.out.println("请输入姓名：");
                String name=sc.next();
                System.out.println("请输入密码：");
                String password=sc.next();
                System.out.println("请输入专业：");
                String career=sc.next();
                String sql1="insert into teacher values(?,?,?,?);";
                PreparedStatement pstmt2= conn.prepareStatement(sql1);
                pstmt2.setInt(1,id);
                pstmt2.setString(2,name);
                pstmt2.setString(3,password);
                pstmt2.setString(4,career);
                int i = pstmt2.executeUpdate();
                if(i>0){
                    System.out.println("注册成功");
                    //First.teacherPage();
                    adminTeaPage();
                }else{
                    System.out.println("注册失败，请重新注册");
                    JDBCUtils.close(null,pstmt2,conn);
                    registTea();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTea(){
        try {
            show1();
            conn=JDBCUtils.getConnection();
            System.out.println("请输入要删除的id");
            int id=sc.nextInt();
            String sql="delete from teacher where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            int i = pstmt.executeUpdate();
            if(i>0){
                System.out.println("删除成功");
                show1();
                adminTeaPage();
            }else {
                System.out.println("删除失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateTea(){
        try {
            show1();
            conn=JDBCUtils.getConnection();
            System.out.println("请选择需要更改的id");
            int id=sc.nextInt();
            System.out.println("请选择需要更改的信息");
            System.out.println("1.密码 2.专业");
            int i1=sc.nextInt();
            System.out.println("请输入更改后的值");
            switch (i1){
                case 1:
                    String password=sc.next();
                    String sql="update techer set password=? where id=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(2,id);
                    pstmt.setString(1,password);
                    int i2 = pstmt.executeUpdate();
                    if(i2>0){
                        System.out.println("更新成功");
                        show1();
                        adminTeaPage();
                    }else {
                        System.out.println("更新失败");
                    }
                    break;
                case 2:
                    //System.out.println("请输入更改后的值");
                    String career=sc.next();
                    String sql1="update teacher set career=? where id=?";
                    pstmt = conn.prepareStatement(sql1);
                    pstmt.setInt(2,id);
                    pstmt.setString(1,career);
                    int i = pstmt.executeUpdate();
                    if(i>0){
                        System.out.println("更新成功");
                        show1();
                        adminTeaPage();
                    }else {
                        System.out.println("更新失败");
                    }
                    break;
                default:
                    System.out.println("输入错误");
                    updateTea();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void queryAllTea(){
        try {
            conn=JDBCUtils.getConnection();
            System.out.println("全部教师个人信息：");
            //System.out.println("id-名字-密码-系别-学分-籍贯-电话-email-性别");
            String sql="select * from teacher";
            pstmt = conn.prepareStatement(sql);
            //pstmt.setInt(1,id);
            ResultSet res = pstmt.executeQuery();
            while (res.next()){
                int id=res.getInt("id");
                String name=res.getString("name");
                String password=res.getString("password");
                String career=res.getString("career");
                System.out.print("|id:"+id+"|");
                System.out.print("姓名:"+name+"|");
                System.out.print("密码:"+password+"|");
                System.out.print("系别:"+career+"|");
                System.out.println("=======");
                adminTeaPage();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void logic() {
        try {
            //conn= JDBCUtils.getConnection();
            System.out.println("请输入id");
            id=sc.nextInt();
            System.out.println("请输入密码");
            String password=sc.next();
            String sql="select * from admin where id=? and password=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.setString(2,password);
            ResultSet res=pstmt.executeQuery();
            if(res.next()){
                //String name=res.getString("name");
                System.out.println("登录成功");
                JDBCUtils.close(res,pstmt,conn);
                adminPage();
            }else {
                System.out.println("登录失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void regist() {
        try {
            conn= JDBCUtils.getConnection();
            System.out.println("请输入id");
            int id=sc.nextInt();
            String sql2="select id from admin where id=?";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setInt(1,id);
            ResultSet res=pstmt.executeQuery();
            if(res.next()){
                System.out.println("id已存在，请重新输入");
                JDBCUtils.close(res,pstmt,conn);
                regist();
            }else{
                System.out.println("请输入姓名：");
                String name=sc.next();
                System.out.println("请输入密码：");
                String password=sc.next();
                String sql1="insert into admin values(?,?,?);";
                PreparedStatement pstmt2= conn.prepareStatement(sql1);
                pstmt2.setInt(1,id);
                pstmt2.setString(2,name);
                pstmt2.setString(3,password);
                int i = pstmt2.executeUpdate();
                if(i>0){
                    System.out.println("注册成功");
                }else{
                    System.out.println("注册失败，请重新注册");
                    JDBCUtils.close(null,pstmt2,conn);
                    regist();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        try {
            conn=JDBCUtils.getConnection();
            System.out.println("请输入要删除的id");
            int id=sc.nextInt();
            String sql="delete from admin where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            int i = pstmt.executeUpdate();
            if(i>0){
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
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
            System.out.println("1.密码 2.退出");
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
                        adminAdmDao();
                    }else {
                        System.out.println("更新失败");
                    }
                    break;

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
            System.out.println("管理员个人信息：");
            String sql="select * from admin where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet res = pstmt.executeQuery();
            if(res.next()){
                int id=res.getInt("id");
                String name=res.getString("name");
                String password=res.getString("password");
                System.out.print("|id:"+id+"|");
                System.out.print("姓名:"+name+"|");
                System.out.print("密码:"+password+"|");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add() {

    }
    public void show(){
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   public void show1(){
       try {
           conn=JDBCUtils.getConnection();
           System.out.println("全部教师个人信息：");
           //System.out.println("id-名字-密码-系别-学分-籍贯-电话-email-性别");
           String sql="select * from teacher";
           pstmt = conn.prepareStatement(sql);
           //pstmt.setInt(1,id);
           ResultSet res = pstmt.executeQuery();
           while (res.next()){
               int id=res.getInt("id");
               String name=res.getString("name");
               String password=res.getString("password");
               String career=res.getString("career");
               System.out.print("|id:"+id+"|");
               System.out.print("姓名:"+name+"|");
               System.out.print("密码:"+password+"|");
               System.out.print("系别:"+career+"|");
               System.out.println("=======");

           }
       } catch (SQLException e) {
           e.printStackTrace();
       }

   }

}
